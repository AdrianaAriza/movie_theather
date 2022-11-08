package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class TicketActivity extends AppCompatActivity {
    private TicketViewModel ticketViewModel;
    private MovieViewModel movieViewModel;
    private UserViewModel userViewModel;

    private Button btnPrint;
    private int numtick;
    private double tPrice;
    private EditText nTiAd;
    private EditText nTiCh;
    private CalendarView CalendView;

    Ticket ticket;
    Movie movie;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        ticket = new Ticket();
        movie = new Movie();
        user = new User();

        ticketViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(TicketActivity.this, "Movie ticket successfully booked", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(TicketActivity.this, "Error booking movie ticket", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Spinner spin = (Spinner) findViewById(R.id.m_spinner);
        spin.setSelection(-1);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos>0){
                    movie.setMovieName(adapterView.getItemAtPosition(pos).toString());
                    movieViewModel.insert(movie);
                    SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("MovieName",adapterView.getItemAtPosition(pos).toString());
                    editor.commit();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        RadioGroup radioGroup=findViewById(R.id.rg2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbt1=findViewById(R.id.rb2t1);
                if(rbt1.isChecked()){
                    ticket.setShowTime(rbt1.getText().toString());
                    SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("ShowTime",rbt1.getText().toString());
                    editor.commit();
                }
                RadioButton rbt2=findViewById(R.id.rb2t2);
                if(rbt2.isChecked()){
                    ticket.setShowTime(rbt2.getText().toString());
                    SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("ShowTime",rbt2.getText().toString());
                    editor.commit();
                }
                RadioButton rbt3=findViewById(R.id.rb2t3);
                if(rbt3.isChecked()){
                    ticket.setShowTime(rbt3.getText().toString());
                    SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("ShowTime",rbt3.getText().toString());
                    editor.commit();
                }
                RadioButton rbt4=findViewById(R.id.rb2t4);
                if(rbt4.isChecked()){
                    ticket.setShowTime(rbt4.getText().toString());
                    SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("ShowTime",rbt4.getText().toString());
                    editor.commit();
                }
            }
        });
        //TextView tView1 = (TextView)findViewById(R.id.price);
        //tView1.setText("Total Price: " +tPrice);

        CalendView = (CalendarView) findViewById(R.id.calendView);
        CalendView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                String showDa = ""+year+(month+1)+day;
                ticket.setShowDate(showDa);
                SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("ShowDate",showDa);
                editor.commit();
            }
        });
    }

    public void onClickPrint(View view) {
        nTiAd = findViewById(R.id.numadult);
        nTiCh = findViewById(R.id.numchild);
        numtick= Integer.parseInt(nTiAd.getText().toString())+Integer.parseInt(nTiCh.getText().toString());
        ticket.setNumbOfTickets(numtick);
        tPrice = (Integer.parseInt(nTiAd.getText().toString())*7.99)+(Integer.parseInt(nTiCh.getText().toString())*10.99);
        ticket.setPrice(tPrice);
        SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String strprice=tPrice+"";
        editor.putString("Price", strprice);
        editor.commit();
        ticket.setCustId(user.getCustId());
        ticket.setMovieId(movie.getMovieId());
        ticketViewModel.insert(ticket);
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }
    public void onClickCancel(View view) {
        SharedPreferences myPref=getSharedPreferences("PrefFile", 0);
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("session", "True");
        editor.commit();
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }
}