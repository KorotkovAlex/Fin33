package com.anjlab.fin33.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Саня on 21.10.2016.
 */
public class AppState {
    private static AppState appState = new AppState( );

    private List<Bank> banks = new ArrayList<>();
    private List<BanksUpdatedListener> subscribers = new ArrayList<>();

    /* Static 'instance' method */
    public AppState(){
//        Bank bank = new Bank();
//        bank.setName("Name Bank");
//        bank.addExchangeRate(createExchangeRateFrom(ExchangeRate.Kind.BUY, ExchangeRate.Currency.USD, bank));
//        bank.addExchangeRate(createExchangeRateFrom(ExchangeRate.Kind.SELL, ExchangeRate.Currency.USD, bank));
//        bank.addExchangeRate(createExchangeRateFrom(ExchangeRate.Kind.BUY, ExchangeRate.Currency.EUR, bank));
//        bank.addExchangeRate(createExchangeRateFrom(ExchangeRate.Kind.SELL, ExchangeRate.Currency.EUR, bank));
//        banks.add(bank);
    }
    public ExchangeRate createExchangeRateFrom(ExchangeRate.Kind kind, ExchangeRate.Currency currency,Bank bank){
        ExchangeRate exchangeRate = new ExchangeRate();
        String str= "77.0000";
        BigDecimal bd=new BigDecimal(str);
        exchangeRate.setTrend(ExchangeRate.Trend.NONE);
        exchangeRate.setBest(true);
        exchangeRate.setKind(kind);
        exchangeRate.setCurrency(currency);
        exchangeRate.setPrice(bd);
        exchangeRate.setBank(bank);
        return exchangeRate;
    }
    public static AppState getInstance() {
        return appState;
    }

    public void updateBanks(List<Bank> newBanks){

        this.banks = newBanks;
        notifySubscribes();
    }
    public List<Bank> getBanks(){

        return banks;
    }
    public void subscribe(BanksUpdatedListener listener){
        subscribers.add(listener);
    }
    public void notifySubscribes(){
        for (BanksUpdatedListener subscriber : subscribers) {
            subscriber.onParseDone(banks);
        }
    }

}
