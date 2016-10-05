package com.anjlab.fin33.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjlab.fin33.R;
import com.anjlab.fin33.model.ExchangeRate;


/**
 * Created by Саня on 22.09.2016.
 */
public class ExchangeRateView extends LinearLayout {
    private TextView textViewExchangeRate;
    private ImageView imageViewTrend;
    private ExchangeRate exchangeRate;

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
        textViewExchangeRate.setText("-0");
        imageViewTrend = (ImageView) this.findViewById(R.id.imageView1);
    }
    public void setExchangeRate(ExchangeRate exchangeRate){
        this.exchangeRate = exchangeRate;

        if (exchangeRate ==  null)
        {
            throw new RuntimeException("exchangeRate ==  null");
        } else {
            textViewExchangeRate.setText(exchangeRate.getPrice().toString());

            if (exchangeRate.getTrend() == ExchangeRate.Trend.UP) {
                imageViewTrend.setImageResource(R.mipmap.ic_up);
            } else if (exchangeRate.getTrend() == ExchangeRate.Trend.DOWN) {
                imageViewTrend.setImageResource(R.mipmap.ic_down);
            } else {
                //  TODO test
                imageViewTrend.setImageBitmap(null);
            }
        }
    }
}
