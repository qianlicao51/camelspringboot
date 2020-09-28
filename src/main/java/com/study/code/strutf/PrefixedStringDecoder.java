package com.study.code.strutf;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/28 11:10
 */
public class PrefixedStringDecoder extends CumulativeProtocolDecoder {
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
        byte[] b = new byte [in.limit()];
        in.get(b);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            //判断当前字节是否是中文
            if(b[i] >= 0 && b[i] <=127){
                //不是中文直接转换保存
                stringBuffer.append((char) b [i]);
            }else{
                //是中文则每个中文2个字节处理
                //判断当前字节是否和总字节还有2位存储中文剩下的2个位，如果报文不够，则忽略这个中文
                if((b.length - i)>2){
                    //定义一个字节组，存放3位的字节
                    byte[] zw = new byte[3];
                    //循环存储3位字节
                    for(int n=0;n<3;n++){
                        zw[n] = b[i];
                        //最后一个字节则i不加1.进行循环体处理
                        if(n<2){
                            i++;
                        }
                    }
                    //将中文按编码进行转换
                    String zwStr = new String(zw,"Utf-8");
                    stringBuffer.append(zwStr);
                }
            }
        }
        out.write(stringBuffer.toString());
        return true;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}

