package com.study;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/21 16:29
 */
public class Cmd {
    public static void main(String[] args) {
        final Collection<File> files = FileUtils.listFiles(new File(""),
                new String[]{"mp4", "m4v", "TS"}, false);

        files.forEach(file -> {
            System.out.println(file);
            // String cmd="cmd /k ffmpeg -i " + file + " -ss 00:10   -f image2 -r 0.003 " + file.getParent() + "  /%02d.jpg";
            String cmd = "cmd /c ffmpeg -i \"" + file + "\" -ss 00:10   -f image2 -r 0.003   \"" + file.getParent() + "/%02d.jpg\"";
            System.out.println(cmd);
            execCommand(cmd);
        });


    }

    public static void execCommand(String arstringCommand) {
        try {
            final Runtime runtime = Runtime.getRuntime();
            try {
                Process process = runtime.exec(arstringCommand);
                BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line;
                System.out.println("OUTPUT");
                while ((line = stdoutReader.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("ERROR");
                while ((line = stderrReader.readLine()) != null) {
                    System.out.println(line);
                }
                int exitVal = process.waitFor();
                System.out.println("process exit value is " + exitVal);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
