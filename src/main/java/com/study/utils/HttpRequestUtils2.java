package com.study.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 10:25
 */
@Slf4j
public class HttpRequestUtils2 {

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
        // post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /** 读取服务器返回过来的json字符串数据 **/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /** 把json字符串转换成json对象 **/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    log.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            log.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
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
        DefaultHttpClient httpClient = new DefaultHttpClient();
        int jsonResult = 0;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == 200) {
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

    public static void main(String[] args) {
        httpGet("http://localhost:9090/header/conn", false, null);
    }

    /**
     * 发送get请求，
     *
     * @param url          url
     * @param needResponse 是否需要响应
     * @return
     */
    public static JSONObject httpGet(String url, boolean needResponse, Map headerMap) {

        // get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", "CodeAddT2");
            HttpResponse response = client.execute(request);

            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if (needResponse) {

                    /** 读取服务器返回过来的json字符串数据 **/
                    String strResult = EntityUtils.toString(response.getEntity());
                    /** 把json字符串转换成json对象 **/
                    jsonResult = JSONObject.fromObject(strResult);
                    url = URLDecoder.decode(url, "UTF-8");
                }
            } else {
                log.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            log.error("get请求提交失败:" + url, e);
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
        return httpGet(url, false, null);
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
        DefaultHttpClient httpClient = new DefaultHttpClient();
        int jsonResult = 0;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == 200) {
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
