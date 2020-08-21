package com.ewo.laddemo.localdb;

import android.content.Context;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * This class manges the local data operations such add, get, remove & update.
 * It can handle the api calls as well if necessary.
 * For example the retrofit instance must be stored in here
 * & the api calls should be called from this class.
 */
public class MovieRepository {

    private static MovieRepository INSTANCE;

    private MovieDao movieDataAccessObject;

    public static MovieRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new MovieRepository(context);
        }
        return INSTANCE;
    }

    private MovieRepository(Context context) {
        movieDataAccessObject = MovieDataBase.getInstance(context.getApplicationContext()).movieDao();
    }

    public LiveData<List<MovieModel>> getMovieList() {
        // Perform local operations
        return movieDataAccessObject.getAllMovies();
        // Perform api call operations if necessary.
    }

    public LiveData<MovieModel> getMovieById(int movieId) {
        // Perform local operations
        return movieDataAccessObject.getMovie(movieId);
        // Perform api call operations if necessary.
    }

    public void addMovie(MovieModel newMovie) {
        // Perform local operations
        runAsync(() -> movieDataAccessObject.addMovie(newMovie));
        // Perform api call operations if necessary.
    }

    public void addMovies(List<MovieModel> movieModels) {
        // Perform local operations
        runAsync(() -> movieDataAccessObject.addMovies(movieModels));
        // Perform api call operations if necessary.
    }

    public void updateMovie(MovieModel movieModel) {
        // Perform local operations
        runAsync(() -> movieDataAccessObject.updateMovie(movieModel));
        // Perform api call operations if necessary.
    }

    public void removeMovie(MovieModel movie) {
        // Perform local operations
        runAsync(() -> movieDataAccessObject.deleteMovie(movie));
        // Perform api call operations if necessary.
    }

    public void wipeData() {
        runAsync(() -> movieDataAccessObject.wipeData());
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