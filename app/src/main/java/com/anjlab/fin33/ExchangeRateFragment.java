package com.anjlab.fin33;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;
import com.anjlab.fin33.model.ExchangeRate;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p/>
 * to handle interaction events.
 * Use the {@link ExchangeRateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExchangeRateFragment extends Fragment implements BanksUpdatedListener {

    public LinearLayout mN;
    //String[] myDataset;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ;
    ExchangeRate.Currency currency;
    ArrayList<String> myDataset = new ArrayList<String>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ExchangeRateFragment() {
    }

    public static ExchangeRateFragment newInstance(ExchangeRate.Currency currency) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("currency", currency);
        ExchangeRateFragment fragment = new ExchangeRateFragment();

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange_rate, container, false);
        mN = (LinearLayout) view.findViewById(R.id.messageNull);
        this.currency = (ExchangeRate.Currency) getArguments().getSerializable("currency");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            ParseFin33Task mt = new ParseFin33Task(null);
                            mt.execute();
                        }
                    }
            );
        }
        AppState.getInstance().subscribe(this);
        onParseDone(AppState.getInstance().getBanks());
        return view;
    }

    @Override
    public void onDestroyView() {

        AppState.getInstance().unsubscribe(this);
        super.onDestroyView();
    }

    @Override
    public void onParseDone(List<Bank> banks) {

        //myDataset = new String[17];
        for (int i = 0; banks.size() > i; i++) {
            Bank bank = banks.get(i);
            //myDataset[i] = bank.getName();
            myDataset.add(bank.getName());
        }
        mAdapter = new BankRowAdapter(banks, currency);
        mRecyclerView.setAdapter(mAdapter);
        TextView btnNew = new TextView(getContext());
        if (banks.size() == 0) {
            btnNew.setText("Перезагрузите приложение");
            mN.addView(btnNew);
        } else {
            btnNew.setText("");
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onParseError(Throwable error) {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
