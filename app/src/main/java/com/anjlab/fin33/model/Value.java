package com.anjlab.fin33.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by alex on 11/21/16.
 */

public class Value {
    private Date date;
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
