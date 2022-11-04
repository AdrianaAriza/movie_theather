package com.example.luzariza_oscarquispe_comp304_assign4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TicketViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private TicketRepository ticketRepository;
    private androidx.lifecycle.LiveData<Integer> insertResult;
    private androidx.lifecycle.LiveData<List<Ticket>> allTickets;
    //
    public TicketViewModel(@NonNull Application application) {
        super(application);
        ticketRepository = new TicketRepository(application);
        insertResult = ticketRepository.getInsertResult();
        allTickets = ticketRepository.getAllTickets();
    }
    //calls repository to insert a ticket
    public void insert(Ticket ticket) {
        ticketRepository.insert(ticket);
    }
    //gets insert results as LiveData object
    public androidx.lifecycle.LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    LiveData<List<Ticket>> getAllTickets() {
        return allTickets;
    }
}