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
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addExchangeRate(ExchangeRate exchangeRate) {
        if (this.exchangeRates == null)
        {
            this.exchangeRates = new ArrayList<>();
        }
        this.exchangeRates.add(exchangeRate);
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public ExchangeRate getExchangeRates(ExchangeRate.Currency currency, ExchangeRate.Kind kind) {
        for (ExchangeRate exchangeRate : exchangeRates) {
            if(exchangeRate.getCurrency() == currency && exchangeRate.getKind() == kind){
                return  exchangeRate;
            }
        }
        return null;
    }
}
