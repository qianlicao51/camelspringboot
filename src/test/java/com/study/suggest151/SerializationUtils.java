package com.study.suggest151;

import java.io.*;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 10:43
 */
public class SerializationUtils {
    private static String FILE_NAME = "d:/obj.bin";

    public static void writeObject(Serializable s) {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(s);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObject() {
        Object obj = null;
        try {
            final ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME));
            obj = input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
