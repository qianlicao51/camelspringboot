package com.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CamelspringbootApplicationTests {

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
