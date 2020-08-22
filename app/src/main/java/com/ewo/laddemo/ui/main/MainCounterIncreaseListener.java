package com.ewo.laddemo.ui.main;

import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.MovieModel;

public interface MainCounterIncreaseListener {
    void onIncreaseMovieCounter(MovieModel movieModel, EnumViewCounterType counterType);
}
