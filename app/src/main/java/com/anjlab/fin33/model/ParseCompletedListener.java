package com.anjlab.fin33.model;

import java.util.List;

/**
 * Created by Саня on 03.10.2016.
 */
public interface ParseCompletedListener {
    void onParseDone(List<Bank> banks);
}
