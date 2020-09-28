package com.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/28 11:28
 */
@Slf4j
public class TcpUtils {
    private static int timeOut = 1500;

    private static int timeOut_ = 3000;

    /**
     * postReNull 向设备发送无返回值报文
     *
     * @throws IOException
     */
    public static void postReNull(String ip, int port, String data) throws IOException {
        postReNullEncode(ip, port, data, "UTF-8");
    }

    public static void postReNullEncode(String ip, int port, String data, String encoding) throws IOException {
        Socket socket = null;
        DataOutputStream dos = null;
        try {
            String tradeMessage = data;
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut);
            byte[] msg = tradeMessage.getBytes(encoding);
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(msg);
            dos.flush();
        } catch (Exception e) {
            //// e.printStackTrace();
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw e;
        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(null, dos, socket);
        }
    }

    public static void main(String[] args) throws IOException {
        String ip = "192.10.10.1";
        int port = 1234;
        String dtte = " ";
        String encoding = " ";
        postReNullEncode(ip, port, dtte, encoding);
    }

    /**
     * postReNullGBK 向设备发送无返回值报文
     *
     * @throws IOException
     */
    public static void postReNullGbk(String ip, int port, String data) throws IOException {
        postReNullEncode(ip, port, data, "GBK");
    }

    /**
     * tcpPost
     *
     * @throws IOException
     */
    public static String post(String ip, int port, String data) throws IOException {
        return postCharsets(ip, port, data, "UTF-8");

    }

    /**
     * @param ip
     * @param port
     * @param data
     * @param charsets
     * @return
     * @throws IOException
     */
    public static String postCharsets(String ip, int port, String data, String charsets) throws IOException {
        Socket socket = null;
        InputStream is = null;
        byte message[] = null;
        DataOutputStream dos = null;
        String value = "";
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut);
            byte[] msg = data.getBytes(charsets);
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(msg);
            dos.flush();
            message = new byte[1024];
            is = socket.getInputStream();
            is.read(message);// 接收服务器的响应信息
            value = new String(message);
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw e;
        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(is, dos, socket);
        }
        return value;
    }

    /**
     * tcpPost GBK
     *
     * @throws IOException
     */
    public static String postGbk(String ip, int port, String data) throws IOException {
        return postCharsets(ip, port, data, "GBK");
    }

    /**
     * tcpPost
     *
     * @throws IOException 读取指定长度byte的返回数据，防止有过长的空串
     */
    public static String postReAssignByte(String ip, int port, int length) throws IOException {
        Socket socket = null;
        InputStream is = null;
        byte message[] = null;
        String value = "";
        try {
            // socket = new Socket(ip, port);
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut);

            message = new byte[length];
            is = socket.getInputStream();
            is.read(message);// 接收服务器的响应信息
            value = new String(message, "gbk");
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw e;
        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(is, null, socket);
        }
        return value;
    }

    /**
     * tcpPost返回字节流
     *
     * @throws IOException
     */
    public static byte[] postReByte(String ip, int port, String data) throws IOException {
        Socket socket = null;
        InputStream is = null;
        byte message[] = null;
        DataOutputStream dos = null;
        try {
            String tradeMessage = data;
            // socket = new Socket(ip, port);
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut);
            byte[] msg = tradeMessage.getBytes("UTF-8");
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(msg);
            dos.flush();
            message = new byte[1024];
            is = socket.getInputStream();
            is.read(message);// 接收服务器的响应信息
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw e;
        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(is, dos, socket);
        }
        return message;
    }

    /**
     * tcpPost 按照发送字节
     *
     * @param byte[] 参数
     * @throws IOException
     */
    @SuppressWarnings("finally")
    public static String postBytes(String ip, int port, byte[] msg) throws Exception {
        Socket socket = null;
        InputStream is = null;
        byte message[] = null;
        DataOutputStream dos = null;
        String value = "";
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut);
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(msg);
            dos.flush();
            message = new byte[1024];
            is = socket.getInputStream();
            is.read(message);// 接收服务器的响应信息
            value = new String(message);
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw e;
        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(is, dos, socket);
            return value;
        }

    }

    /**
     * tcpPost 按照发送字节无返回值
     *
     * @throws IOException
     */
    public static void postBytesReNUll(String ip, int port, byte[] msg) throws Exception {
        postBytesReNUllDelayClose(ip, port, msg, 0);
    }

    /**
     * tcpPost 按照发送字节无返回值,延迟关闭
     *
     * @throws IOException
     */
    public static void postBytesReNUllDelayClose(String ip, int port, byte[] msg, int lazyTime) throws Exception {
        Socket socket = null;
        DataOutputStream dos = null;
        try {
            // socket = new Socket(ip, port);
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut);
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(msg);
            dos.flush();
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
//			 throw new ArithmeticException();
            throw e;
        } finally {
            Thread.sleep(lazyTime);
            closeIO(null, dos, socket);
        }

    }

    /**
     * tcpPost返回16进制字符串
     *
     * @throws IOException
     */
    public static String postRe16(String ip, int port, String data) throws Exception {
        return postRe16delayed(ip, port, data, 0);
    }

    /**
     * tcpPost发送16进制返回16进制字符串
     *
     * @throws IOException
     */
    public static String post16Re16(String ip, int port, String data) throws IOException {
        Socket socket = null;
        InputStream is = null;
        byte message[] = null;
        DataOutputStream dos = null;
        String mes = "";
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut);
            byte[] msg = HexStrConver.hexStringToBytes(data);
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(msg);
            dos.flush();
            message = new byte[1024];
            // message=HexStrConver.toByteArray(data);
            is = socket.getInputStream();
            is.read(message);// 接收服务器的响应信息
            mes = HexStrConver.byte2HexStr(message);
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw e;
        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(is, dos, socket);
        }
        return mes;
    }

    /**
     * tcpPost发送16进制无返回
     *
     * @throws IOException
     */
    public static void post16Renull(String ip, int port, String data) throws IOException {
        Socket socket = null;
        DataOutputStream dos = null;
        try {
            // socket = new Socket(ip, port);
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut);
            byte[] msg = HexStrConver.hexStringToBytes(data);
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(msg);
            dos.flush();
        } catch (Exception e) {
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw e;
        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(null, dos, socket);
        }
    }

    /**
     * tcpPost返回指定字节的16进制字符串
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static String postRe16SelfNew(String ip, int port, byte[] data, int length) throws Exception {
        Socket socket = null;
        InputStream is = null;
        byte message[] = null;
        DataOutputStream dos = null;
        String mes = "";
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut_);
            dos = new DataOutputStream(socket.getOutputStream());
            if (data != null) {
                dos.write(data);
            }
            dos.flush();
            message = new byte[length];
            is = socket.getInputStream();
            is.read(message);// 接收服务器的响应信息
            mes = HexStrConver.byte2HexStr(message);// |474
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw e;
        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(is, dos, socket);
        }
        return mes;
    }

    /**
     * tcpPost返回16进制字符串 (延时)
     *
     * @throws IOException
     */
    public static String postRe16delayed(String ip, Integer port, String data, int delayTime) throws Exception {
        Socket socket = null;
        InputStream is = null;
        byte message[] = null;
        DataOutputStream dos = null;
        String mes = "";
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeOut);
            socket.setSoTimeout(timeOut);
            byte[] msg = data.getBytes("UTF-8");
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(msg);
            dos.flush();
            message = new byte[1024];
            Thread.sleep(delayTime);
            is = socket.getInputStream();
            is.read(message);// 接收服务器的响应信息
            mes = HexStrConver.byte2HexStr(message);
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw new Exception();

        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(is, dos, socket);
        }
        return mes;
    }

    /**
     * tcpPost返回指定字节的16进制字符串
     *
     * @throws Exception
     */
    public static String postRe16Self(String ip, int port, byte[] data, int length) throws Exception {
        return postRe16SelfDelay(ip, port, data, length, 0, timeOut);
    }

    /**
     * 指定延时
     *
     * @param ip
     * @param port
     * @param data
     * @param length
     * @param delayTime
     * @return
     * @throws Exception
     */
    public static String postRe16SelfDelay(String ip, int port, byte[] data, int length, int delayTime) throws Exception {
        return postRe16SelfDelay(ip, port, data, length, delayTime, timeOut);
    }

    public static String postRe16SelfDelay(String ip, int port, byte[] data, int length, int delayTime, int sockTimeOut) throws Exception {
        Socket socket = null;
        InputStream is = null;
        byte message[] = null;
        DataOutputStream dos = null;
        String mes = "";
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), sockTimeOut);
            socket.setSoTimeout(sockTimeOut);
            dos = new DataOutputStream(socket.getOutputStream());
            if (data != null) {
                dos.write(data);
            }
            dos.flush();
            message = new byte[length];
            Thread.sleep(delayTime);
            is = socket.getInputStream();
            is.read(message);// 接收服务器的响应信息
            mes = HexStrConver.byte2HexStr(message);
        } catch (Exception e) {
            log.error("error equ_ip:{}|{}", ip, e.getMessage());
            throw e;
        } finally {
            // 关闭连接和I/O,释放资源
            closeIO(is, dos, socket);
        }
        return mes;
    }

    /**
     * 关闭流
     *
     * @param is
     * @param dos
     * @param socket
     */
    private static void closeIO(InputStream is, DataOutputStream dos, Socket socket) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();


            }
        }
        if (dos != null) {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}

