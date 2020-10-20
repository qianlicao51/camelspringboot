package com.study.code.strutf;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/28 11:09
 */

public class PrefixedStringCodecFactory implements ProtocolCodecFactory {
    private String encoding = "UTF-8";
    private boolean lenthIncludeSelf = true;

    private final PrefixedStringEncoder encoder;

    private final PrefixedStringDecoder decoder;

    public PrefixedStringCodecFactory() {
        encoder = new PrefixedStringEncoder();
        encoder.setEncoding(encoding);
        decoder = new PrefixedStringDecoder();
        decoder.setEncoding(encoding);
        encoder.setLenthIncludeSelf(lenthIncludeSelf);
        decoder.setLenthIncludeSelf(lenthIncludeSelf);
    }

    public ProtocolEncoder getEncoder() throws Exception {
        return encoder;
    }

    public ProtocolDecoder getDecoder() throws Exception {
        return decoder;
    }

    public void setDecoderPrefixLength(int prefixLength) {
        decoder.setPrefixLength(prefixLength);
    }

    public void setEncoderPrefixLength(int prefixLength) {
        encoder.setPrefixLength(prefixLength);
    }

    public int getDecoderPrefixLength() {
        return decoder.getPrefixLength();
    }

    public int getEncoderPrefixLength() {
        return encoder.getPrefixLength();
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
        encoder.setEncoding(encoding);
        decoder.setEncoding(encoding);
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
        return getDecoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
        return getEncoder();
    }

    public boolean isLenthIncludeSelf() {
        return lenthIncludeSelf;
    }

    public void setLenthIncludeSelf(boolean lenthIncludeSelf) {
        this.lenthIncludeSelf = lenthIncludeSelf;
        encoder.setLenthIncludeSelf(lenthIncludeSelf);
        decoder.setLenthIncludeSelf(lenthIncludeSelf);
    }

}

