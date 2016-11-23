package com.anjlab.fin33.model;

import android.graphics.Color;

import java.util.List;

/**
 * Created by alex on 11/21/16.
 */

public class TimeSeries {

    private List<Value> values;
    private String title;
    private int color;

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
