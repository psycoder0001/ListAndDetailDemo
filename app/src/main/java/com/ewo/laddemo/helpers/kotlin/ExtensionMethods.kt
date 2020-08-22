package com.ewo.laddemo.helpers.kotlin

import com.google.gson.Gson

fun <T> List<T>?.convertListToJson(): String {
    // If the list is null or empty.
    if (this == null || this.isEmpty()) {
        return ""
    }
    return Gson().toJson(this);
}