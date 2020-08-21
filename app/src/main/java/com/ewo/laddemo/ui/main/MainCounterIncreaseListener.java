package com.ewo.laddemo.ui.main;

import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.MovieModel;

public interface MainCounterIncreaseListener {
    void onIncreasePersonCounter(MovieModel personModel, EnumViewCounterType counterType);
}
