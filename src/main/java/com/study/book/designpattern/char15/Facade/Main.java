package com.study.book.designpattern.char15.Facade;

import com.study.book.designpattern.char15.Facade.pagemaker.PageMaker;

public class Main {
    public static void main(String[] args) {
        PageMaker.makeWelcomePage("hyuki@hyuki.com", "welcome.html");
    }
}
