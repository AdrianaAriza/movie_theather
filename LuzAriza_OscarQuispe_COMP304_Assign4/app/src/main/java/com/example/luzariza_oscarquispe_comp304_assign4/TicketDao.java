package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TicketDao {
    @Insert
    void insert(Ticket ticket);

    @Query("Select * from Ticket where custId like :userId")
    LiveData<List<Ticket>>getTicketsByUser(Integer userId);

    @Delete
    void delete(Ticket ticket);
}
