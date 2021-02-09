package com.study;

import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/11 16:55
 */
public class Json {
    /**
     * 地感轮训-开始
     */
    public final static JSONObject POOL_START = new JSONObject();
    /**
     * 地感轮训-停止
     */
    public final static JSONObject POOL_END = new JSONObject();

    static {
        POOL_START.put("flag", "START");
        POOL_END.put("flag", "STOP");
    }


    synchronized static void synStatic() {
        System.out.println("static");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // 打印:1
        System.out.println(POOL_END);

        new Thread(() -> new Json().synObj()).start();
        new Thread(Json::synStatic).start();

        Random random = new Random(47);
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextBoolean());
        }

        //JSOUP 提交 JOSN
        String url = "http://127.0.0.1:86/bayonet/getCaptureInfo";
        String jsonStr = "{\"areaId\":\"CNMLX06S018\",\"chanNo\":\"CNMLX06S018I01A\",\"uid\":\"3d4a08c116ec4dbb8cd764c2bbb7f328\",\"pound\":\"0\",\"rfid\":\"123123123123\",\"IEFlag\":\"I\",\"ftpUrl\":\"zz中国\"}";
        try {
            final Document post = Jsoup.connect(url).requestBody(jsonStr)
                    .header("Content-Type", "text/*")
                    // .header("Content-Type", "application/json")
                    .ignoreContentType(true).post();
            System.out.println(post.body().text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void synObj() {
        synchronized (this.getClass()) {
            System.out.println("synObj");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
