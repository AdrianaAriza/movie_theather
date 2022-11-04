package com.example.luzariza_oscarquispe_comp304_assign4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private MovieRepository movieRepository;
    private androidx.lifecycle.LiveData<Integer> insertResult;
    private androidx.lifecycle.LiveData<List<Movie>> allMovies;
    //
    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        insertResult = movieRepository.getInsertResult();
        allMovies = movieRepository.getAllMovies();
    }
    //calls repository to insert a movie
    public void insert(Movie movie) {
        movieRepository.insert(movie);
    }
    //gets insert results as LiveData object
    public androidx.lifecycle.LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
}