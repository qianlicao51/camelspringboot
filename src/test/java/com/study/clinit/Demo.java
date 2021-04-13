package com.study.clinit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/13 15:37
 */
public class Demo {
    /**
     * offer来了核心知识点 p29 class.forName和 ClassLoader区别
     */
    public static void main(String[] args) throws ClassNotFoundException {
        String className = "com.study.clinit.A";
        // class.from可以指定不对类进行初始化
        Class a = Class.forName(className, false, ClassLoader.getSystemClassLoader());
        final Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(className);
        System.out.println(aClass.getSimpleName());
    }
}
