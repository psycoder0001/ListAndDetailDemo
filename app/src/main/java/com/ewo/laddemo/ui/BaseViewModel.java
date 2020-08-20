package com.ewo.laddemo.ui;

import android.app.Application;
import android.util.Log;

import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.LoanRepository;
import com.ewo.laddemo.localdb.PersonModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public abstract class BaseViewModel extends AndroidViewModel {
    protected LoanRepository repository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        repository = LoanRepository.getInstance(application);
    }

    public void increaseViewCounterWithType(PersonModel personModel, EnumViewCounterType counterType) {
        if (personModel == null) {
            Log.println(Log.ASSERT, "Detail", "Failed to increase person view counter! Person might be already removed.");
            return;
        }
        if (personModel.getViewCounterType() == counterType) {
            personModel.viewCountValue++;
            repository.updatePerson(personModel);
        }
    }
}
