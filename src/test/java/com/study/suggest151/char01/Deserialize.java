package com.study.suggest151.char01;

import com.study.suggest151.SerializationUtils;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 10:53
 */
public class Deserialize {
    public static void main(String[] args) {
        //计税系统反序列化 ，并打印信息
        final Person p = (Person) SerializationUtils.readObject();
        final StringBuffer sb = new StringBuffer();
        sb.append("姓名" + p.getName());
        sb.append("\t基本工资" + p.getSalary().getBasePay());
        sb.append("\t绩效工资" + p.getSalary().getBonus());
        System.out.println(sb);

    }
}
