package com.anjlab.fin33.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 11/21/16.
 */

public class Fin33BestExchangeRateParser {

    private String uri;
    private List<Value> values;
    private int lastNumber;
    private String name;

    public Fin33BestExchangeRateParser(String uri,int lastNumber, String name) {
        this.uri = uri;
        this.lastNumber = lastNumber;
        this.name = name;
    }

    public List<Value> executeHttpGet() throws IOException {
        URL url = new URL(uri);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        values = new ArrayList<Value>();
        String str;
        String substr;
        while ((str = in.readLine()) != null) {
            substr = str.substring(0,10);
            if (substr.equals("&x_labels=")) {
                substr = str.substring(10,str.length()-1);
                String[] strSplit = substr.split(",");
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                for (int i = 0; i < strSplit.length; i++){
                    Value value = new Value();
                    try {
                        value.setDate(format.parse(strSplit[i]));
                        Log.d("value.getdate", " " + value.getDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    values.add(value);
                }
                break;
            }
        }
        in = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((str = in.readLine()) != null)
        {
            substr = str.substring(0,lastNumber);
            if (substr.equals(name)) {//"&values_3="
                substr = str.substring(lastNumber,str.length()-1);
                String[] strSplit = substr.split(",");
                for (int i = 0; i < strSplit.length; i++){
                    Value value = values.get(i);
                    value.setValue(new BigDecimal(strSplit[i]));
                }
                return values;
            }
        }
        in.close();
        return null;

    }
}
