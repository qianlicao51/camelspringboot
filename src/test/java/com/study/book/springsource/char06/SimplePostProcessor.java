package com.study.book.springsource.char06;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/23 11:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplePostProcessor {
    private String connectionString;
    private String password;
    private String username;
}
