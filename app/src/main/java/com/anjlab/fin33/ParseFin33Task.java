package com.anjlab.fin33;

import android.os.AsyncTask;
import android.util.Log;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;
import com.anjlab.fin33.model.ExchangeRate;
import com.anjlab.fin33.model.Fin33Parser;
import com.crashlytics.android.Crashlytics;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Саня on 21.10.2016.
 */
class ParseFin33Task extends AsyncTask<Void, Void, Void>  {

    private InputStream input;
    private List<Bank> banks;
    String url = "http://www.fin33.ru/";
    private Throwable error;
    private boolean demo;
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
            if(input == null) {
                Log.d("Input null","NULL");
                doc = Jsoup.connect(url).get();
            }else {
                Log.d("Input ss","ssss");
                doc = Jsoup.parse(input, "windows-1251", "");
            }

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
            Crashlytics.logException(new RuntimeException("Error"));
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (error != null) {
            Log.d("AppState.getInstance().parseError(error);","AppState.getInstance().parseError(error);");
            AppState.getInstance().parseError(error);
        }
        else if (banks != null) {
            for (Bank bank : banks) {
                for (ExchangeRate exchangeRate : bank.getExchangeRates()) {
                    exchangeRate.setDemo(demo);
                }
            }
            AppState.getInstance().updateBanks(banks);
        }
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }
    public boolean getDemo(){
        return demo;
    }
}
