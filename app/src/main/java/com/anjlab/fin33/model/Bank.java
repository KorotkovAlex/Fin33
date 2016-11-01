package com.anjlab.fin33.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Саня on 14.09.2016.
 */
public class Bank {
    private String name;
    private List<ExchangeRate> exchangeRates;
    private String address;
    private String phoneNumber;
    private String img;

    public static ExchangeRate findBestRate(
            List<Bank> banks, ExchangeRate.Currency currency, ExchangeRate.Kind kind) {
        for (Bank bank : banks) {
            ExchangeRate rate = bank.getExchangeRates(currency, kind);
            if (rate.isBest()) {
                return rate;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addExchangeRate(ExchangeRate exchangeRate) {
        if (this.exchangeRates == null) {
            this.exchangeRates = new ArrayList<>();
        }
        this.exchangeRates.add(exchangeRate);
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ExchangeRate getExchangeRates(ExchangeRate.Currency currency, ExchangeRate.Kind kind) {
        for (ExchangeRate exchangeRate : exchangeRates) {
            if (exchangeRate.getCurrency() == currency
                    && exchangeRate.getKind() == kind) {
                return exchangeRate;
            }
        }
        return null;
    }

    public ExchangeRate getBest(ExchangeRate.Currency currency, ExchangeRate.Kind kind) {
        for (ExchangeRate exchangeRate : exchangeRates) {
            if (exchangeRate.getCurrency() == currency
                    && exchangeRate.getKind() == kind && exchangeRate.isBest()) {
                return exchangeRate;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", exchangeRates=" + exchangeRates +
                '}';
    }
}
