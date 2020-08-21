package com.ewo.laddemo.localdb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class MovieModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;
    public float rating;
    public int year;
    public String imgUrl;
    public int viewCountValue;
    public int viewCounterType;

    public MovieModel() {
        name = "N/A";
    }

    public MovieModel(String name, float rating, int year, String imgUrl, EnumViewCounterType viewCounterType) {
        this.name = name;
        this.rating = rating;
        this.year = year;
        this.imgUrl = imgUrl;
        this.viewCounterType = viewCounterType.getId(); // manuel mapping is safer
//        this.viewIncrementType = viewIncrementType.ordinal(); // index of the enum.
    }

    public String getRating() {
        return rating + "/10";
    }

    public EnumViewCounterType getViewCounterType() {
        return EnumViewCounterType.parse(viewCounterType);
    }
}
