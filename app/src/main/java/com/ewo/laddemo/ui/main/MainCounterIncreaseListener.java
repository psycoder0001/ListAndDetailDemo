package com.ewo.laddemo.ui.main;

import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.PersonModel;

public interface MainCounterIncreaseListener {
    void onIncreasePersonCounter(PersonModel personModel, EnumViewCounterType counterType);
}
