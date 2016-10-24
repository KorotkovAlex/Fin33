package com.anjlab.fin33;

import android.os.AsyncTask;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;
import com.anjlab.fin33.model.Fin33Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Саня on 21.10.2016.
 */
class MyTask extends AsyncTask<Void, Void, Void> {
    Document doc;
    private InputStream input;
    public MyTask(InputStream open) {
        this.input = open;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
           doc = Jsoup.parse(input, "windows-1251", "");
           // doc = Jsoup.connect("http://www.fin33.ru/").get();

        } catch (Exception e) {
            throw new RuntimeException("Error", e);

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        try {
            new Fin33Parser().parseMainInfo(doc, new BanksUpdatedListener() {
                @Override
                public void onParseDone(List<Bank> banks) {
                    AppState.getInstance().updateBanks(banks);
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
