package com.study.handler;

import com.study.utils.ConfigUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/9 11:05
 * 多线程处理 测试 业务重复打断
 */
@Component("threadMessageHandler2")
public class ThreadMessageHandler2 implements MessageHandler {
    public static final ConcurrentHashMap<String, Thread> MAP = new ConcurrentHashMap<>();

    /**
     * 2#start or  2#stop 模拟控制业务开启关闭
     *
     * @param exchange 消息
     */
    @Override
    public void handler(Exchange exchange) {

        Message in = exchange.getIn();
        String body = in.getBody(String.class);
        final Thread currentThread = Thread.currentThread();

        System.out.println();
        System.out.println(String.format("接受到的数据：%s 处理的线程是%s", body, currentThread));

        final String[] split = StringUtils.split(body, "#");
        body = split[0];
        //开启 停止命令
        String cmd = split[1];

        System.out.println(StringUtils.repeat("--", "*", 30));

        //直接返回的情况
        if ((MAP.containsKey(body) && StringUtils.equalsAnyIgnoreCase(ConfigUtils.CMD_START, cmd))
                || (StringUtils.equalsAnyIgnoreCase(ConfigUtils.CMD_STOP, cmd) && !MAP.containsKey(body))) {
            //业务无需控制，直接返回
            System.out.println("当前业务无需处理" + MAP.containsKey(body) + " " + body);
            return;
        }

        //需要打断的情况
        if (StringUtils.equalsAnyIgnoreCase(ConfigUtils.CMD_STOP, cmd)) {
            final Thread thread = MAP.get(body);
            System.out.println(String.format("key  %s  的线程%s 将被打断", body, thread));
            thread.interrupt();
            return;
        }
        MAP.put(body, currentThread);
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(String.format("%s 线程是%s", body, currentThread.getName()));
            } catch (InterruptedException e) {
                System.out.println(MAP.get(body) + "--线程接收到被打断信号");
                MAP.remove(body);
                //此处直接反复，否则会进入下面 移除 key，导致业务还会发生重复
                return;
            }
        }
    }
}
