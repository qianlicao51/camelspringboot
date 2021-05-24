package com.study.clinit;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/13 15:36
 */
public class A implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int num;
    private int a;
    private int b;

    static {
        num = 10;
        System.out.println("A.static 初始值设定项");
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a1 = (A) o;
        return new EqualsBuilder().append(a, a1.a).append(b, a1.b).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(a).append(b).toHashCode();
    }
}
