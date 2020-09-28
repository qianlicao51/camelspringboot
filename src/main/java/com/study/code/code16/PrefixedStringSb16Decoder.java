package com.study.code.code16;

import com.study.utils.ConfigUtils;
import com.study.utils.HexStrConver;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/28 10:55
 */
public class PrefixedStringSb16Decoder extends CumulativeProtocolDecoder {
    private int prefixLength = 0;//定义报文转换预留字节长度
    private int maxDataLength = 8192;//最大字节长度
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
     * 处理报文
     */
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        byte[] b = new byte[in.limit()];
        String ip = session.getRemoteAddress().toString();
        in.get(b);
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer = stringBuffer.append(HexStrConver.byte2HexStr(b));
        out.write(stringBuffer.toString() + "@&&&@" + ip);
        return true;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
