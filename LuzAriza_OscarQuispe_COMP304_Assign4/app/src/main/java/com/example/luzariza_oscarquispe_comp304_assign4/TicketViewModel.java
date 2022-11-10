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
    private MovieRepository movieRepository;
    private androidx.lifecycle.LiveData<Integer> insertResult;
    private androidx.lifecycle.LiveData<Integer> deleteResult;
    private androidx.lifecycle.LiveData<List<Ticket>> allTickets;
    private androidx.lifecycle.LiveData<List<Movie>> movie;
    //
    public TicketViewModel(@NonNull Application application) {
        super(application);
        ticketRepository = new TicketRepository(application);
        insertResult = ticketRepository.getInsertResult();
        deleteResult = ticketRepository.getDeleteResult();
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

    //calls repository to insert a ticket
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
    //gets insert results as LiveData object
    public androidx.lifecycle.LiveData<Integer> getDeleteResult() {

        return deleteResult;
    }
    //returns query results as live data object
    LiveData<List<Ticket>> getTicketsByUser(Integer userId ) {
        allTickets = ticketRepository.getTicketsByUser(userId);
        return allTickets;
    }
    //
}