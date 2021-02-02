package com.study.java82.char05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/30 16:59
 */
public class Demo01 {
    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


    public static void main(String[] args) {

        //找出2011年发生的所有交易，并按交易额排序(从低到高)
        final List<Transaction> tr2011 = transactions.stream()//
                .filter(transaction -> transaction.getYear() == 2011)//
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        //交易员都在哪些不同的城市工作过
        final List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());


        //查找所有的剑桥的交易员，并按姓名排序
        final List<Trader> traders = transactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge")).collect(Collectors.toList());

        //返回所有交易员的姓名字符串，按照字母顺序排序|次方法效率不高，使用下面的更合适
        final String reduce = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct().sorted().reduce(" ", (n1, n2) -> n1 + n2);

        final String traderStr = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct().sorted().collect(Collectors.joining());


    }
}
