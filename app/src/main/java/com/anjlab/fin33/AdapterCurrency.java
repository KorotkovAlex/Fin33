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
import com.anjlab.fin33.view.ExchangeRateView;

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

        public LinearLayout linearLayoutBuy;
        public LinearLayout linearLayoutSell;
        public ExchangeRateView ervBuy;
        public ExchangeRateView ervSell;
        public ViewHolder(View v) {
            super(v);
            ervBuy =(ExchangeRateView) v.findViewById(R.id.ervBuy);
            ervSell =(ExchangeRateView) v.findViewById(R.id.ervSell);
            linearLayoutBuy = (LinearLayout) v.findViewById(R.id.linearLayoutBuy);
            linearLayoutSell = (LinearLayout) v.findViewById(R.id.linearLayoutSell);
            //textViewExchangeRate =(TextView) v.findViewById(R.id.textViewExchangeRate);
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
                holder.ervBuy.setExchangeRate(buyRate);
                holder.ervSell.setExchangeRate(sellRate);
        
                Log.d("sellRate.isBest()","" + sellRate.isBest());
                if(sellRate.isBest()){
                    holder.linearLayoutSell.setBackgroundResource(R.color.colorBest);
                }
                if(buyRate.isBest()){
                    holder.linearLayoutBuy.setBackgroundResource(R.color.colorBest);
                }

    }
    @Override
    public int getItemCount() {
        return banks.size();
    }
}
