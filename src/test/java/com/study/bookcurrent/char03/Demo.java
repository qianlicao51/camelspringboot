package com.study.bookcurrent.char03;

import java.util.HashSet;
import java.util.Set;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/1 11:01
 */
public class Demo {
    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<>();
    }
}

class Secret {

}
