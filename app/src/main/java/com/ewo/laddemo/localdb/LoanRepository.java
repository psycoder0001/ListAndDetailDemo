package com.ewo.laddemo.localdb;

import android.content.Context;

import java.util.List;
import java.util.Random;

import androidx.lifecycle.LiveData;

/**
 * This class manges the local data operations such add, get, remove & update.
 * It can handle the api calls as well if necessary.
 * For example the retrofit instance must be stored in here
 * & the api calls should be called from this class.
 */
public class LoanRepository {

    private static LoanRepository INSTANCE;

    private LoanDao loanDataAccessObject;

    public static LoanRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LoanRepository(context);
        }
        return INSTANCE;
    }

    private LoanRepository(Context context) {
        loanDataAccessObject = LoanDataBase.getInstance(context.getApplicationContext()).loanDao();
    }

    public static PersonModel GenerateNewPerson(EnumViewCounterType counterType) {
        // Perform local operations
        String name = new String[]{"Jake", "Miles", "Picard", "Worf", "Pane", "Merry", "Fairy", "Carry", "Alex", "Nikolai", "Samuel"}[new Random().nextInt(5)];
        int loanTotal = new Random().nextInt(90001) + 10000;
        int loanPaid = new Random().nextInt(loanTotal + 1);
        PersonModel personModel = new PersonModel(name, loanTotal, loanPaid, counterType);
        return personModel;
        // Perform api call operations if necessary.
    }

    public LiveData<List<PersonModel>> getPersonList() {
        // Perform local operations
        return loanDataAccessObject.getAllPerson();
        // Perform api call operations if necessary.
    }

    public LiveData<PersonModel> getPerson(int personId) {
        // Perform local operations
        return loanDataAccessObject.getPerson(personId);
        // Perform api call operations if necessary.
    }

    public void addPerson(PersonModel newPerson) {
        // Perform local operations
        runAsync(() -> loanDataAccessObject.addPerson(newPerson));
        // Perform api call operations if necessary.
    }

    public void updatePerson(PersonModel personModel) {
        // Perform local operations
        runAsync(() -> loanDataAccessObject.updatePerson(personModel));
        // Perform api call operations if necessary.
    }

    public void removePerson(PersonModel person) {
        // Perform local operations
        runAsync(() -> loanDataAccessObject.deletePerson(person));
        // Perform api call operations if necessary.
    }

    public void wipeData() {
        runAsync(() -> loanDataAccessObject.wipeData());
    }

    private void runAsync(Runnable runnable) {
        new Thread() {
            @Override
            public void run() {
                runnable.run();
            }
        }.start();
    }
}