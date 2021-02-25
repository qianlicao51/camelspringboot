package com.study.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/22 14:37
 */
@Slf4j
public class HttpRequestUtils {
    /**
     * StandardCharsets.UTF_8
     */
    public static final String CHAT_UTF8 = "UTF-8";

    /**
     * httpPost
     *
     * @param url       路径
     * @param jsonParam 参数
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam) {
        return httpPost(url, jsonParam, false);
    }

    /**
     * httpPost
     *
     * @param url       路径
     * @param jsonParam 参数
     * @return
     */
    public static int httpPostState(String url, JSONObject jsonParam) {
        return httpPostState(url, jsonParam, false);
    }

    /**
     * post请求
     *
     * @param url            url地址
     * @param jsonParam      参数
     * @param noNeedResponse 不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
        // post请求返回结果| DefaultHttpClient 弃用后使用 HttpClientBuilder.create().build()
        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), CHAT_UTF8);
                entity.setContentEncoding(CHAT_UTF8);
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, CHAT_UTF8);
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            }
        } catch (IOException e) {
            log.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    public static int httpPosts(String url, JSONObject jsonParam, boolean noNeedResponse) {
        // post请求返回结果
        int resultInfo = 0;
        try {
            final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost method = new HttpPost(url);

            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), CHAT_UTF8);
                entity.setContentEncoding(CHAT_UTF8);
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, CHAT_UTF8);
            /** 请求发送成功，并得到响应 **/
            resultInfo = result.getStatusLine().getStatusCode();
        } catch (IOException e) {
            log.error("post请求提交失败:" + url, e);
        }
        return resultInfo;
    }

    public static void main(String[] args) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("name", "测试");
        jsonParam.put("work", "工作");
        httpPostForWrapCard("http://localhost:8080/gate/", jsonParam);
    }

    /**
     * post请求
     *
     * @param url       url地址
     * @param jsonParam 参数
     * @return
     */
    public static int httpPostForWrapCard(String url, JSONObject jsonParam) {
        // post请求返回结果
        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        int jsonResult = 0;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), CHAT_UTF8);
                entity.setContentEncoding(CHAT_UTF8);
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, CHAT_UTF8);
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str;
                try {
                    //TODO 解决返回值中文乱码问题
                    str = EntityUtils.toString(result.getEntity());
                    jsonResult = result.getStatusLine().getStatusCode();
                    System.out.println(str);
                } catch (Exception e) {
                    log.error("post请求提交失败:" + url, e);
                }
            } else {
                jsonResult = result.getStatusLine().getStatusCode();
            }
        } catch (IOException e) {
            log.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static JSONObject httpGet(String url) {
        // get请求返回结果
        JSONObject jsonResult = null;
        try {
            final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);

            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /** 读取服务器返回过来的json字符串数据 **/
                String strResult = EntityUtils.toString(response.getEntity());
                /** 把json字符串转换成json对象 **/
                jsonResult = net.sf.json.JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, CHAT_UTF8);
            } else {
                log.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            log.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    /**
     * post请求
     *
     * @param url            url地址
     * @param jsonParam      参数
     * @param noNeedResponse 不需要返回结果
     * @return
     */
    public static int httpPostState(String url, JSONObject jsonParam, boolean noNeedResponse) {
        // post请求返回结果
        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        int jsonResult = 0;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), CHAT_UTF8);
                entity.setContentEncoding(CHAT_UTF8);
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, CHAT_UTF8);
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = "";
                try {
                    str = EntityUtils.toString(result.getEntity());
                    jsonResult = result.getStatusLine().getStatusCode();
                } catch (Exception e) {
                    log.error("post请求提交失败:" + url, e);
                }
            } else {
                jsonResult = result.getStatusLine().getStatusCode();
            }
        } catch (IOException e) {
            log.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }
}
