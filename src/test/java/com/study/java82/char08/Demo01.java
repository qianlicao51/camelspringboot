package com.study.java82.char08;

import java.util.List;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/30 11:19
 */
public class Demo01 {
    public static void main(String[] args) {
// final List<String> friends = Arrays.asList("Raphael", "Olivia");
// friends.set(0, "Richard");
// friends.add("Thibaut");//UnsupportedOperationException

        final List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friends);
        friends.add("Chin-Chun");//UnsupportedOperationException

    }
}
