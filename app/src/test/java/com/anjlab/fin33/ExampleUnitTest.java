package com.anjlab.fin33;

import android.util.Log;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;
import com.anjlab.fin33.model.Fin33BestExchangeRateParser;
import com.anjlab.fin33.model.Fin33Parser;
import com.anjlab.fin33.model.Value;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    String url  = "http://www.fin33.ru/";
    Document doc;
    @Test
    public void addition_isCorrect() throws Exception {

        doc = Jsoup.connect(url).get();

        new Fin33Parser().parseMainInfo(doc, new BanksUpdatedListener() {
            @Override
            public void onParseDone(List<Bank> banks) {}

            @Override
            public void onParseError(Throwable error) {}
        });

    }
    @Test
    public void staticsExchangeRate() throws IOException {
        Fin33BestExchangeRateParser fin33BestExchangeRateParser =
                new Fin33BestExchangeRateParser("http://www.fin33.ru/best_eur.txt",10,"&values_3=");
        List<Value> values = new ArrayList<>();
        values.addAll(fin33BestExchangeRateParser.executeHttpGet());
        for (Value value: values) {
            System.out.println("getValue = " + value.getValue() + " getDate = " +  value.getDate());
        }

        Assert.assertEquals(14,values.size());
    }
}