package com.anjlab.fin33.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjlab.fin33.R;


/**
 * Created by Саня on 22.09.2016.
 */
public class PriceBuyView extends LinearLayout {
    private TextView textViewPriceBuy;
    private ImageView imageView1;

    public PriceBuyView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.price_buy_view, this);
    }

    public PriceBuyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public PriceBuyView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.PriceBuyView, 0, 0);
        LayoutInflater.from(context).inflate(R.layout.price_buy_view, this);
        textViewPriceBuy = (TextView) this.findViewById(R.id.textViewPriceBuy);
        textViewPriceBuy.setText("Test");
        imageView1 = (ImageView) this.findViewById(R.id.imageView1);

    }
}
