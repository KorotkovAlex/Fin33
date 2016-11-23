package com.anjlab.fin33.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Саня on 21.10.2016.
 */
public class AppState {

    private static AppState appState = new AppState();
    private List<Bank> banks = new ArrayList<>();
    private List<BanksUpdatedListener> subscribers = new ArrayList<>();
    private Map<String,List<TimeSeries>> timeSeriesMap = new HashMap<String,List<TimeSeries>>();
    private List<GraphUpdateListener> subscribersWithGraphs = new ArrayList<>();
    public AppState() {
    }

    public static AppState getInstance() {
        return appState;
    }

    public void updateBanks(List<Bank> newBanks) {
        this.banks = newBanks;
        notifySubscribers();
    }

    public void updateGraph(Map <String,List<TimeSeries>> timeSeries) {
        this.timeSeriesMap = timeSeries;
        notifySubscribers();
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void subscribe(BanksUpdatedListener listener) {
        subscribers.add(listener);
        Log.d("Count subscribers", "" + subscribers.size());
    }

    public void subscribeGraph(GraphUpdateListener listener) {
        subscribersWithGraphs.add(listener);
        Log.d("Count subscribers", "" + subscribers.size());
    }
    public Map<String,List<TimeSeries>> getGraph() {
        return timeSeriesMap;
    }
    private void notifySubscribers() {
        for (BanksUpdatedListener subscriber : subscribers) {
            subscriber.onParseDone(banks);
        }
        for (GraphUpdateListener subscribersWithGraph : subscribersWithGraphs ) {
            subscribersWithGraph.onParseDone(timeSeriesMap);
        }
    }

    public void unsubscribe(BanksUpdatedListener listener) {
        subscribers.remove(listener);
    }

    public void parseError(Throwable error) {
        for (BanksUpdatedListener subscriber : subscribers) {
            subscriber.onParseError(error);
        }
    }
}
