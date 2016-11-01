package com.anjlab.fin33.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Саня on 14.09.2016.
 */
public class ExchangeRate {

    //  TODO Wrap with ThreadLocal to make them thread-safe
    public static final SimpleDateFormat HH_MM_FORMAT = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat DD_MM_YYYY_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private Kind kind;
    private BigDecimal price;
    private Currency currency;
    private Trend trend;
    private boolean best;
    private Date date;
    private boolean demo;
    private Bank bank;

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Trend getTrend() {
        return trend;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isBest() {
        return best;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demoMod) {
        this.demo = demoMod;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank nameBank) {
        this.bank = nameBank;
    }

    public String getFormattedDate() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        Date dateWithoutTime = clearTime(calendar);

        calendar.setTime(new Date());
        Date todayWithoutTime = clearTime(calendar);

        StringBuilder builder = new StringBuilder();

        if (dateWithoutTime.equals(todayWithoutTime)) {
            builder.append("cегодня ").append(HH_MM_FORMAT.format(date));
        } else {
            builder.append(DD_MM_YYYY_FORMAT.format(date));
        }

        if (isDemo()) {
            builder.append(", демо");
        }
        return builder.toString();
    }

    private Date clearTime(Calendar calendar) {
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.AM_PM);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "kind=" + kind +
                ", price=" + price +
                ", currency=" + currency +
                ", trend=" + trend +
                ", best=" + best +
                ", date=" + date +
                ", bank=" + bank.getName() +
                '}';
    }

    public enum Kind {BUY, SELL}

    public enum Currency {USD, EUR}

    public enum Trend {UP, DOWN, NONE}
}
