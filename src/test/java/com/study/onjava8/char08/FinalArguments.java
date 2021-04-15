package com.study.onjava8.char08;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/15 10:05
 */
class Gizmo {
    public void spin() {

    }
}

public class FinalArguments {
    void with(final Gizmo g) {
        // g=new Gizmo();illegal ,g is final
    }

    void without(Gizmo g) {
        g = new Gizmo();
        g.spin();
    }

    void f(final int i) {
        // i++; 不能改变
    }

    int g(final int i) {
        return i + 1;
    }

}
