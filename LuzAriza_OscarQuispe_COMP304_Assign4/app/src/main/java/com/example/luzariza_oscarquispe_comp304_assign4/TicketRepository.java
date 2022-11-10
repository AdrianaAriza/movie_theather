package com.example.luzariza_oscarquispe_comp304_assign4;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TicketRepository {
    private final TicketDao ticketDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private MutableLiveData<Integer> deleteResult = new MutableLiveData<>();
    private LiveData<List<Ticket>> ticketsList;
    //
    public TicketRepository(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        ticketDao = db.ticketDao();

    }
    // returns query results as LiveData object
    LiveData<List<Ticket>> getTicketsByUser(Integer userId) {
        //call interface method
        ticketsList = ticketDao.getTicketsByUser(userId);
        return ticketsList;
    }
    //inserts a ticket asynchronously
    public void insert(Ticket ticket) {
        insertAsync(ticket);
    }
    public void delete(Ticket ticket) {
        deleteAsync(ticket);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    public LiveData<Integer> getDeleteResult() {
        return deleteResult;
    }

    private void insertAsync(final Ticket ticket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ticketDao.insert(ticket);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
    private void deleteAsync(final Ticket ticket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ticketDao.delete(ticket);
                    deleteResult.postValue(1);
                } catch (Exception e) {
                    deleteResult.postValue(0);
                }
            }
        }).start();
    }
}