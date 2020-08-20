package com.ewo.laddemo;

import com.ewo.laddemo.ui.main.MainCounterIncreaseListener;

import java.util.Random;

import androidx.multidex.MultiDexApplication;

public class XApp extends MultiDexApplication {

    public MainCounterIncreaseListener mainCounterIncreaseListener;
    private Random random;

    public Random getRandom() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }
}
