package com.study.suggest151.char05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/25 9:29
 */
public class Client077 {
    public static void main1(String[] args) {
        int tagCloudNum = 10;
        final List<String> tagClouds = new ArrayList<>(tagCloudNum);
        //初始化标签云，一般从数据库读入，
        final Random rand = new Random();
        for (int i = 0; i < tagCloudNum; i++) {
            //取得随机位置
            int randomPosition = rand.nextInt(tagCloudNum);
            //当前元素与随机元素交换
            final String temp = tagClouds.get(i);
            tagClouds.set(i, tagClouds.get(randomPosition));
            tagClouds.set(randomPosition, temp);
        }
    }

    public static void main2(String[] args) {
        int tagCloudNum = 10;
        final List<String> tagClouds = new ArrayList<>(tagCloudNum);
        //初始化标签云，一般从数据库读入，
        final Random rand = new Random();
        for (int i = 0; i < tagCloudNum; i++) {
            //取得随机位置
            int randomPosition = rand.nextInt(tagCloudNum);
            //当前元素与随机元素交换
            Collections.swap(tagClouds, i, randomPosition);
        }
    }

    public static void main(String[] args) {
        int tagCloudNum = 10;
        final List<String> tagClouds = new ArrayList<>(tagCloudNum);
        //初始化标签云，一般从数据库读入，
        Collections.shuffle(tagClouds);
    }
}
