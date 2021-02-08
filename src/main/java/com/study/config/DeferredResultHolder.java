package com.study.config;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/8 9:28
 */

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在线程1和线程2之间传递DeferredResult对象
 */
@Component
public class DeferredResultHolder {

    /**
     * map的key就是订单号，value就是每个订单的处理结果
     */
    private Map<String, DeferredResult<Object>> map = new ConcurrentHashMap<>(5000);

    public Map<String, DeferredResult<Object>> getMap() {
        return map;
    }
}
