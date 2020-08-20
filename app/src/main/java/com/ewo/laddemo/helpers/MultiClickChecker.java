package com.ewo.laddemo.helpers;

import android.util.SparseLongArray;
import android.view.View;

public class MultiClickChecker {
    private static final long MIN_CLICK_INTERVAL = 500;
    public static SparseLongArray clickMemory;

    public static boolean isMultiClick(View view) {
        int viewId = view.getId();
        long currentTime = System.currentTimeMillis();
        if (clickMemory == null) {
            clickMemory = new SparseLongArray();
        } else {
            if (currentTime - clickMemory.get(viewId) < MIN_CLICK_INTERVAL) {
                return true;
            }
        }
        clickMemory.put(viewId, currentTime);
        return false;
    }
}
