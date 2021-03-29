package com.study.wwj.thread.char24;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/19 16:47
 */
public class ChatServer {
    //服务端口
    private final int port;
    //线程池
    private ThreadPoolExecutor threadPool;
    //服务端Socket
    private ServerSocket serverSocket;
    //通过构造函数传入端口

    public ChatServer(int port) {
        this.port = port;
    }
    //默认使用 13312端口

    public ChatServer() {
        this(13312);
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().startServer();
    }

    public void startServer() throws IOException {
        threadPool = new ThreadPoolExecutor(2, 2, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1000));

        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);
        System.out.println("Chat server is start and listen at port: " + port);
        this.listen();
    }

    private void listen() throws IOException {
        for (; ; ) {
            //accept是一个阻塞方法，担忧新的链接进来是才会返回
            final Socket client = serverSocket.accept();
            threadPool.execute(new ClientHandler(client));
        }
    }

}
