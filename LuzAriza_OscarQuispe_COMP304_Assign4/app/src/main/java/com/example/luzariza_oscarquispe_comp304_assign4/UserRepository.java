package com.example.luzariza_oscarquispe_comp304_assign4;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;

import java.util.List;

public class UserRepository {
    private final UserDao userDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<User>> usersList;
    private LiveData<List<User>> password;

    //
    public UserRepository(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        userDao = db.userDao();
        //call interface method
        usersList = userDao.getAllUsers();
    }
    // returns query results as LiveData object
    LiveData<List<User>> getAllUsers() {
        return usersList;
    }
    // returns query results as LiveData object
    public LiveData<List<User>> getPasswordByEmail(String email) {
        return userDao.getPasswordByEmail(email);
    }
    //inserts a user asynchronously
    public void insert(User user) {
        insertAsync(user);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private  void insertAsync(final User user) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    userDao.insert(user);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
/*
    private LiveData<List<String>> getPasswordAsync(final String email) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    password = userDao.getPasswordByEmail(email);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
        return password;
    }*/
}
