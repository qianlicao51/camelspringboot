package com.study.jetty;

import com.study.handler.MessageHandler;
import com.study.utils.SysUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.http.common.HttpMessage;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/21 16:00
 */
@Component("httpHandle")
public class HttpHandle implements MessageHandler {

    @Override
    public void handler(Exchange exchange) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        Message in = exchange.getIn();
        // 获取post ：raw|postman 提交POST数据 https://blog.csdn.net/qq_35275233/article/details/86548137
        String headbody = in.getBody(String.class);
        System.out.println(headbody);

        HttpMessage httpMessage = exchange.getIn(HttpMessage.class);
        // TODO 获取 request 和 response
        HttpServletRequest req = httpMessage.getRequest();
        HttpServletResponse response = httpMessage.getResponse();

        response.setContentType("text/html; charset=GBK");
        response.setCharacterEncoding("GBK");
        in.setBody(SysUtils.getDate());
        // exchange.getOut().setBody(SysUtils.getDateYmD());
        // responsePrint(req,response,headbody);


    }

    /**
     * 直接返回字符串内容，使用会有问题和jetty使用的servlet-api
     *
     * @param request  request
     * @param response response
     * @param str      发送字符串
     */
    protected void responsePrint(HttpServletRequest request, HttpServletResponse response, String str) {
        try {
            response.setContentType("text/html; charset=GBK");
            response.setCharacterEncoding("GBK");
            response.getWriter().print(str);
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
