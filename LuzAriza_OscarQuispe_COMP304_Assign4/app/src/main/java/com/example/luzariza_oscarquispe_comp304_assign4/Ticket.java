package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    private int ticketId;
    private int custId;
    private int movieId;
    private String showDate;
    private String showTime;
    private int numbOfTickets;
    private double price;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getNumbOfTickets() {
        return numbOfTickets;
    }

    public void setNumbOfTickets(int numbOfTickets) {
        this.numbOfTickets = numbOfTickets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}