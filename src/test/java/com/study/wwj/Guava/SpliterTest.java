package com.study.wwj.Guava;

import com.google.common.base.Splitter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/16 11:03
 */
public class SpliterTest {

    @Test
    public void test() {
        final List<String> list = Splitter.on("|").splitToList("hello|scala");
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(2));
        assertThat(list.get(0), equalTo("hello"));
    }

    @Test
    public void test_Split_OmitEmpty() {
        List<String> result = Splitter.on("|").splitToList("hello|scala|||");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(5));

        //忽略空格
        result = Splitter.on("|").omitEmptyStrings().splitToList("hello|scala|||");
        assertThat(result.size(), equalTo(2));
    }

    @Test
    void test_Split_OmitEmpty_TrimResult() {
        List<String> result = Splitter.on("|").trimResults()
                .splitToList("hello | scala");
        assertThat(result.get(0), equalTo("hello"));

    }

    @Test
    void testSplitFixLength() {
        final List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(4));
    }
}
