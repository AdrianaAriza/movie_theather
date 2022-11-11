package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ShowsTicketsActivity extends AppCompatActivity {

    TicketViewModel ticketViewModel;
    Integer userId;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_tickets);
        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        layout = (LinearLayout) findViewById(R.id.tickets);
        SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
        userId = preferences.getInt("custId", -1);
        Log.d("USERID", String.valueOf(userId));
        ticketViewModel.getTicketsByUser(userId).observe(this, new Observer<List<Ticket>>() {
            @Override
            public void onChanged(List<Ticket> ticketList) {
                for(Ticket ticket : ticketList){
                    addTicketView(ticket);
                }
                Button finish = new Button(ShowsTicketsActivity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,40, 0, 0);
                finish.setLayoutParams(params);
                finish.setGravity(Gravity.CENTER_HORIZONTAL);
                finish.setText("FINISH");
                finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                layout.addView(finish);
            }
        });
    }

    private void addTicketView(Ticket ticket){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, 100, 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        TextView movieName = new TextView(this);
        movieName.setText("Movie: " + ticket.getMovieName());
        linearLayout.addView(movieName);
        TextView dateTime = new TextView(this);
        dateTime.setText("Date & Time: " + ticket.getShowDate() + " - " + ticket.getShowTime());
        linearLayout.addView(dateTime);
        TextView number = new TextView(this);
        number.setText("Amount of Tickets: " + String.valueOf(ticket.getNumbOfTickets()));
        linearLayout.addView(number);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Date date = sf.parse(ticket.getShowDate());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date now = new Date();
            System.out.println(dateFormat.format(date));
            long diff = date.getTime() - now.getTime();
            TimeUnit time = TimeUnit.DAYS;
            long difference = time.convert(diff, TimeUnit.MILLISECONDS);
            System.out.println("The difference in days is : "+difference);
            if(difference >= 2){
                Button cancel = new Button(this);
                cancel.setText("CANCEL TICKET");
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ticketViewModel.delete(ticket);
                        finish();
                        startActivity(getIntent());
                    }
                });
                linearLayout.addView(cancel);
            }
        } catch (Exception e){
            Log.d("Error", String.valueOf(e));
        }


        layout.addView(linearLayout);
    }
}