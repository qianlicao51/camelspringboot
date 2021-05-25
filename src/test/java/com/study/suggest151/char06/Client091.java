package com.study.suggest151.char06;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

enum CommonIdentifer implements Identifier {
    //权限级别
    Reader, Author, Admin;

    @Override
    public boolean identify() {
        return false;
    }
}

/**
 * @author study
 * @version 1.0
 * @date 2021/5/25 15:54
 */
interface Identifier {
    //无权访问时的礼貌语
    String REFUSE_WORD = "您无权访问";

    //鉴权
    boolean identify();
}

//注解标注在类上面，但是会保留到运行期
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Access {
    //什么级别可以访问，默认是管理员
    CommonIdentifer level() default CommonIdentifer.Admin;
}

@Access(level = CommonIdentifer.Author)
class Foo {
}

public class Client091 {
    public static void main(String[] args) {
        //初始化逻辑
        final Foo b = new Foo();
        //获取注解
        final Access access = b.getClass().getAnnotation(Access.class);
        if (access == null || !access.level().identify()) {
            //没有注解或者鉴权失败
            System.out.println(access.level().REFUSE_WORD);
        }
    }
}
