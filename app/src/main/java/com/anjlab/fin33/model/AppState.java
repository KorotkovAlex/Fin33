package com.anjlab.fin33.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Саня on 21.10.2016.
 */
public class AppState {
    private static AppState appState = new AppState();

    private List<Bank> banks = new ArrayList<>();
    private List<BanksUpdatedListener> subscribers = new ArrayList<>();

    public AppState() {
    }

    public static AppState getInstance() {
        return appState;
    }

    public void updateBanks(List<Bank> newBanks) {
        this.banks = newBanks;
        notifySubscribers();
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void subscribe(BanksUpdatedListener listener) {
        subscribers.add(listener);
        Log.d("Count subscribers", "" + subscribers.size());
    }

    private void notifySubscribers() {
        for (BanksUpdatedListener subscriber : subscribers) {
            subscriber.onParseDone(banks);

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
