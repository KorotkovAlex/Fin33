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
 * Created by Саня on 26.09.2016.
 */
public class PriceSellView extends LinearLayout {
    private TextView textViewPriceSell;
    private ImageView imageView2;

    public PriceSellView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.price_sell_view, this);
    }

    public PriceSellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public PriceSellView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.PriceBuyView, 0, 0);
        LayoutInflater.from(context).inflate(R.layout.price_sell_view, this);
        textViewPriceSell = (TextView) this.findViewById(R.id.textViewPriceSell);
        textViewPriceSell.setText("Test");
        imageView2 = (ImageView) this.findViewById(R.id.imageView2);

    }
}
