package com.ewo.laddemo.localdb;

import java.util.Random;

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

    public static EnumViewCounterType getRandomType(Random random) {
        return EnumViewCounterType.parse(random.nextInt(EnumViewCounterType.values().length));
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
}
