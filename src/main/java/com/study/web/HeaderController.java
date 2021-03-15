package com.study.web;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 9:58
 */
@RestController
@RequestMapping("/header")
public class HeaderController {

    /**
     * Authorization: Basic YWRtaW46ODg4ODg4 这行是基本验证账号。默认： 用户admin 密码888888
     * tring.Format("{0}:{1}", "admin", "888888"))
     */
    @RequestMapping(value = "/conn", produces = {"text/html; charset=GBK"})
    public String header(@RequestHeader(value = "Authorization", required = false, defaultValue = "default")
                                 String aut) {
        System.out.println(aut);
        String pwd = String.format("%s:%s", "admin", "888888");
        try {
            final String s = Base64.getEncoder().encodeToString(pwd.getBytes("utf-8"));
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(StringUtils.repeat("-", 20));
        return new DateTime().toString();
    }
}
