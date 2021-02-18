package com.study.web;

import com.study.utils.SysUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/18 20:03
 */
@Slf4j
@RestController
@RequestMapping("/conn")
public class PostController {
    final static Map<String, String> map = new HashMap<>();

    static {
        map.put("name", "测试" + SysUtils.getDate());
    }

    /**
     * RequestBody(表示 请求体) RequestParam(url中的参数) get可以
     *
     * @param name
     * @param data
     * @return
     */
    @GetMapping("/get")
    public Object get(@RequestParam String name, @RequestBody String data) {
        System.out.println(name);
        System.out.println(StringUtils.repeat("-*", 8));
        System.out.println(data);
        return map;
    }

    @PostMapping("/get")
    public Object post(@RequestParam String name, @RequestBody String data) {
        System.out.println(name);
        System.out.println(StringUtils.repeat("-*", 8));
        System.out.println(data);
        return map;
    }
}
