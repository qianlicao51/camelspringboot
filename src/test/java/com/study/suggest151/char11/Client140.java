package com.study.suggest151.char11;

import com.google.common.collect.*;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/26 15:05
 */
public class Client140 {
    public static void main(String[] args) {
        //不可变列表
        ImmutableList<String> list = ImmutableList.of("A", "B", "C");
        //不可变MAP
        ImmutableMap<Integer, String> map = ImmutableMap.of(1, "map", 2, "age");

        //多值Map
        Multimap<String, String> phoneBook = ArrayListMultimap.create();
        phoneBook.put("张三", "110");
        phoneBook.put("张三", "119");
        Table<Double, Double, String> g = HashBasedTable.create();
        //定义人民广场的经纬度坐标
        g.put(31.23, 121.48, "人民广场");
        //输出坐标点的建筑物
        g.get(31.23, 121.48);

        //双向Map
        //key value都不允许重复的Map
        BidiMap bidiMap = new TreeBidiMap();
        bidiMap.put("1", "A");
        bidiMap.put("2", "B");
        bidiMap.getKey("2");

    }
}
