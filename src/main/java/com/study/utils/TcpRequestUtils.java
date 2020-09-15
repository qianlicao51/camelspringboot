package com.study.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpRequestUtils {

	private static Logger logger = LoggerFactory.getLogger(TcpRequestUtils.class); // 日志记录
	private static int timeOut = 500;


	@SuppressWarnings("finally")
	public static String tcpPost(String ip, int port, String param) throws IOException {
		Socket socket = null;
		InputStream is = null;
		byte message[] = null;
		DataOutputStream dos = null;
		String s = "";
		try {
			socket = new Socket(ip, port);
			socket.setSoTimeout(timeOut);
			byte[] msg = param.getBytes("UTF-8");
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(msg);
			dos.flush();
			message = new byte[128];
			is = socket.getInputStream();
			is.read(message);// 接收服务器的响应信息
			s = new String(message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接和I/O,释放资源
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(dos);
			IOUtils.closeQuietly(socket);
			return s;
		}
	}

	public static void tcpPostReNull(String ip, int port, String param) throws IOException {
		Socket socket = null;
		DataOutputStream dos = null;
		try {
			socket = new Socket(ip, port);
			socket.setSoTimeout(timeOut);
			byte[] msg = param.getBytes("UTF-8");
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(msg);
			dos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接和I/O,释放资源
			IOUtils.closeQuietly(dos);
			IOUtils.closeQuietly(socket);
		}
	}
	public static void main(String[] args) throws IOException {
		System.out.println("-------------");
		tcpPostReNull("127.0.0.1", 9000, "senddae");
		System.out.println("-------------");
	}
}
