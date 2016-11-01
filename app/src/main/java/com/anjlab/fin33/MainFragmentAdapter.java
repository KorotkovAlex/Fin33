package com.anjlab.fin33;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.ExchangeRate;
import com.anjlab.fin33.view.ExchangeRateView;

import java.util.List;

public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.ViewHolder> {

    private ExchangeRate.Currency[] currencies;
    private List<Bank> banks;

    public MainFragmentAdapter(List<Bank> banks, ExchangeRate.Currency[] currencies) {
        this.banks = banks;
        this.currencies = currencies;
    }

    @Override
    public MainFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExchangeRate.Currency currency = currencies[position];// ExchangeRate.Currency.USD;
        holder.mTextView.setText(getTitle(currency));
        Bank bank = new Bank();
        holder.ervBuy.setExchangeRate(Bank.findBestRate(banks, currency, ExchangeRate.Kind.BUY));
        ExchangeRate exchange = Bank.findBestRate(banks, currency, ExchangeRate.Kind.BUY);
        if (exchange != null) {
            bank = exchange.getBank();
            holder.textViewDateBuy.setText("" + exchange.getFormattedDate());
            holder.textView3.setText(bank.getName());
            holder.ervSell.setExchangeRate(Bank.findBestRate(banks, currency, ExchangeRate.Kind.SELL));
            exchange = Bank.findBestRate(banks, currency, ExchangeRate.Kind.SELL);
            holder.textViewDateSell.setText("" + exchange.getFormattedDate());
            bank = exchange.getBank();
            holder.textView4.setText(bank.getName());
        }
    }

    private String getTitle(ExchangeRate.Currency currency) {
        switch (currency) {
            case USD:
                return "USD, лучший курс ";
            case EUR:
                return "EUR, лучший курс ";
            default:
                return "?";
        }
    }

    @Override
    public int getItemCount() {
        return currencies.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView textView4;
        public TextView textView3;
        public ExchangeRateView ervBuy;
        public ExchangeRateView ervSell;
        private TextView textViewDateBuy;
        private TextView textViewDateSell;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.currency);
            textView4 = (TextView) v.findViewById(R.id.textView4);
            textView3 = (TextView) v.findViewById(R.id.textView3);
            textViewDateBuy = (TextView) v.findViewById(R.id.textViewDateBuy);
            textViewDateSell = (TextView) v.findViewById(R.id.textViewDateSell);
            ervBuy = (ExchangeRateView) v.findViewById(R.id.ervBuy);
            ervSell = (ExchangeRateView) v.findViewById(R.id.ervSell);
        }
    }
}