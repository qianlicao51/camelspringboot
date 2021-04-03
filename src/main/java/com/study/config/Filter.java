package com.study.config;

import com.study.camelconfig.adapter.KeepAliveFilter;
import org.apache.mina.core.filterchain.IoFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/11 12:51
 */
@Configuration
public class Filter {
    @Bean("ioFilters")
    public List<IoFilter> ioFilters() {
        return Arrays.asList(new KeepAliveFilter());
    }
}
