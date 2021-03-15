package com.study.wwj.thread.char22;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 16:26
 */
//代表正在编辑的文档
public class Document {
    //自动保存文档的线程
    private static AutoSavaThread autoSavaThread;
    private final FileWriter writer;
    //如果文档发生变化，change设置为true
    private boolean changed = false;
    //一次保存的内容，可以理解为缓存
    private List<String> content = new ArrayList<>();

    private Document(String documentPath, String documentNames) throws IOException {
        this.writer = new FileWriter(new File(documentPath, documentNames));
    }

    //创建文档，顺便启动自动保存文档的线程
    public static Document create(String documentPath, String documentNames) throws IOException {
        final Document document = new Document(documentPath, documentNames);
        autoSavaThread = new AutoSavaThread(document);
        autoSavaThread.start();
        return document;
    }

    //文档的编辑，其实就是往content队列中提交字符串
    public void edit(String content) {
        synchronized (this) {
            this.content.add(content);
            //文档改变
            this.changed = true;
        }
    }

    //文档关闭的时候，首先中断自动保存线程，然后关闭 writer释放资源
    public void close() throws IOException {
        autoSavaThread.interrupt();
        writer.close();
    }

    //save 用于手动进行文档保存
    public void save() throws IOException {
        synchronized (this) {
            //如果文档已经保存了，直接返回
            if (!changed) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + " execute the save action");
            //将内容写入文档中
            for (String s : content) {
                this.writer.write(s);
                //获取不同系统的换行符
                this.writer.write(System.lineSeparator());
            }
            this.writer.flush();
            this.changed = false;//表明此刻没有新的内容编辑
            this.content.clear();
        }
    }
}
