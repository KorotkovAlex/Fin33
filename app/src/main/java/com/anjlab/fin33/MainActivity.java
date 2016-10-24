package com.anjlab.fin33;



import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.anjlab.fin33.model.ViewPagerAdapter;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout llB;
    ViewPagerAdapter viewPagerAdapter;//view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        //toolbar.setTitleTextColor();
      // setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        llB =(LinearLayout) findViewById(R.id.llB);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));
        tabLayout.setupWithViewPager(viewPager);

        llB = (LinearLayout) findViewById(R.id.llB);
        llB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                MyTask mt = null;
                try {
                    mt = new MyTask(getAssets().open("fin33_16_09_2016.html"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mt.execute();

            }
        });

    }

}
