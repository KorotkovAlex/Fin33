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

    public enum Kind {BUY,SELL}
    public enum Currency {USD, EUR}
    public enum Trend {UP, DOWN,NONE}

    private Kind kind;
    private BigDecimal price;
    private Currency currency;
    private Trend trend;
    private boolean best;
    private Date date;
    private boolean demo;
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
    public void setDemo(boolean demoMod){
        this.demo = demoMod;
    }
    public boolean isDemo(){
        return demo;
    }
    public void setBank(Bank nameBank) {
        this.bank = nameBank;
    }

    public Bank getBank() {
        return bank;
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
}
//
//10-03 16:01:17.209 17226-17226/com.anjlab.fin33 D/Bank: banks:
//        [Bank{name='ЗАО Банк "Советский"',
//        exchangeRates=[ExchangeRate{kind=BUY, price=64.9500, currency=USD, trend=UP, best=false,
//        date=Mon Oct 03 11:32:00 GMT+03:00 2016, bank=ЗАО Банк "Советский"},
//        ExchangeRate{kind=SELL, price=65.5500, currency=USD, trend=UP, best=false,
//        date=Mon Oct 03 11:32:00 GMT+03:00 2016, bank=ЗАО Банк "Советский"},
//        ExchangeRate{kind=BUY, price=72.9600, currency=EUR, trend=UP, best=false, date=Mon Oct 03 11:32:00 GMT+03:00 2016,
//        bank=ЗАО Банк "Советский"}, ExchangeRate{kind=SELL, price=73.6100, currency=EUR, trend=UP, best=false,
//        date=Mon Oct 03 11:32:00 GMT+03:00 2016, bank=ЗАО Банк "Советский"}]},
//
//        Bank{name='ПАО "Межтопэнергобанк"', exchangeRates=[ExchangeRate{kind=BUY, price=65.0500, currency=USD, trend=UP, best=true, date=Mon Oct 03 13:12:00 GMT+03:00 2016, bank=ПАО "Межтопэнергобанк"}, ExchangeRate{kind=SELL, price=65.6000, currency=USD, trend=UP, best=false, date=Mon Oct 03 13:12:00 GMT+03:00 2016, bank=ПАО "Межтопэнергобанк"}, ExchangeRate{kind=BUY, price=73.0000, currency=EUR, trend=NONE, best=true, date=Mon Oct 03 13:12:00 GMT+03:00 2016, bank=ПАО "Межтопэнергобанк"}, ExchangeRate{kind=SELL, price=73.4000, currency=EUR, trend=NONE, best=true, date=Mon Oct 03 13:12:00 GMT+03:00 2016, bank=ПАО "Межтопэнергобанк"}]}, Bank{name='ПАО АКБ "Связь-Банк"', exchangeRates=[ExchangeRate{kind=BUY, price=63.8000, currency=USD, trend=UP, best=false, date=Mon Oct 03 09:23:00 GMT+03:00 2016, bank=ПАО АКБ "Связь-Банк"}, ExchangeRate{kind=SELL, price=65.8500, currency=USD, trend=DOWN, best=false, date=Mon Oct 03 09:23:00 GMT+03:00 2016, bank=ПАО АКБ "Связь-Банк"}, ExchangeRate{kind=BUY, price=71.8500, currency=EUR, trend=UP, best=false, date=Mon Oct 03 09:23:00 GMT+03:00 2016, bank=ПАО АКБ "Связь-Банк"}, ExchangeRate{kind=SELL, price=73.9500, currency=EUR, trend=DOWN, best=false, date=Mon Oct 03 09:23:00 GMT+03:00 2016, bank=ПАО АКБ "Связь-Банк"}]}, Bank{name='ЗАО "Банк ФИНАМ"', exchangeRates=[ExchangeRate{kind=BUY, price=65.0000, currency=USD, trend=UP, best=false, date=Mon Oct 03 12:58:00 GMT+03:00 2016, bank=ЗАО "Банк ФИНАМ"}, ExchangeRate{kind=SELL, price=65.5000, currency=USD, trend=NONE, best=false, date=Mon Oct 03 12:58:00 GMT+03:00 2016, bank=ЗАО "Банк ФИНАМ"}, ExchangeRate{kind=BUY, price=72.9000, currency=EUR, trend=NONE, best=false, date=Mon Oct 03 12:58:00 GMT+03:00 2016, bank=ЗАО "Банк ФИНАМ"}, ExchangeRate{kind=SELL, price=73.5000, currency=EUR, trend=NONE, best=false, date=Mon Oct 03 12:58:00 GMT+03:00 2016, bank=ЗАО "Банк ФИНАМ"}]}]; currency=USD;kind=SELL
