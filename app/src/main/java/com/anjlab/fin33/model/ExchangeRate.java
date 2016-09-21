package com.anjlab.fin33.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Саня on 14.09.2016.
 */
public class ExchangeRate {
    public enum Kind {BUY,SELL}
    public enum Currency {USD, EUR}
    public enum Trend {UP, DOWN,NONE}

    private Kind kind;
    private BigDecimal price;
    private Currency currency;
    private Trend trend;
    private boolean best;
    private Date date;
    private Bank bank;

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public Trend getTrend() {
        return trend;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    public boolean isBest() {
        return best;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setBank(Bank nameBank) {
        this.bank = nameBank;
    }

    public Bank getBank() {
        return bank;
    }


}
