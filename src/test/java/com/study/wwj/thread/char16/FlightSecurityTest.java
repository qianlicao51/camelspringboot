package com.study.wwj.thread.char16;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 15:52
 */
public class FlightSecurityTest {
    public static void main(String[] args) {
        //定义三个旅客
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity, "A123456", "AF123456").start();
        new Passengers(flightSecurity, "B123456", "BF123456").start();
        new Passengers(flightSecurity, "C123456", "CF123456").start();

    }

    static class Passengers extends Thread {
        //机场安检类
        private final FlightSecurity flightSecurity;
        //旅客身份证
        private final String idCard;
        //登机牌
        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true) {
                flightSecurity.pass(boardingPass, idCard);
            }
        }
    }
}
