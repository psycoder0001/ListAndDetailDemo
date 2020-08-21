package com.ewo.laddemo.ui.detail;

import android.app.Application;

import com.ewo.laddemo.localdb.MovieModel;
import com.ewo.laddemo.ui.base.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/**
 * This is the view model class of the fragment_detail page.
 * <p>
 * View classes such as fragment & activity should not contain any data operation
 * & all the data operations must be written here.
 */
public class DetailViewModel extends BaseViewModel {
    public MovieModel movieModel;
    public DetailFragmentDTO data;

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MovieModel> getMovieById(int personId) {
        return repository.getMovieById(personId);
    }

    public void removeMovie() {
        repository.removeMovie(movieModel);
        movieModel = null;
    }
}
