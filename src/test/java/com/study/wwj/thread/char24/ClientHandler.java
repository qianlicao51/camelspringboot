package com.study.wwj.thread.char24;

import java.io.*;
import java.net.Socket;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/19 16:56
 */
public class ClientHandler implements Runnable {
    private final Socket socket;
    //
    private final String clientIdentity;

    public ClientHandler(final Socket client) {
        this.socket = client;
        this.clientIdentity = socket.getInetAddress().getHostAddress() + socket.getPort();
    }

    @Override
    public void run() {
        try {
            this.chat();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chat() throws IOException {
        final BufferedReader bufferedReader = wrap2print(this.socket.getInputStream());
        final PrintStream printStream = wrap2Print(this.socket.getOutputStream());
        String received;
        while ((received = bufferedReader.readLine()) != null) {
            //将客户端消息发送到控制台
            System.out.printf("clent:%s-message:%s\n", clientIdentity, received);
            if (received.equals("quit")) {
                writeClient(printStream, "cill will close");
                socket.close();
                break;
            }
            writeClient(printStream, "Server:" + received);
        }
    }

    //将字节流转换成 BufferedReader 缓存字符流
    private BufferedReader wrap2print(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private PrintStream wrap2Print(OutputStream outputStream) {
        return new PrintStream(outputStream);
    }

    //向客户端发送请求
    private void writeClient(PrintStream printStream, String message) {
        printStream.println(message);
        printStream.flush();
    }
}
