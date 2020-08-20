package com.ewo.laddemo.localdb;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * This is class is a connection between the local db(SQLite) and application.
 * All of the sql operations commands must be written here.
 */
@Dao
public interface LoanDao {

    @Query("SELECT * FROM person ORDER BY loanTotal DESC")
    LiveData<List<PersonModel>> getAllPerson();

    @Insert
    void addPerson(List<PersonModel> personModelList);

    @Insert
    void addPerson(PersonModel personModel);

    @Update()
    void updatePerson(PersonModel personModel);

    @Delete
    void deletePerson(PersonModel personModel);

    @Query("DELETE FROM person")
    void wipeData();

    @Query("SELECT * FROM person WHERE id IS :personId")
    LiveData<PersonModel> getPerson(int personId);
}