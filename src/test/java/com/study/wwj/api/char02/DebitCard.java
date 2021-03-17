package com.study.wwj.api.char02;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/17 15:54
 */
public class DebitCard {
    private final String account;
    private final int amount;

    public DebitCard(String account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("account", account)
                .append("amount", amount)
                .toString();
    }
}
