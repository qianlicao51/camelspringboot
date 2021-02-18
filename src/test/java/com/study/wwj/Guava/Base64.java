package com.study.wwj.Guava;

import com.google.common.base.Preconditions;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/16 15:23
 */
public class Base64 {
    private final static String CODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private final static char[] CODE_DICT = CODE_STRING.toCharArray();


    public static String encode(String plain) {
        Preconditions.checkNotNull(plain);
        final StringBuilder builder = new StringBuilder();


        return builder.toString();
    }


    private static String toBinary(final String source) {

        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            final String binaryString = Integer.toBinaryString(source.charAt(i));
        }
        return builder.toString();
    }
}
