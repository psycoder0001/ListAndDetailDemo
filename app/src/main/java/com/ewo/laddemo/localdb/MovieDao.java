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
public interface MovieDao {

    //    @Query("SELECT * FROM movies ORDER BY rating DESC")
    @Query("SELECT * FROM movies")
    LiveData<List<MovieModel>> getAllMovies();

    @Insert
    void addMovies(List<MovieModel> movieModelList);

    @Insert
    void addMovie(MovieModel movieModel);

    @Update()
    void updateMovie(MovieModel movieModel);

    @Delete
    void deleteMovie(MovieModel movieModel);

    @Query("DELETE FROM movies")
    void wipeData();

    @Query("SELECT * FROM movies WHERE id IS :movieId")
    LiveData<MovieModel> getMovie(int movieId);
}