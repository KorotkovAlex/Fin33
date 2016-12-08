package com.anjlab.fin33;

import android.graphics.Color;
import android.os.AsyncTask;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;
import com.anjlab.fin33.model.ExchangeRate;
import com.anjlab.fin33.model.Fin33BestExchangeRateParser;
import com.anjlab.fin33.model.Fin33Parser;
import com.anjlab.fin33.model.TimeSeries;
import com.anjlab.fin33.model.Value;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Саня on 21.10.2016.
 */
class ParseFin33Task extends AsyncTask<Void, Void, Void> {

    String url = "http://www.fin33.ru/";
    private InputStream input;
    private List<Bank> banks;
    private Throwable error;
    private boolean demo;
    private TimeSeries timeSeries;
    private Map<Integer, List<TimeSeries>> timeSeriesMap = new HashMap<>();

    public ParseFin33Task(InputStream input) {
        this.input = input;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {

            Document doc;
            if (input == null) {
                doc = Jsoup.connect(url).get();
                graph();
            } else {
                doc = Jsoup.parse(input, "windows-1251", "");
            }
            //new Fin33BestExchangeRateParser("http://www.fin33.ru/best_eur.txt").executeHttpGet();
            new Fin33Parser().parseMainInfo(doc, new BanksUpdatedListener() {
                @Override
                public void onParseDone(List<Bank> banks) {
                    ParseFin33Task.this.banks = banks;
                }

                @Override
                public void onParseError(Throwable error) {

                }
            });
        } catch (Exception e) {
            this.error = e;
        }
        return null;
    }



    @Override
    protected void onPostExecute(Void result) {
        if (error != null) {
            AppState.getInstance().parseError(error);
        } else if (banks != null) {
            for (Bank bank : banks) {
                for (ExchangeRate exchangeRate : bank.getExchangeRates()) {
                    exchangeRate.setDemo(demo);
                }
            }
            AppState.getInstance().updateBanks(banks);
            AppState.getInstance().updateGraph(timeSeriesMap);
        }
    }

    public void graph() throws IOException {
        Color color = new Color();
        List<TimeSeries> timeSeriesList = new ArrayList<>();
        timeSeries = new TimeSeries();
        timeSeries.setColor(color.argb(255,247,90,19));
        timeSeries.setTitle("Покупка");
        timeSeries.setValues(new Fin33BestExchangeRateParser("http://www.fin33.ru/best_eur.txt",8,
                    "&values=").executeHttpGet());
        timeSeriesList.add(timeSeries);

        timeSeries = new TimeSeries();
        timeSeries.setColor(color.argb(255,9,128,83));
        timeSeries.setTitle("Продажа");
        timeSeries.setValues(new Fin33BestExchangeRateParser("http://www.fin33.ru/best_eur.txt",10,
                "&values_2=").executeHttpGet());
        timeSeriesList.add(timeSeries);

        timeSeries = new TimeSeries();
        timeSeries.setColor(color.argb(255,69,102,214));
        timeSeries.setTitle("Курс ЦБ");
        timeSeries.setValues(new Fin33BestExchangeRateParser("http://www.fin33.ru/best_eur.txt",10,
                "&values_3=").executeHttpGet());
        timeSeriesList.add(timeSeries);

        timeSeriesMap.put(1,timeSeriesList);

        timeSeriesList = new ArrayList<>();
        timeSeries = new TimeSeries();
        timeSeries.setColor(color.argb(255,247,90,19));
        timeSeries.setTitle("Покупка");
        timeSeries.setValues(new Fin33BestExchangeRateParser("http://www.fin33.ru/best_usd.txt",8,
                "&values=").executeHttpGet());
        timeSeriesList.add(timeSeries);

        timeSeries = new TimeSeries();
        timeSeries.setColor(color.argb(255,9,128,83));
        timeSeries.setTitle("Продажа");
        timeSeries.setValues(new Fin33BestExchangeRateParser("http://www.fin33.ru/best_usd.txt",10,
                "&values_2=").executeHttpGet());
        timeSeriesList.add(timeSeries);

        timeSeries = new TimeSeries();
        timeSeries.setColor(color.argb(255,69,102,214));
        timeSeries.setTitle("Курс ЦБ");
        timeSeries.setValues(new Fin33BestExchangeRateParser("http://www.fin33.ru/best_usd.txt",10,
                "&values_3=").executeHttpGet());
        timeSeriesList.add(timeSeries);

        timeSeriesMap.put(0,timeSeriesList);
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }
}
