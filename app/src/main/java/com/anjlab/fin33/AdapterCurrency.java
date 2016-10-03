package com.anjlab.fin33;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.ExchangeRate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Саня on 22.09.2016.
 */
public class AdapterCurrency extends RecyclerView.Adapter<AdapterCurrency.ViewHolder> {
    private final ExchangeRate.Currency currency;
    private List<Bank> banks;
    List<ExchangeRate> exchangeRates;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView4;
        public ImageView imageView1;
        public ImageView imageView2;
        public LinearLayout linearLayoutBuy;
        public LinearLayout linearLayoutSell;
        public TextView textViewPriceBuy;
        public TextView textViewPriceSell;
        public ViewHolder(View v) {
            super(v);
            textView4 =(TextView) v.findViewById(R.id.textView4);
            imageView1 =(ImageView) v.findViewById(R.id.imageView1);
            imageView2 =(ImageView) v.findViewById(R.id.imageView2);
            linearLayoutBuy = (LinearLayout) v.findViewById(R.id.linearLayoutBuy);
            linearLayoutSell = (LinearLayout) v.findViewById(R.id.linearLayoutSell);
            textViewPriceBuy =(TextView) v.findViewById(R.id.textViewPriceBuy);
            textViewPriceSell =(TextView) v.findViewById(R.id.textViewPriceSell);
        }


    }
    public AdapterCurrency(List<Bank> banks, ExchangeRate.Currency currency) {
        this.currency = currency;
        this.banks = banks;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view_currency, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
                Bank bank = banks.get(position);
                Log.d("position","" + position);
                Log.d("banks.get(position)","" + bank.getName());

                ExchangeRate sellRate = bank.getExchangeRates(currency,ExchangeRate.Kind.SELL);
                ExchangeRate buyRate = bank.getExchangeRates(currency,ExchangeRate.Kind.BUY);

                Log.d("sellRate.isBest()","" + sellRate.isBest());
                if(sellRate.isBest()){
                    holder.linearLayoutSell.setBackgroundResource(R.color.colorBest);
                }
                if(buyRate.isBest()){
                    holder.linearLayoutBuy.setBackgroundResource(R.color.colorBest);
                }
                    holder.textViewPriceBuy.setText("" + buyRate.getPrice());
                    holder.textView4.setText("" + bank.getName());
                    if(sellRate.getTrend() == ExchangeRate.Trend.UP) {
                        holder.imageView1.setImageResource(R.mipmap.ic_up);
                    }else if(sellRate.getTrend() == ExchangeRate.Trend.DOWN){
                        holder.imageView1.setImageResource(R.mipmap.ic_down);
                    }
                   holder.textViewPriceSell.setText("" + sellRate.getPrice());
                    if(buyRate.getTrend() == ExchangeRate.Trend.UP) {
                        holder.imageView2.setImageResource(R.mipmap.ic_up);
                    }else if(buyRate.getTrend() == ExchangeRate.Trend.DOWN){
                        holder.imageView2.setImageResource(R.mipmap.ic_down);
                    }
    }
    @Override
    public int getItemCount() {
        return banks.size();
    }
}
