package com.study.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author MI
 * @ClassName HTTPDemo.java
 * @createTime 2021年10月18日 11:31:00
 */
public class HTTPDemo {
    public static void main(String[] args) {
        // postRequest();
        getRequest();
    }

    public static void getRequest() {
        String url = "https://img-blog.csdnimg.cn/20200424160957581.png";
        try {
            Response execute = Request.Get(url).execute();
            InputStream inputStream = execute.returnContent().asStream();
            FileUtils.copyInputStreamToFile(inputStream, new File("d:/" + FilenameUtils.getName(url)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void postRequest() {
        String url = "https://www.jisilu.cn/data/cbnew/cb_list/";
        Map<String, String> paraMap = new LinkedHashMap<>();
        //C 是可转债  E是可交债
        paraMap.put("btype", "C");
        //只列出以上市
        paraMap.put("listed", "Y");
        paraMap.put("qflag", "N");
        try {
            String resultJson = Request.Post(url)
                    .useExpectContinue()
                    .connectTimeout(60000)
                    .socketTimeout(10 * 60000)
                    .version(HttpVersion.HTTP_1_1)
                    .bodyString(new JSONObject().fluentPutAll(paraMap).toJSONString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent().toString();
            System.out.println(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
