package com.anjlab.fin33;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.ExchangeRate;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;
    private String[] usdPrice;
    private String[] eurPrice;
    private List<Bank> banks;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;
        public TextView textView6;
        public ImageView imageView1;
        public ImageView imageView2;
        public ViewHolder(View v) {
            super(v);
            mTextView =(TextView) v.findViewById(R.id.currency);
            textView3 =(TextView) v.findViewById(R.id.textView3);
            textView4 =(TextView) v.findViewById(R.id.textView4);
            textView5 =(TextView) v.findViewById(R.id.textView5);
            textView6 =(TextView) v.findViewById(R.id.textView6);
            imageView1 =(ImageView) v.findViewById(R.id.imageView1);
            imageView2 =(ImageView) v.findViewById(R.id.imageView2);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Bank> banks, String[] mDataset) {
        this.banks = banks;
        this.mDataset = mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,   int viewType) {
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

        holder.mTextView.setText(mDataset[position]);

        if(position == 0) {
            for (int i = 0; banks.size() > i; i++) {
                Bank bank = banks.get(i);
                List<ExchangeRate> exchangeRates;
                exchangeRates = bank.getExchangeRates();
                if (exchangeRates.get(0).isBest()) {
                    holder.textView5.setText("" + exchangeRates.get(0).getPrice());
                    holder.textView3.setText("" + bank.getName());
                    if(exchangeRates.get(0).getTrend() == ExchangeRate.Trend.UP) {
                        holder.imageView1.setImageResource(R.mipmap.ic_up);
                    }else if(exchangeRates.get(0).getTrend() == ExchangeRate.Trend.DOWN){
                        holder.imageView1.setImageResource(R.mipmap.ic_down);
                    }

                }
                if (exchangeRates.get(1).isBest()) {
                    holder.textView6.setText("" + exchangeRates.get(1).getPrice());
                    holder.textView4.setText("" + bank.getName());
                    if(exchangeRates.get(1).getTrend() == ExchangeRate.Trend.UP) {
                        holder.imageView1.setImageResource(R.mipmap.ic_up);
                    }else if(exchangeRates.get(1).getTrend() == ExchangeRate.Trend.DOWN){
                        holder.imageView1.setImageResource(R.mipmap.ic_down);
                    }
                }
            }
        }
        if(position == 1) {
            for (int i = 0; banks.size() > i; i++) {
                Bank bank = banks.get(i);
                List<ExchangeRate> exchangeRates;
                exchangeRates = bank.getExchangeRates();
                if (exchangeRates.get(2).isBest()) {
                    holder.textView5.setText("" + exchangeRates.get(2).getPrice());
                    holder.textView3.setText("" + bank.getName());
                    if(exchangeRates.get(2).getTrend() == ExchangeRate.Trend.UP) {
                        holder.imageView1.setImageResource(R.mipmap.ic_up);
                    }else if(exchangeRates.get(2).getTrend() == ExchangeRate.Trend.DOWN){
                        holder.imageView1.setImageResource(R.mipmap.ic_down);
                    }

                }
                if (exchangeRates.get(3).isBest()) {
                    holder.textView6.setText("" + exchangeRates.get(3).getPrice());
                    holder.textView4.setText("" + bank.getName());
                    if(exchangeRates.get(3).getTrend() == ExchangeRate.Trend.UP) {
                        holder.imageView1.setImageResource(R.mipmap.ic_up);
                    }else if(exchangeRates.get(3).getTrend() == ExchangeRate.Trend.DOWN){
                        holder.imageView1.setImageResource(R.mipmap.ic_down);
                    }
                }
            }
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}