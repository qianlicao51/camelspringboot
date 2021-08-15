package com.study.book.designpattern.char10.strategy;

public interface Strategy {
    Hand nextHand();
    void study(boolean win);
}
