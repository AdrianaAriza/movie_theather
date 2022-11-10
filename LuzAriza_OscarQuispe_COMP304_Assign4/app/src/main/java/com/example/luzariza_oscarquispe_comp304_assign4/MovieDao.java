package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insert (Movie movie);

    @Query("Select * from Movie order by movieId")
    LiveData<List<Movie>>getAllMovies();

    @Query("select * from Movie where movieName = :name")
    LiveData<List<Movie>>getMovieByName(String name);
}