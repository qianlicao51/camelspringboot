package com.study.web;

import com.alibaba.fastjson.JSONObject;
import com.study.config.DeferredResultHolder;
import com.study.utils.SysUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/8 9:23
 */

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final String DATE_YMDHMS = "yyyy-MM-dd HH:mm:ss:SSS";
    @Autowired
    DeferredResultHolder deferredResultHolder;

    public static String getDate() {
        return new DateTime().toString(DATE_YMDHMS);
    }

    @GetMapping("/test")
    public DeferredResult test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("主线程开始");
        //随机生成8位的订单号
        String oderNumber = RandomStringUtils.randomNumeric(8);
        log.info("oderNumber=" + oderNumber);
        //订单处理结果
        DeferredResult<Object> result = new DeferredResult<>(30 * 1000L);
        log.info("开始" + getDate());
        //处理超时事件 采用委托机制
        result.onTimeout(new Runnable() {
            @Override
            public void run() {
                log.info(oderNumber + "DeferredResult超时");
                result.setResult(oderNumber + "超时了!" + getDate());
                deferredResultHolder.getMap().remove(oderNumber);
            }
        });

        System.out.println("阻塞1");
        result.onCompletion(() -> {
            //完成后
            log.info(oderNumber + "调用完成" + SysUtils.getDate());
            deferredResultHolder.getMap().remove(oderNumber);
        });
        System.out.println("阻塞2");
        deferredResultHolder.getMap().put(oderNumber, result);
        log.info("主线程返回");
        return result;
    }

    @GetMapping("/test1")
    public void test1(String key, String meg) {
        if (deferredResultHolder.getMap().containsKey(key)) {
            log.info(key + "key 配对成功");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("name", "John");
            data.put("sex", "male");
            data.put("age", 22);
            data.put("is_student", true);
            data.put("hobbies", new String[]{"hiking", "swimming"});
            JSONObject obj = new JSONObject(data);
            // 或是下面这种写法，将 java 对象转换为 json 对象
            // JSONObject obj = JSONObject.fromObject(data);
            deferredResultHolder.getMap().get(key).setResult(obj);
        } else {
            log.info(key + "key 配对失败");
        }
    }
}
