package com.ewo.laddemo.ui.main;

import android.app.Application;

import com.ewo.laddemo.localdb.DummyItemGenerator;
import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.MovieModel;
import com.ewo.laddemo.ui.base.BaseViewModel;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/**
 * This is the view model class of the main listing page.
 * <p>
 * View classes such as fragment & activity should not contain any data operation
 * & all the data operations must be written here.
 */
public class MainViewModel extends BaseViewModel {
    public LiveData<List<MovieModel>> personList;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<MovieModel>> getPersonList() {
        if (personList == null) {
            personList = repository.getMovieList();
        }
        return personList;
    }

    public void wipeData() {
        repository.wipeData();
    }

    public void generateInitialData() {
        MovieModel person0 = DummyItemGenerator.GenerateNewItem(0, EnumViewCounterType.INSTANT_COUNTER);
        MovieModel person1 = DummyItemGenerator.GenerateNewItem(1, EnumViewCounterType.DESTROY_COUNTER);
        MovieModel person2 = DummyItemGenerator.GenerateNewItem(2, EnumViewCounterType.REMOTE_EVENT_COUNTER);
        repository.addMovies(Arrays.asList(person0, person1, person2));
    }
}
