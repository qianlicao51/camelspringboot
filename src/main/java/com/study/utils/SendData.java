package com.study.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 发送数据
 */
public class SendData {

	/**
	 * 使用短连接TCP 发送数据
	 *
	 * @param ip
	 * @param port
	 * @param sendContext
	 * @return
	 */
	@SuppressWarnings("finally")
	public static String sendDataByTcpRetulNull(String ip, int port, String sendContext) {
		Socket socket = null;
		DataOutputStream dos = null;
		String s = "";
		try {
			socket = new Socket(ip, port);
			socket.setSoTimeout(10000);
			byte[] msg = sendContext.getBytes("UTF-8");
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(msg);
			dos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接和I/O,释放资源
			IOUtils.closeQuietly(dos);
			IOUtils.closeQuietly(socket);
			return s;
		}
	}

	@SuppressWarnings("finally")
	public static String sendDataByTcp(String ip, int port, String sendContext) {
		Socket socket = null;
		InputStream in = null;
		byte message[] = null;
		DataOutputStream dos = null;
		String s = "";
		try {
			socket = new Socket(ip, port);
			socket.setSoTimeout(10000);
			byte[] msg = sendContext.getBytes("UTF-8");
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(msg);
			dos.flush();
			message = new byte[msg.length];
			in = socket.getInputStream();
			int length = in.read(message);// 接收服务器的响应信息
			s = new String(message, 0, length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接和I/O,释放资源
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(dos);
			IOUtils.closeQuietly(socket);
			return s;
		}
	}

	public static void main(String[] args) throws IOException {
		JSONObject sendContext = new JSONObject();

		sendContext.put("nane", "弓");
		sendContext.put("age", 12);
		System.out.println("A".getBytes("UTF-8").length);// 1
		System.out.println("弓".getBytes("UTF-8").length);// 3
		System.out.println("弓".getBytes("GBK").length);// 2
		System.out.println(sendContext);
		String sendDataByTcp = sendDataByTcp("127.0.0.1", 5550, JSON.toJSONString(sendContext) + "\r\n");
		System.out.println(sendDataByTcp);
		System.out.println("-----------------");
	}
}
