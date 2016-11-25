package com.anjlab.fin33;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.ExchangeRate;
import com.anjlab.fin33.model.Visibility;
import com.anjlab.fin33.view.ExchangeRateView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Саня on 22.09.2016.
 */
public class BankRowAdapter extends RecyclerView.Adapter<BankRowAdapter.ViewHolder> {

    private final ExchangeRate.Currency currency;
    private List<Bank> banks;
    private int colorBest;
    private List <Visibility> visibilities;
    Visibility visibility;
    View v;
    public BankRowAdapter(List<Bank> banks, ExchangeRate.Currency currency) {
        visibilities = new ArrayList<Visibility>();

        this.currency = currency;
        this.banks = banks;
        for (Bank bank:banks) {
            visibility = new Visibility();
            visibility.setBank(bank);
            visibility.setVisibility(false);
            this.visibilities.add(visibility);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       this.v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_view_currency, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bank bank = banks.get(position);
        List<ExchangeRate> exchangeRates = bank.getExchangeRates();
        ExchangeRate exchangeRateDate = exchangeRates.get(0);
        ExchangeRate buyRate = bank.getExchangeRates(currency, ExchangeRate.Kind.BUY);
        ExchangeRate sellRate = bank.getExchangeRates(currency, ExchangeRate.Kind.SELL);

        holder.tvNameBank.setText(bank.getName());
        holder.textViewDate.setText(exchangeRateDate.getFormattedDate());

        holder.ervBuy.setExchangeRate(buyRate);
        holder.ervSell.setExchangeRate(sellRate);
        //TODO ???? sell
        if (sellRate.isBest()) {
            holder.textViewBestBuy.setText(R.string.icon_best);
            holder.textViewBestBuy.setTextColor(colorBest);
        } else {
            holder.textViewBestBuy.setText(null);
        }
        //TODO ???? buy
        if (buyRate.isBest()) {
            holder.textViewBestSell.setText(R.string.icon_best);
            holder.textViewBestSell.setTextColor(colorBest);
        } else {
            holder.textViewBestSell.setText(null);
        }
        Visibility visibility = visibilities.get(position);
        if(visibility.isVisibility()){
            holder.linearLayoutTest.setVisibility(View.VISIBLE);
            final Animation animationRotateCenter = AnimationUtils.loadAnimation(v.getContext(), R.anim.angle);
            animationRotateCenter.setFillAfter(true);
            holder.textViewAngle.startAnimation(animationRotateCenter);
        } else {
            holder.linearLayoutTest.setVisibility(View.GONE);
            final Animation animationRotateCenter = AnimationUtils.loadAnimation(v.getContext(), R.anim.angle_turn);
            animationRotateCenter.setFillAfter(true);
            holder.textViewAngle.startAnimation(animationRotateCenter);
        }

    }


    @Override
    public int getItemCount() {
        return banks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ExchangeRateView ervBuy;
        private ExchangeRateView ervSell;
        private TextView tvNameBank;
        private TextView textViewBestBuy;
        private TextView textViewBestSell;
        private TextView textViewDate;
        private CardView cardView;
        private TextView textViewAngle;
        private LinearLayout linearLayoutTest;
        private LinearLayout linearLayoutLink;
        private TextView textViewLink;

        public ViewHolder(View v) {
            super(v);
            linearLayoutTest = (LinearLayout) v.findViewById(R.id.linearLayoutTest) ;
            colorBest = v.getResources().getColor(R.color.colorBest);
            ervBuy = (ExchangeRateView) v.findViewById(R.id.ervBuy);
            ervSell = (ExchangeRateView) v.findViewById(R.id.ervSell);
            tvNameBank = (TextView) v.findViewById(R.id.tvNameBank);
            textViewBestBuy = (TextView) v.findViewById(R.id.textViewBestBuy);
            textViewBestSell = (TextView) v.findViewById(R.id.textViewBestSell);
            Typeface font = Typeface.createFromAsset(v.getContext().getAssets(), "fontawesome-webfont.ttf");
            textViewBestBuy.setTypeface(font);
            textViewBestSell.setTypeface(font);
            textViewAngle = (TextView) v.findViewById(R.id.textViewAngle);
            textViewAngle.setTypeface(font);
            textViewDate = (TextView) v.findViewById(R.id.textViewDate);
            textViewLink = (TextView) v.findViewById(R.id.textViewLink);
            textViewLink.setTypeface(font);
            cardView = (CardView) v.findViewById(R.id.card_view);
            linearLayoutLink = (LinearLayout) v.findViewById(R.id.linearLayoutLink);
            linearLayoutLink.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(banks.get(getAdapterPosition()).getLink()));
                    v.getContext().startActivity(browserIntent);
                }
            });
            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    float currentRotation = 0;
                    Visibility visibility = visibilities.get(getAdapterPosition());
                    if (visibility.isVisibility()) {
                        final Animation animationRotateCenter = AnimationUtils.loadAnimation(v.getContext(), R.anim.angle_turn);
                        animationRotateCenter.setFillAfter(true);
                        textViewAngle.startAnimation(animationRotateCenter);
                        AlphaAnimation anim = new AlphaAnimation(1.0f,0.0f);
                        anim.setDuration(400);
                        anim.setStartOffset(0);
                        anim.setFillAfter(true);
                        textViewLink.startAnimation(anim);
                        ValueAnimator va = ValueAnimator.ofInt(linearLayoutTest.getHeight(),0);
                        va.setDuration(400);
                        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator animation) {
                                Integer value = (Integer) animation.getAnimatedValue();
                                linearLayoutTest.getLayoutParams().height = value.intValue();
                                linearLayoutTest.requestLayout();
                            }
                        });
                        va.start();
                        visibility.setVisibility(false);
                    } else {
                        AlphaAnimation anim = new AlphaAnimation(0.0f,1.0f);
                        anim.setDuration(400);
                        anim.setStartOffset(0);
                        anim.setFillAfter(true);
                        textViewLink.startAnimation(anim);
                        final Animation animationRotateCenter = AnimationUtils.loadAnimation(v.getContext(), R.anim.angle);
                        animationRotateCenter.setFillAfter(true);
                        textViewAngle.startAnimation(animationRotateCenter);
                        ValueAnimator va = ValueAnimator.ofInt(0,100);
                        va.setDuration(400);
                        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator animation) {
                                Integer value = (Integer) animation.getAnimatedValue();
                                linearLayoutTest.getLayoutParams().height = value.intValue();
                                linearLayoutTest.requestLayout();
                            }
                        });
                        va.start();
                        linearLayoutTest.setVisibility(View.VISIBLE);
                        visibility.setVisibility(true);
                    }

                    Log.d("Номер карт = ","" + getAdapterPosition() + "  "  + banks.get(getAdapterPosition()).getName());
                }
            });
        }
    }
}
