package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TicketDao {
    @Insert
    void insert(Ticket ticket);

    @Query("Select * from Ticket")
    LiveData<List<Ticket>>getAllTickets();
}
