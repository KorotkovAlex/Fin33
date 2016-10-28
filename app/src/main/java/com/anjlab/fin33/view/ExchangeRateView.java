package com.anjlab.fin33.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjlab.fin33.R;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.ExchangeRate;


/**
 * Created by Саня on 22.09.2016.
 */
public class ExchangeRateView extends LinearLayout {
    private TextView textViewExchangeRate;
    private TextView textViewBest;
    private TextView textViewDA;
    private ExchangeRate exchangeRate;
    public RelativeLayout relative;
    private TextView textViewDate;
   // public LinearLayout linearLayoutSell;

    public ExchangeRateView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.exchange_rate_view, this);
    }

    public ExchangeRateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public ExchangeRateView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ExchangeRateView, 0, 0);
        LayoutInflater.from(context).inflate(R.layout.exchange_rate_view, this);
        textViewExchangeRate = (TextView) this.findViewById(R.id.textViewExchangeRate);
        textViewExchangeRate.setText("Данные отсутствуют");
        textViewDA = (TextView) this.findViewById(R.id.textViewDA);
        //imageViewBest = (ImageView) findViewById(R.id.imageView2);
        relative = (RelativeLayout) this.findViewById(R.id.relative);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fontawesome-webfont.ttf" );
        textViewDA.setTypeface(font);
        //linearLayoutSell = (LinearLayout) this.findViewById(R.id.linearLayoutSell);

    }
    public void setExchangeRate(ExchangeRate exchangeRate){

        this.exchangeRate = exchangeRate;

        if (exchangeRate ==  null)
        {
            //throw new RuntimeException("exchangeRate ==  null");
            textViewExchangeRate.setText("Данные отсутствуют");
        } else {
            Log.d("ssss",exchangeRate.getPrice().toString());
            textViewExchangeRate.setText(exchangeRate.getPrice().toString());
            Bank bank = exchangeRate.getBank();
            bank.getName();

            if (exchangeRate.getTrend() == ExchangeRate.Trend.UP) {
                textViewDA.setText(R.string.icon_up);
                textViewDA.setTextColor(getResources().getColor(R.color.colorUp));
            } else if (exchangeRate.getTrend() == ExchangeRate.Trend.DOWN) {
                textViewDA.setText(R.string.icon_down);
                textViewDA.setTextColor(getResources().getColor(R.color.colorDown));

            } else {
                //  TODO test
                textViewDA.setText(null);
            }
        }
    }
}
