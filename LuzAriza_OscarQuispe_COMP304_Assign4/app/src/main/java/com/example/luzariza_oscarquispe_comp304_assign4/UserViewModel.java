package com.example.luzariza_oscarquispe_comp304_assign4;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private UserRepository userRepository;
    private androidx.lifecycle.LiveData<Integer> insertResult;
    private androidx.lifecycle.LiveData<List<User>> allUsers;
    private androidx.lifecycle.LiveData<List<User>> user;
    private androidx.lifecycle.LiveData<Integer> updateResult;
    //
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        insertResult = userRepository.getInsertResult();
        updateResult = userRepository.getUpdateResult();
        allUsers = userRepository.getAllUsers();
    }
    //calls repository to insert a user
    public void insert(User user) {
        userRepository.insert(user);
    }
    //
    public void update(User user) {
        userRepository.update(user);
    }
    //gets insert results as LiveData object
    public androidx.lifecycle.LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //
    public androidx.lifecycle.LiveData<Integer> getUpdateResult() {return updateResult; }
    //returns query results as live data object
    LiveData<List<User>> getAllUsers() { return allUsers; }

    LiveData<List<User>> getUserByEmail(String email){
        user = userRepository.getUserByEmail(email);
        return user;
    }
}
