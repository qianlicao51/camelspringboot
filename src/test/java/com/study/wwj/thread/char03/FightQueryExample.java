package com.study.wwj.thread.char03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 11:01
 */
public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {

        final List<String> results = search("SH", "BJ");
        System.out.println("======result=======");
        results.forEach(System.out::println);
    }

    static List<String> search(String original, String dest) {
        final ArrayList<String> result = new ArrayList<>();
        //创建线程
        final List<FightQueryTask> tasks = fightCompany.stream()
                .map(f -> createSearchTask(f, original, dest))
                .collect(Collectors.toList());
        //启动线程
        tasks.forEach(Thread::start);
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //获取每一个线程查询结果，并且加入result中
        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String original, String dest) {
        return new FightQueryTask(fight, original, dest);

    }
}
