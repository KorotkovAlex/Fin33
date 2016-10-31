package com.anjlab.fin33;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity implements BanksUpdatedListener {
    TextView textViewSS;
    TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash_screen);
        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        textViewSS = (TextView) findViewById(R.id.textViewSS);
        textViewSS.setTypeface(font);

        textViewSS.setText(R.string.icon_refresh);
        textView6 = (TextView) findViewById(R.id.textView6);
        final Animation animationRotateCenter = AnimationUtils.loadAnimation(this, R.anim.rotate);
        textViewSS.startAnimation(animationRotateCenter);
        AppState.getInstance().subscribe(this);
        ParseFin33Task mt = new ParseFin33Task(null);
        mt.execute();

    }

    @Override
    protected void onDestroy() {
        AppState.getInstance().unsubscribe(this);
        super.onDestroy();
    }

    @Override
    public void onParseDone(List<Bank> banks) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK & Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
    }

    @Override
    public void onParseError(Throwable error) {
        //TODO
        Intent intent = new Intent(this, ErrorSplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK & Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);


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
