package com.study.code;

/**
 * @Title:  PrefixedStringDecoder.java
 * @Package com.zhuzi.code
 * @Description:    TODO(用一句话描述该文件做什么)
 * @author: gongruiqing
 * @date:   2019年5月24日 下午3:04:52
 * @version V1.0
 * @Copyright: 2019 口岸科技. All rights reserved.
 *
 */

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 重写编码过滤器
 *
 * @author dehuisun
 *
 */
public class PrefixedStringDecoder extends CumulativeProtocolDecoder {
	private int prefixLength = 0;// 定义报文转换预留字节长度
	private int maxDataLength = 8192;// 最大字节长度
	private String encoding = "UTF-8";
	private boolean lenthIncludeSelf = true;

	public int getPrefixLength() {
		return prefixLength;
	}

	public void setPrefixLength(int prefixLength) {
		this.prefixLength = prefixLength;
	}

	public int getMaxDataLength() {
		return maxDataLength;
	}

	public void setMaxDataLength(int maxDataLength) {
		this.maxDataLength = maxDataLength;
	}

	public boolean isLenthIncludeSelf() {
		return lenthIncludeSelf;
	}

	public void setLenthIncludeSelf(boolean lenthIncludeSelf) {
		this.lenthIncludeSelf = lenthIncludeSelf;
	}

	/**
	 * 处理
	 */
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		byte[] b = new byte[in.limit()];
		in.get(b);
		String str = new String(b, "utf-8");
		out.write(str.toString());
		return true;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
