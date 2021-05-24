package com.study;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Collection;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/24 19:25
 */
public class FileRename {
    public static void main(String[] args) {
        String filePath = "F:\\迅雷下载\\【www.gaoqing.tv】国产剧 青瓷 (全48集) HunanTV.Green.Porcelain.Complete.HDTV.720p.x264-CHDTV\\";
        final Collection<File> files = FileUtils.listFiles(new File(filePath), null, false);
        files.stream().forEach(file -> {
            System.out.println(file.getName());
            String fileName = StringUtils.replaceEach(file.getName(), new String[]{"【www.gaoqing.tv】", " 青瓷 HunanTV.Green.Porcelain.Ep", ".HDTV.720p.x264-CHDTV"}
                    , new String[]{"", "青瓷-", ""});
            file.renameTo(new File(file.getParentFile(), fileName));
        });

        System.out.println(StringUtils.replaceEach("【www.gaoqing.tv】国产剧 青瓷 HunanTV.Green.Porcelain.Ep05.HDTV.720p.x264-CHDTV.mkv"
                , new String[]{"【www.gaoqing.tv】", " 青瓷 HunanTV.Green.Porcelain.Ep", ".HDTV.720p.x264-CHDTV"}
                , new String[]{"", "青瓷-", ""}));

    }
}
