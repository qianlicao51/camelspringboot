package com.study.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/3 22:30
 */
public class SendMsgDate implements Serializable {
    private static final long serialVersionUID = 1L;
    private String strDate;

    public SendMsgDate() {
    }

    public SendMsgDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
