package com.study.onjava8.char06;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/13 11:11
 */
public class OrderOfInitialization {
    public static void main(String[] args) {
        House h = new House();
        h.f(); // Shows that construction is done
    }
}

class Window {
    Window(int marker) {
        System.out.println("Window(" + marker + ")");
    }
}

/**
 * 在类中变量定义的顺序决定了它们初始化的顺序。即使变量定义散布在方法定义之间，它们仍会在任何方法（包括构造器）被调用之前得到初始化
 */
class House {
    Window w1 = new Window(1); // Before constructor

    House() {
        // Show that we're in the constructor:
        System.out.println("House()");
        w3 = new Window(33); // Reinitialize w3
    }

    Window w2 = new Window(2); // After constructor

    void f() {
        System.out.println("f()");
    }

    Window w3 = new Window(3); // At end
}
