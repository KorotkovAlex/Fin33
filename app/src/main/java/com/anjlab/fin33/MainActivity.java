package com.anjlab.fin33;



import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;
import com.anjlab.fin33.model.ViewPagerAdapter;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity implements BanksUpdatedListener {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    //LinearLayout llB;
    ViewPagerAdapter viewPagerAdapter;//view1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //llB =(LinearLayout) findViewById(R.id.llB);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

//        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));
        tabLayout.setupWithViewPager(viewPager);

//        llB = (LinearLayout) findViewById(R.id.llB);
//        llB.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//
//                ParseFin33Task mt = new ParseFin33Task(null);
//                mt.execute();
//
//            }
//        });
        AppState.getInstance().subscribe(this);

    }

    @Override
    protected void onDestroy() {
        AppState.getInstance().unsubscribe(this);
        super.onDestroy();
    }

    @Override
    public void onParseDone(List<Bank> banks) {

    }

    @Override
    public void onParseError(Throwable error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ошибка")
                .setMessage("Попробуйте включить интернет")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
