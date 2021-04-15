package com.study.onjava8.char08;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/15 9:56
 */
public class BlankFinal {
    private final int i = 0;//initialized final
    private final int j;//blank final
    private final Poppet p;//blank final reference

    //Blank finals MUS be initialized in constructor
    public BlankFinal(int j) {
        this.j = j;
        this.p = new Poppet(j);
    }

    public BlankFinal() {
        j = 1;
        p = new Poppet(1);
    }
}

class Poppet {
    private int i;

    public Poppet(int i) {
        this.i = i;
    }
}
