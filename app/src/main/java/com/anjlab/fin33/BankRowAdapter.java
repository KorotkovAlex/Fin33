package com.anjlab.fin33;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.ExchangeRate;
import com.anjlab.fin33.view.ExchangeRateView;

import java.util.List;

/**
 * Created by Саня on 22.09.2016.
 */
public class BankRowAdapter extends RecyclerView.Adapter<BankRowAdapter.ViewHolder> {
    private final ExchangeRate.Currency currency;
    private List<Bank> banks;
    List<ExchangeRate> exchangeRates;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayoutBuy;
        public RelativeLayout relativeLayoutSell;
        public ExchangeRateView ervBuy;
        public ExchangeRateView ervSell;
        public TextView tvNameBank;
        public ImageView imageViewBestBuy;
        public ImageView imageViewBestSell;
        public ViewHolder(View v) {
            super(v);
            ervBuy =(ExchangeRateView) v.findViewById(R.id.ervBuy);
            ervSell =(ExchangeRateView) v.findViewById(R.id.ervSell);
            tvNameBank = (TextView) v.findViewById(R.id.tvNameBank);
            relativeLayoutBuy = (RelativeLayout) v.findViewById(R.id.relativeLayoutBuy);
            relativeLayoutSell = (RelativeLayout) v.findViewById(R.id.relativeLayoutSell);
            imageViewBestBuy = (ImageView) v.findViewById(R.id.imageView2);
            imageViewBestSell = (ImageView) v.findViewById(R.id.imageView3);
        }


    }
    public BankRowAdapter(List<Bank> banks, ExchangeRate.Currency currency) {
        this.currency = currency;
        this.banks = banks;
        Bank.findBestRate(banks,currency,ExchangeRate.Kind.SELL);
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
                holder.tvNameBank.setText(bank.getName());
                holder.ervBuy.setExchangeRate(buyRate);
                holder.ervSell.setExchangeRate(sellRate);
        ExchangeRate exchangeRate = bank.getExchangeRates(currency,ExchangeRate.Kind.BUY);
        if(exchangeRate.isBest()){
            //relative.setBackgroundResource(R.color.colorBest);
            holder.imageViewBestBuy.setImageResource(R.mipmap.ic_best);
        } else {
            //relative.setBackgroundResource(R.color.colorNonBest);
            holder.imageViewBestBuy.setImageDrawable(null);
        }
        exchangeRate = bank.getExchangeRates(currency,ExchangeRate.Kind.SELL);
        if(exchangeRate.isBest()){
            //relative.setBackgroundResource(R.color.colorBest);
            holder.imageViewBestSell.setImageResource(R.mipmap.ic_best);
        } else {
            //relative.setBackgroundResource(R.color.colorNonBest);
            holder.imageViewBestSell.setImageDrawable(null);
        }

    }
    @Override
    public int getItemCount() {
        return banks.size();
    }
}
