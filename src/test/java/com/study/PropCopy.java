package com.study;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author study
 * @version 1.0
 * @date 2021/7/22 14:57
 */
public class PropCopy {
    @Test
    public void copyProp() {
        final Father father = new Father();
        father.setId(1);
        father.setName("father");
        final Son son = new Son();
        BeanUtils.copyProperties(father, son);
        System.out.println(son);
        //
        final Son s1 = new Son();
        s1.setId(2);
        s1.setName("son");
        final Father father1 = new Father();
        BeanUtils.copyProperties(s1, father1);
        System.out.println(father1);
    }

    @Setter
    @Getter
    static class Father {
        private int id;

        private String name;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
        }
    }

    @Setter
    @Getter
    static class Son extends Father {
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
        }
    }
}
