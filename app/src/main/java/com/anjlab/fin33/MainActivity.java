package com.anjlab.fin33;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.Fin33Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] myDataset;
    Context context;
    Document doc;
    Bank bank;
    List <Bank> banks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        try {
            //is=getAssets().open("fin33_16_09_2016.html");
//            File file = new File("file:///android_asset/6.html");
            doc = Jsoup.parse(getAssets().open("fin33_16_09_2016.html"), "windows-1251", "");
            banks = new Fin33Parser().parseMainInfo(doc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        myDataset = new String[2];
        myDataset[0] = "Доллар,USD";
        myDataset[1] = "Евро,EUR";
        mAdapter = new MyAdapter(banks,myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }
}
