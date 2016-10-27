package com.anjlab.fin33;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjlab.fin33.model.AppState;
import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.ExchangeRate;
import com.anjlab.fin33.model.Fin33Parser;
import com.anjlab.fin33.model.BanksUpdatedListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.ParseException;
import java.util.List;



public class MainFragment extends Fragment implements BanksUpdatedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] myDataset;
    Document doc;
    Bank bank;
    List<Bank> banks;
    Context context;
    SwipeRefreshLayout mSwipeRefreshLayout;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ParseFin33Task mt = new ParseFin33Task(null);
                        mt.execute();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
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
        // TODO
        MainFragment.this.banks = banks;
        mAdapter = new MainFragmentAdapter(AppState.getInstance().getBanks(), new ExchangeRate.Currency[]{
                ExchangeRate.Currency.USD, ExchangeRate.Currency.EUR
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onParseError(Throwable error) {
        //TODO

    }
}
