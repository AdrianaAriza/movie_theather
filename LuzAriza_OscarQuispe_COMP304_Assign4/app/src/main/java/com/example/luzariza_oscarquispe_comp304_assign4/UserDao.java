package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("select * from User")
    LiveData<List<User>>getAllUsers();

    @Query("select * from User where email = :email")
    LiveData<List<User>>getPasswordByEmail(String email);

}
