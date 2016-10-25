package com.anjlab.fin33;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;

import java.io.IOException;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity implements BanksUpdatedListener {
    ImageView imageView;
    TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView)findViewById(R.id.imageViewSS);
        textView6 = (TextView) findViewById(R.id.textView6);
        final Animation animationRotateCenter = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(animationRotateCenter);
        AppState.getInstance().subscribe(this);
        if(AppState.getInstance().getBanks().isEmpty()) {
                try {
                    new ParseFin33Task(getAssets().open("fin33_16_09_2016.html"))
                    .execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        else
        {
            onParseDone(AppState.getInstance().getBanks());
        }
    }

    @Override
    protected void onDestroy() {
        AppState.getInstance().unsubscribe(this);
        super.onDestroy();
    }

    @Override
    public void onParseDone(List<Bank> banks) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
    }

    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }

}
