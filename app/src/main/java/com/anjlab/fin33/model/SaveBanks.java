package com.anjlab.fin33.model;

import android.support.annotation.NonNull;

import org.jsoup.nodes.Document;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Саня on 05.10.2016.
 */
public class SaveBanks {

    List<Bank> banks;
    public void getBanks(final BanksUpdatedListener listener) throws ParseException {


        final List<Bank> banks = new ArrayList<>();
        for (int i = 0;  i < 16; i++) {
            Bank bank = new Bank();
            bank.setName("Имя банка"); // Запоминаем  имя банка
            Date docDate = null;
                String timeStr = "14:32";
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                Calendar calendar = new GregorianCalendar();
                int yyyy = calendar.get(Calendar.YEAR);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                int mm = calendar.get(Calendar.MONTH )+1;
                try {
                    docDate = format.parse(dd + "." + mm + "." + yyyy + " "  + timeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            bank.addExchangeRate(createExchangeRateFrom(ExchangeRate.Kind.BUY, ExchangeRate.Currency.USD, docDate, bank));
            bank.addExchangeRate(createExchangeRateFrom(ExchangeRate.Kind.SELL, ExchangeRate.Currency.USD, docDate, bank));
            bank.addExchangeRate(createExchangeRateFrom(ExchangeRate.Kind.BUY, ExchangeRate.Currency.EUR, docDate, bank));
            bank.addExchangeRate(createExchangeRateFrom(ExchangeRate.Kind.SELL, ExchangeRate.Currency.EUR, docDate, bank));
            banks.add(bank);

        }

        listener.onParseDone(banks);
    }
    public ExchangeRate createExchangeRateFrom(ExchangeRate.Kind kind, ExchangeRate.Currency currency, Date docDate, Bank bank){
        ExchangeRate exchangeRate = new ExchangeRate();
        String str= "77.0000";
        BigDecimal bd=new BigDecimal(str);
        exchangeRate.setTrend(ExchangeRate.Trend.NONE);

        exchangeRate.setBest(true);
        exchangeRate.setKind(kind);
        exchangeRate.setCurrency(currency);
        exchangeRate.setPrice(bd);
        exchangeRate.setDate(docDate);
        exchangeRate.setBank(bank);
        return exchangeRate;
    }

//        };
//        downloadThread.start();
    //   }
}
