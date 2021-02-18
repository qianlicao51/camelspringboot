package com.study.wwj.Guava;

import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/16 11:21
 */
public class StringsTest {

    @Test
    public void testStringsMethod() {
        assertThat(Strings.emptyToNull(""), nullValue());
        assertThat(Strings.nullToEmpty(null), equalTo(""));
        assertThat(Strings.nullToEmpty("hello"), equalTo("hello"));
        //公共前缀
        assertThat(Strings.commonPrefix("Hello", "Hit"), equalTo("H"));

        //左边填充
        assertThat(Strings.padStart("Alex", 5, 'H'), equalTo("HAlex"));
        assertThat(Strings.padEnd("Alex", 5, 'H'), equalTo("AlexH"));
    }


    @Test
    void testCharsets() {
    }

}
