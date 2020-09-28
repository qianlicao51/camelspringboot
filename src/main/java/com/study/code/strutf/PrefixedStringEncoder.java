package com.study.code.strutf;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/28 11:10
 */
public class PrefixedStringEncoder extends ProtocolEncoderAdapter {

    private String encoding = "UTF-8";

    private int maxDataLength = 8192;//最大字节长度

    private int prefixLength = 0;//定义报文转换预留字节长度

    private boolean lenthIncludeSelf = true;

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        String value = "";
        if (message instanceof byte[]) {
            value = new String((byte[]) message, this.encoding);
        } else {
            value = message.toString();
        }
        int len = value.getBytes(encoding).length;//length(value);
        IoBuffer buffer = IoBuffer.allocate(len).setAutoExpand(true);
        byte bt[] = value.getBytes();
        buffer.put(bt, 0, bt.length);
        buffer.flip();
        out.write(buffer);
    }

    public int getPrefixLength() {
        return prefixLength;
    }

    public int getMaxDataLength() {
        return maxDataLength;
    }

    public void setMaxDataLength(int maxDataLength) {
        this.maxDataLength = maxDataLength;
    }

    public void setPrefixLength(int prefixLength) {
        this.prefixLength = prefixLength;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isLenthIncludeSelf() {
        return lenthIncludeSelf;
    }

    public void setLenthIncludeSelf(boolean lenthIncludeSelf) {
        this.lenthIncludeSelf = lenthIncludeSelf;
    }

    public int length(String s) {
        if (s == null) {
            return 0;
        }
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            // 如果为汉，日，韩，则多加一位
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    public boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

}

