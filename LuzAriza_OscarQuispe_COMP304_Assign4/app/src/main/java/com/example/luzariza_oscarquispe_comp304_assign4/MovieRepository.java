package com.example.luzariza_oscarquispe_comp304_assign4;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MovieRepository {
    private final MovieDao movieDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Movie>> moviesList;
    //
    public MovieRepository(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        movieDao = db.movieDao();
        //call interface method
        moviesList = movieDao.getAllMovies();
    }
    // returns query results as LiveData object
    LiveData<List<Movie>> getAllMovies() {
        return moviesList;
    }
    //inserts a movie asynchronously
    public void insert(Movie movie) {
        insertAsync(movie);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Movie movie) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    movieDao.insert(movie);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}