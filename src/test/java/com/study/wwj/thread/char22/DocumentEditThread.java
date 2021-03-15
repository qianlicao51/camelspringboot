package com.study.wwj.thread.char22;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 16:42
 */
//该线程代表的是主动进行文档编辑，为了增加交互性，使用scanner
public class DocumentEditThread extends Thread {
    private final String documentPath;
    private final String documentName;
    private final Scanner scanner = new Scanner(System.in);

    public DocumentEditThread(String documentPath, String documentName) {
        super("DocumentEditThread");
        this.documentPath = documentPath;
        this.documentName = documentName;
    }

    @Override
    public void run() {
        int times = 0;
        try {
            final Document document = Document.create(documentPath, documentName);
            while (true) {
                //获取用户的键盘输入
                final String text = scanner.next();
                if ("quit".equals(text)) {
                    document.close();
                    break;
                }
                //将内容编辑到document
                document.edit(text);
                //用户输入5次自动保存
                if (times == 5) {
                    document.save();
                    times = 0;
                }
                times++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
