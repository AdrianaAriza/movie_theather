package com.example.luzariza_oscarquispe_comp304_assign4;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class UserRepository {
    private final UserDao userDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<User>> usersList;
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
    //inserts a user asynchronously
    public void insert(User user) {
        insertAsync(user);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final User user) {

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
}
