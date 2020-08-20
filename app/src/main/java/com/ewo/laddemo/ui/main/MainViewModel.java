package com.ewo.laddemo.ui.main;

import android.app.Application;

import com.ewo.laddemo.XApp;
import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.LoanRepository;
import com.ewo.laddemo.localdb.PersonModel;
import com.ewo.laddemo.ui.BaseViewModel;

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
    public LiveData<List<PersonModel>> personList;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<PersonModel>> getPersonList() {
        if (personList == null) {
            personList = repository.getPersonList();
        }
        return personList;
    }

    public void generateRandomPerson() {
        EnumViewCounterType counterType = EnumViewCounterType.getRandomType(((XApp) getApplication()).getRandom());
        PersonModel newPerson = LoanRepository.GenerateNewPerson(counterType);
        repository.addPerson(newPerson);
    }

    public void wipeData() {
        repository.wipeData();
    }
}
