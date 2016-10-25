package com.anjlab.fin33;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;
import com.anjlab.fin33.model.Fin33Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.text.ParseException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Саня on 21.10.2016.
 */
class ParseFin33Task extends AsyncTask<Void, Void, Void>  {

    private InputStream input;
    private List<Bank> banks;
    String url = "http://www.fin33.ru/";

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
                doc = Jsoup.connect(url).get();
            }else {
                doc = Jsoup.parse(input, "windows-1251", "");
            }
            //

            new Fin33Parser().parseMainInfo(doc, new BanksUpdatedListener() {
                @Override
                public void onParseDone(List<Bank> banks) {
                    ParseFin33Task.this.banks = banks;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Error", e);

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
            if(banks != null) {
                AppState.getInstance().updateBanks(banks);
            }
    }

}
