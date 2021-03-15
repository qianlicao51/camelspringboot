package com.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
class CamelspringbootApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void executePipelined() {
        final List<Object> list = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Nullable
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                //1 打开 Pipline
                connection.openPipeline();
                //2 执行批量操作
                for (int i = 0; i < 1000; i++) {
                    String key = "key_" + i;
                    String value = "value_" + i;
                    // connection.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
                    connection.get(key.getBytes(StandardCharsets.UTF_8));
                }
                //3 返回结果,这里返回null
                return null;
                // 4 redisTemplate将会把最终结果汇总到外层的list中。
            }
        });
        //5 查看管道批量操作的返回结果
        for (Object dateTime : list) {
            System.out.println(dateTime);
        }
    }

    @Test
    void contextLoads() {
        final int i = this.hashCode();
        System.out.println(i);
        System.out.println(Integer.toHexString(i));
        //10进制转8进制
        System.out.println(Integer.toOctalString(8));

        // 16 转为  10进制
        System.out.println(Integer.parseInt(Integer.toHexString(i), 16));
    }

}
