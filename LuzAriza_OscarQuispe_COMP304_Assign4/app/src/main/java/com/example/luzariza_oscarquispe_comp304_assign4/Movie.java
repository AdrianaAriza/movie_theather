package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"movieName"},
        unique = true)})
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int movieId;

    @ColumnInfo(name="movieName")
    private String movieName;

    public Movie() {
    }
    public Movie(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}