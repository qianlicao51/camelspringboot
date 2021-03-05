package com.study.wwj.thread.char16;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 15:47
 */
public class FlightSecurity {
    private int count = 0;
    //登机牌
    private String boardingPass = "null";
    //身份证
    private String idCard = "null";

    public synchronized void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("======exception===" + toString());
        }
    }

    @Override
    public String toString() {
        return "The " + count +
                " passengers ,boardingPass[" + boardingPass + "] ,idCard [" + idCard + "]";
    }
}
