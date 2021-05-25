package com.study.suggest151.char06;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/25 15:37
 */
public class Client090 {
    //鸟巢 工厂方法模式
    enum BirdNest {
        Sparrow;

        //鸟的繁殖
    }

    @Retention(RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    @interface Desc {
        Color c() default Color.White;

        enum Color {
            White, Grayish, Yellow;
        }
    }

    @Desc(c = Desc.Color.White)
    static abstract class Bird {
        //鸟的颜色
        public abstract Desc.Color getColor();
    }

    //麻雀
    static class Sparrow extends Bird {
        private Desc.Color color;

        public Sparrow() {
            this.color = Desc.Color.Grayish;
        }

        public Sparrow(Desc.Color color) {
            this.color = color;
        }

        @Override
        public Desc.Color getColor() {
            return null;
        }
    }
}
