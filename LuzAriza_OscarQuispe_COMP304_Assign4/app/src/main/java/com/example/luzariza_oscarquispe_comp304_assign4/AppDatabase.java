package com.example.luzariza_oscarquispe_comp304_assign4;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.luzariza_oscarquispe_comp304_assign4.User;
import com.example.luzariza_oscarquispe_comp304_assign4.UserDao;

//Room database class
@Database(entities = {User.class, Movie.class, Ticket.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //
    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "MovieTheaterDB";

    public abstract UserDao userDao();
    public abstract MovieDao movieDao();
    public abstract TicketDao ticketDao();

    //
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }
}