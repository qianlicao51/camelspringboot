package com.study.book.springsource.char07;

import com.study.utils.SysUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/4 16:01
 */
@Aspect
public class AspectJTest {
    static ThreadLocal<String> threadLocal;

    static {
        System.out.println("AspectJTest.static 初始值设定项");
    }

    {
        System.out.println("AspectJTest.实例化初始器");
    }

    @Pointcut("execution(* *.test(..))*")
    public void test() {
    }

    @Before("test()")
    public void beforeTest() {
        threadLocal = new ThreadLocal<>();
        System.out.println(threadLocal.get());
        System.out.println("AspectJTest.beforeTest");
        final String date = SysUtils.getDate();
        System.out.println(date);
        threadLocal.set(date);
    }

    @After("test()")
    public void afterTest() {
        final String date = SysUtils.getDate();
        System.out.println(date);
        System.out.println(threadLocal.get());
        System.out.println("AspectJTest.afterTest");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.out.println("before1");
        Object o = null;
        try {
            o = p.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after1");
        System.out.println(o);
        return o;
    }

    @After("test()")
    public void compline() {
        threadLocal.remove();
        System.out.println("finished");
    }
}
