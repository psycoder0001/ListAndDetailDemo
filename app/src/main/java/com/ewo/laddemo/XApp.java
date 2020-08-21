package com.ewo.laddemo;

import com.ewo.laddemo.ui.base.FragmentEventListener;
import com.ewo.laddemo.ui.main.MainCounterIncreaseListener;

import java.util.Random;

import androidx.multidex.MultiDexApplication;

public class XApp extends MultiDexApplication {

    private static XApp instance;
    public MainCounterIncreaseListener mainCounterIncreaseListener;
    public FragmentEventListener fragmentEventListener;
    private Random random;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static XApp get() {
        return instance;
    }

    public Random getRandom() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }
}
