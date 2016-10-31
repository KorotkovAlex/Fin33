package com.anjlab.fin33;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;
import com.crashlytics.android.Crashlytics;

import java.io.IOException;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class ErrorSplashActivity extends AppCompatActivity implements BanksUpdatedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_error_splash);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        TextView textView = (TextView) findViewById(R.id.textViewES);
        textView.setTypeface(font);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RelativeLayout rlR = (RelativeLayout) findViewById(R.id.relativeLayoutReset);
        RelativeLayout rlD = (RelativeLayout) findViewById(R.id.relativeLayoutDemo);
        rlR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ErrorSplashActivity.this, SplashScreenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK & Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);            }
        });
        rlD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppState.getInstance().getBanks().isEmpty()) {
                    try {
                        ParseFin33Task task = new ParseFin33Task(getAssets().open("fin33_16_09_2016.html"));
                        task.setDemo(true);
                        task.execute();
                    } catch (IOException e) {
                        Crashlytics.logException(new RuntimeException("This is a parseException"));
                        e.printStackTrace();
                    }
                } else {
                    onParseDone(AppState.getInstance().getBanks());
                }


            }
        });
    }

    @Override
    public void onParseDone(List<Bank> banks) {

    }

    @Override
    public void onParseError(Throwable error) {

    }
}
