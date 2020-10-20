package com.study.utils;

import java.io.UnsupportedEncodingException;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/28 10:56
 */
public class HexStrConver {
    /**
     * 字符串转换成十六进制字符串
     *
     * @param str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 十六进制转换字符串
     *
     * @param hexStr Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * bytes转换成十六进制字符串
     *
     * @param b byte数组
     * @return String 每个Byte值之间空格分隔
     */
    public static String byte2HexStr(byte[] b) {
        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    /**
     * 将16进制字符串转换为byte[]
     */
    public static byte[] hexStringToBytes(String s) {
//		if (TextUtils.isEmpty(hexString))
//	        throw new IllegalArgumentException("this hexString must not be empty");

        int len = s.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return b;
    }


    /**
     * 将16进制字符串转换为gbk字符串
     */
    public static String hexStringToGbk(String s) throws UnsupportedEncodingException {


        int len = s.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }


        String result = new String(b, "GBK");

        return result;

    }


    /**
     * bytes字符串转换为Byte值
     *
     * @param src Byte字符串，每个Byte之间没有分隔符
     * @return byte[]
     */
    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = Byte.decode("0x" + src.substring(i * 2, m)
                    + src.substring(m, n));
        }

        return ret;
    }

    /**
     * String的字符串转换成unicode的String
     *
     * @param strText 全角字符串
     * @return String 每个unicode之间无分隔符
     * @throws Exception
     */
    public static String strToUnicode(String strText) throws Exception {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128) {
                str.append("\\u" + strHex);
            } else
            // 低位在前面补00
            {
                str.append("\\u00" + strHex);
            }
        }
        return str.toString();
    }

    /**
     * unicode的String转换成String的字符串
     *
     * @param hex 16进制值字符串 （一个unicode为2byte）
     * @return String 全角字符串
     */
    public static String unicodeToString(String hex) {
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }

    /**
     * 16进制的字符串表示转成10进制字节数组
     *
     * @param hexString 16进制格式
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        String[] cmds = hexString.split(" ");
        byte[] byteArray = new byte[cmds.length];
        int i = 0;
        for (String b : cmds) {
            if (b.equals("FF")) {
                byteArray[i++] = -1;
            } else {
                byteArray[i++] = Byte.parseByte(b, 16);
            }
        }
        return byteArray;
    }

    /**
     * 16进制的字符串表示转成ASCII字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray2(String hexString) {
        if (hexString != null && !("").equals(hexString)) {
            hexString = hexString.replace(" ", "");
            byte[] byteArray = new byte[hexString.length() / 2];
            hexString = hexString.toLowerCase();
            int k = 0;
            for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
                byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
                byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
                byteArray[i] = (byte) (high << 4 | low);
                k += 2;
            }
            return byteArray;
        } else {
            return null;
        }
    }

    /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param s 原串
     * @return
     */
    public static String convertStringToUTF8(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c >= 0 && c <= 255) {
                    sb.append(c);
                } else {
                    byte[] b;

                    b = Character.toString(c).getBytes("utf-8");

                    for (int j = 0; j < b.length; j++) {
                        int k = b[j];
                        if (k < 0) {
                            k += 256;
                        }
                        sb.append(Integer.toHexString(k).toUpperCase());
                        // sb.append("%" +Integer.toHexString(k).toUpperCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return sb.toString();
    }

    private static String hexString = "0123456789ABCDEF";

    private static int hex2Dec(char ch) {
        if (ch == '0') {
            return 0;
        }
        if (ch == '1') {
            return 1;
        }
        if (ch == '2') {
            return 2;
        }
        if (ch == '3') {
            return 3;
        }
        if (ch == '4') {
            return 4;
        }
        if (ch == '5') {
            return 5;
        }
        if (ch == '6') {
            return 6;
        }
        if (ch == '7') {
            return 7;
        }
        if (ch == '8') {
            return 8;
        }
        if (ch == '9') {
            return 9;
        }
        if (ch == 'a') {
            return 10;
        }
        if (ch == 'A') {
            return 10;
        }
        if (ch == 'B') {
            return 11;
        }
        if (ch == 'b') {
            return 11;
        }
        if (ch == 'C') {
            return 12;
        }
        if (ch == 'c') {
            return 12;
        }
        if (ch == 'D') {
            return 13;
        }
        if (ch == 'd') {
            return 13;
        }
        if (ch == 'E') {
            return 14;
        }
        if (ch == 'e') {
            return 14;
        }
        if (ch == 'F') {
            return 15;
        }
        if (ch == 'f') {
            return 15;
        } else {
            return -1;
        }

    }

    public static String decode(String hexStr) throws UnsupportedEncodingException {
        if (null == hexStr || "".equals(hexStr) || (hexStr.length()) % 2 != 0) {
            return null;
        }

        int byteLength = hexStr.length() / 2;
        byte[] bytes = new byte[byteLength];

        int temp = 0;
        for (int i = 0; i < byteLength; i++) {
            temp = hex2Dec(hexStr.charAt(2 * i)) * 16 + hex2Dec(hexStr.charAt(2 * i + 1));
            bytes[i] = (byte) (temp < 128 ? temp : temp - 256);
        }
        return new String(bytes, "GBK");
    }

    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        // 根据默认编码获取字节数组
        return encodeEncoding(str, "GBK");
        /*byte[] bytes = str.getBytes("GBK");
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
            sb.append(" ");
        }
        return sb.toString();*/
    }

    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encodeEncoding(String str, String encoding) throws UnsupportedEncodingException {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes(encoding);
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * 将二进制转换为10进制
     *
     * @param binStr ：待转换的二进制
     * @return
     */
    public static Integer Biannary2Decimal(String binStr) {
        binStr = binStr.trim();
        Integer sum = 0;
        int len = binStr.length();
        for (int i = 1; i <= len; i++) {
            //第i位 的数字为：
            int dt = Integer.parseInt(binStr.substring(i - 1, i));
            sum += (int) Math.pow(2, len - i) * dt;
        }
        return sum;
    }


    /**
     * 二进制字符串转十六进制字符串
     **/
    public static String conver2HexStr(String str) {
        String sum = "";
        int t = str.length() % 4;
        if (t != 0) {
            for (int i = str.length(); i - 4 >= 0; i = i - 4) {
                String s = str.substring(i - 4, i);
                int tem = Integer.parseInt(String.valueOf(s), 2);
                sum = Integer.toHexString(tem).toUpperCase() + sum;
            }
            String st = str.substring(0, t);
            int tem = Integer.parseInt(String.valueOf(st), 2);
            sum = Integer.toHexString(tem).toUpperCase() + sum;
        } else {
            for (int i = str.length(); i - 4 >= -1; i = i - 4) {
                String s = str.substring(i - 4, i);
                int tem = Integer.parseInt(String.valueOf(s), 2);
                sum = Integer.toHexString(tem).toUpperCase() + sum;
            }
        }
        return sum;
    }

    /**
     * 10进制转16进制
     *
     * @param n
     * @return
     */
    public static String intToHex(int n) {
        //StringBuffer s = new StringBuffer();
        StringBuilder sb = new StringBuilder(8);
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            sb = sb.append(b[n % 16]);
            n = n / 16;
        }
        a = sb.reverse().toString();
        return a;
    }

    /**
     * @param strPart 字符串
     * @return 16进制字符串
     * @throws
     * @Title:string2HexGBK
     * @Description:字符GBK串转16进制字符串
     */
    public static String string2HexGBK(String strPart) {
        byte[] pp = null;
        try {
            pp = strPart.toString().getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < pp.length; i++) {
            result.append(" " + String.format("%02X", pp[i]));
        }
        return result.toString();
    }

    /**
     * @param hexdata 16进制字符串
     * @return 16进制的校验和
     */
    public static String makeChecksum(String hexdata) {
        if (hexdata == null || hexdata.equals("")) {
            return "00";
        }
        hexdata = hexdata.replaceAll(" ", "");
        int total = 0;
        int len = hexdata.length();
        if (len % 2 != 0) {
            return "00";
        }
        int num = 0;
        while (num < len) {
            String s = hexdata.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        return hexInt(total).toUpperCase();
    }

    private static String hexInt(int total) {
        int a = total / 256;
        int b = total % 256;
        if (a > 255) {
            return hexInt(a) + format(b);
        }
        //返回低位在前的16进制
        return format(b) + " " + format(a);
    }

    /**
     * @param hex 16进制
     * @return 00格式
     */
    private static String format(int hex) {
        String hexa = Integer.toHexString(hex);
        int len = hexa.length();
        if (len < 2) {
            hexa = "0" + hexa + " ";
        }
        return hexa;
    }


}

