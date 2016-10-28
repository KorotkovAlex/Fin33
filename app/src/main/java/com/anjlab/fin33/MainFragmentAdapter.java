package com.anjlab.fin33;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.ExchangeRate;
import com.anjlab.fin33.view.ExchangeRateView;

import java.text.ParseException;
import java.util.List;

public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.ViewHolder> {
    private ExchangeRate.Currency[] currencies;
    private List<Bank> banks;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public TextView textView4;
        public TextView textView3;
        private TextView textViewDateBuy;
        private TextView textViewDateSell;
        public ExchangeRateView ervBuy;
        public ExchangeRateView ervSell;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.currency);
            textView4 = (TextView) v.findViewById(R.id.textView4);
            textView3 = (TextView) v.findViewById(R.id.textView3);
            textViewDateBuy = (TextView) v.findViewById(R.id.textViewDateBuy);
            textViewDateSell = (TextView) v.findViewById(R.id.textViewDateSell);
            ervBuy =(ExchangeRateView) v.findViewById(R.id.ervBuy);
            ervSell =(ExchangeRateView) v.findViewById(R.id.ervSell);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainFragmentAdapter(List<Bank> banks, ExchangeRate.Currency[] currencies) {
        this.banks = banks;
        this.currencies = currencies;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ExchangeRate.Currency currency = currencies[position];// ExchangeRate.Currency.USD;
        holder.mTextView.setText(getTitle(currency));
        Bank bank = new Bank();

        //

        holder.ervBuy.setExchangeRate(Bank.findBestRate(banks, currency, ExchangeRate.Kind.BUY));
        ExchangeRate exchange = Bank.findBestRate(banks, currency, ExchangeRate.Kind.BUY);
        if (exchange !=  null) {
            bank = exchange.getBank();

                try {
                    holder.textViewDateBuy.setText("" + exchange.getFormattedDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            holder.textView3.setText(bank.getName());

            holder.ervSell.setExchangeRate(Bank.findBestRate(banks, currency, ExchangeRate.Kind.SELL));
            exchange = Bank.findBestRate(banks, currency, ExchangeRate.Kind.SELL);


            try {
                holder.textViewDateSell.setText("" + exchange.getFormattedDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

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

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return currencies.length;
    }
}