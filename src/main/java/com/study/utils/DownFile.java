package com.study.utils;

import org.apache.commons.io.FileUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownFile {
	private final static String brow_FF="FF";
    private DownFile() {
    }

    /**
     * 文件下载
     *
     * @param req      HttpServletRequest
     * @param response HttpServletResponse
     * @param paths    文件路径
     * @param fileName 下载显示的文件名
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static void webDownFile(HttpServletRequest req, HttpServletResponse response, Path paths, String fileName) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            String filenamedisplay = java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
            if (brow_FF.equals(getBrowser(req))) {
                // 针对火狐浏览器处理方式不一样了
                filenamedisplay = new String(fileName.getBytes(StandardCharsets.UTF_8.name()), StandardCharsets.ISO_8859_1.name());
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + filenamedisplay);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");

            if (Files.exists(paths)) {
                Files.copy(paths, out);// JDK自带的;缓存是8192
                FileUtils.copyFile(paths.toFile(), out);// 缓存是1024*4
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    private static String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (UserAgent != null) {
            if (UserAgent.indexOf("msie") >= 0) {
                return "IE";
            }

            if (UserAgent.indexOf("firefox") >= 0) {
                return "FF";
            }

            if (UserAgent.indexOf("safari") >= 0) {
                return "SF";
            }
            if (UserAgent.indexOf("mozilla") >= 0) {
                return "Chrome";
            }
        }
        return null;
    }

}
