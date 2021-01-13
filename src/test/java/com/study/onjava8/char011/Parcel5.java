package com.study.onjava8.char011;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/13 16:35
 */
public class Parcel5 {
    public Destination destination(String s) {
        final class PDestination implements Destination {
            private String label;

            public PDestination(String label) {
                this.label = label;
            }

            @Override
            public String readLabel() {
                return null;
            }
        }

        return new PDestination(s);
    }

    public static void main(String[] args) {
        final Parcel5 p = new Parcel5();
        //向上转型
        final Destination tasmaina = p.destination("Tasmaina");

    }

    public static void main1(String[] args) {
        System.out.println(450 * 4);
        System.out.println(4500 - 950 * 2);
        System.out.println(4500 - 1700);
    }
}
