package com.ewo.laddemo.localdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;

public enum EnumViewCounterType {
    INSTANT_COUNTER, DESTROY_COUNTER, REMOTE_EVENT_COUNTER;

    public static EnumViewCounterType parse(int viewCounterType) {
        switch (viewCounterType) {
            case 2:
                return REMOTE_EVENT_COUNTER;
            case 1:
                return DESTROY_COUNTER;
            default:
                return INSTANT_COUNTER;
        }
    }

    public int getId() {
        switch (this) {
            case REMOTE_EVENT_COUNTER:
                return 2;
            case DESTROY_COUNTER:
                return 1;
            default: //INSTANT_COUNTER
                return 0;
        }
    }

    public String getShortName() {
        switch (this) {
            case REMOTE_EVENT_COUNTER:
                return "REM C";
            case DESTROY_COUNTER:
                return "DES C";
            default: //INSTANT_COUNTER
                return "INS C";
        }
    }

    @NonNull
    @Override
    public String toString() {
        switch (this) {
            case REMOTE_EVENT_COUNTER:
                return "Remote View Counter";
            case DESTROY_COUNTER:
                return "Destroy View Counter";
            default: //INSTANT_COUNTER
                return "Instant View Counter";
        }
    }

    public static EnumViewCounterType getRandomType(Random random) {
        return EnumViewCounterType.parse(random.nextInt(EnumViewCounterType.values().length));
    }

    public static List<String> getListOfItem() {
        List<String> itemNames = new ArrayList<>();
        itemNames.add(INSTANT_COUNTER.toString());
        itemNames.add(DESTROY_COUNTER.toString());
        itemNames.add(REMOTE_EVENT_COUNTER.toString());
        return itemNames;
    }
}
