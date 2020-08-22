package com.ewo.laddemo.ui.addmovie;

import android.app.Application;

import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.MovieModel;
import com.ewo.laddemo.ui.base.BaseViewModel;

import androidx.annotation.NonNull;

public class AddMovieViewModel extends BaseViewModel {
    public EnumViewCounterType selectedCounterType = EnumViewCounterType.INSTANT_COUNTER; // default value

    public AddMovieViewModel(@NonNull Application application) {
        super(application);
    }

    public void save(String name, String rating, String year, String url) {
        MovieModel movieModel = new MovieModel(name, Float.parseFloat(rating), Integer.parseInt(year), url, selectedCounterType);
        repository.addMovie(movieModel);
    }
}