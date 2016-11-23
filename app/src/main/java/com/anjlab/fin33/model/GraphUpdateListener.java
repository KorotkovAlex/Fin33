package com.anjlab.fin33.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Саня on 03.10.2016.
 */
public interface GraphUpdateListener {

    void onParseDone(Map<String , List<TimeSeries>> map);

    //void onParseError(Throwable error);
}
