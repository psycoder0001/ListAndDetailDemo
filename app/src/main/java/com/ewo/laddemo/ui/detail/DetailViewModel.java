package com.ewo.laddemo.ui.detail;

import android.app.Application;

import com.ewo.laddemo.localdb.PersonModel;
import com.ewo.laddemo.ui.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/**
 * This is the view model class of the detail page.
 * <p>
 * View classes such as fragment & activity should not contain any data operation
 * & all the data operations must be written here.
 */
public class DetailViewModel extends BaseViewModel {
    public PersonModel personModel;

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<PersonModel> getPerson(int personId) {
        return repository.getPerson(personId);
    }

    public void removePerson() {
        repository.removePerson(personModel);
        personModel = null;
    }
}
