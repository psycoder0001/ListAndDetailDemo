package com.ewo.laddemo.ui.base;

import android.app.Application;
import android.util.Log;

import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.MovieModel;
import com.ewo.laddemo.localdb.MovieRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public abstract class BaseViewModel extends AndroidViewModel {
    protected MovieRepository repository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance(application);
    }

    public void increaseViewCounterWithType(MovieModel movieModel, EnumViewCounterType acceptableCounterType) {
        if (movieModel == null) {
            Log.println(Log.ASSERT, "Detail", "Failed to increase movie view counter! This movie is removed.");
            return;
        }
        if (movieModel.getViewCounterType() == acceptableCounterType) {
            movieModel.viewCountValue++;
            repository.updateMovie(movieModel);
        }
    }
}