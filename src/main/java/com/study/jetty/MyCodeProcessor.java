package com.study.jetty;

import com.study.utils.DownFile;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.common.HttpMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/15 21:55
 */
@Component("myCodeProcessor")
public class MyCodeProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        //获取HTTP session|官网例子https://camel.apache.org/components/latest/jetty-component.html
        HttpSession session = exchange.getIn(HttpMessage.class).getRequest().getSession();

        HttpMessage httpMessage = exchange.getIn(HttpMessage.class);
        // TODO 获取 request 和 response
        HttpServletRequest req = httpMessage.getRequest();
        HttpServletResponse response = httpMessage.getResponse();

        ClassPathResource pathResource = new ClassPathResource("pic/1.png");
        Path paths = Paths.get(pathResource.getFile().getAbsolutePath());
        System.out.println(pathResource);

        System.out.println(pathResource.getFile().exists());
        String fileName = "1.jpg";
        DownFile.webDownFile(req, response, paths, fileName);
        // IOUtils.copy(pathResource.getInputStream(), response.getOutputStream());
    }
}
