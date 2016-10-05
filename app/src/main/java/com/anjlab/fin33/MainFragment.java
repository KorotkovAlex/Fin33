package com.anjlab.fin33;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.ExchangeRate;
import com.anjlab.fin33.model.Fin33Parser;
import com.anjlab.fin33.model.ParseCompletedListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] myDataset;
    Document doc;
    Bank bank;
    List<Bank> banks;
    Context context;

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
        try {
            doc = Jsoup.parse(getActivity().getAssets().open("fin33_16_09_2016.html"), "windows-1251", "");
            new Fin33Parser().parseMainInfo(doc, new ParseCompletedListener() {
                @Override
                public void onParseDone(List<Bank> banks) {
                    MainFragment.this.banks = banks;

                    mAdapter = new MyAdapter(banks, new ExchangeRate.Currency[]{
                            ExchangeRate.Currency.USD, ExchangeRate.Currency.EUR
                    });
                    mRecyclerView.setAdapter(mAdapter);
                }
            });
        } catch (Exception e) {
           throw new RuntimeException("Error", e);
        }

        return view;
    }


}
