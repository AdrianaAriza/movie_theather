package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    String session = "False";
    Button login_btn;
    Button update_btn;
    TextView greeting;
    Button signOut;
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signOut = findViewById(R.id.signOut);
        signOut.setVisibility(View.INVISIBLE);
        SharedPreferences myPref=getSharedPreferences("PrefFile", 0);
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("session", "False");
        editor.commit();
        session = myPref.getString("session", "False");

        String[] movieNames;
        movieNames = getResources().getStringArray(R.array.lmovies);
        for (String name : movieNames ){
            Movie movie = new Movie(name);
            movieViewModel.insert(movie);
        }
    }
    @Override
    public void onResume() {
        Log.d("onResume", "onResume");
        super.onResume();
        login_btn = findViewById(R.id.login);
        update_btn = findViewById(R.id.update_profile);
        SharedPreferences myPref=getSharedPreferences("PrefFile", 0);
        session = myPref.getString("session", "False");
        String firstName = myPref.getString("firstName", "");
        String gret = "Welcome " + firstName;
        greeting = findViewById(R.id.greeting);
        if (Objects.equals(session, "True")) {
            greeting.setVisibility(View.VISIBLE);
            greeting.setText(gret);
            login_btn.setVisibility(View.INVISIBLE);
            update_btn.setVisibility(View.VISIBLE);
            signOut.setVisibility(View.VISIBLE);
        } else {
            greeting.setVisibility(View.INVISIBLE);
            login_btn.setVisibility(View.VISIBLE);
            update_btn.setVisibility(View.INVISIBLE);
            signOut.setVisibility(View.INVISIBLE);
        }
    }

    public void goToLogin(View view){
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
    }

    public void goToMovie(View view){
        if (Objects.equals(session, "True")) {
            Intent book = new Intent(MainActivity.this, TicketActivity.class);
            startActivity(book);
        } else {
            Toast.makeText(MainActivity.this, "LOGIN FIRST", Toast.LENGTH_LONG).show();
            Intent login = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(login);
        }
    }

    public void signOut(View view){
        SharedPreferences myPref=getSharedPreferences("PrefFile", 0);
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("session", "False");
        editor.commit();
        onResume();
    }
    public void goToUpdate(View view){
        Intent update = new Intent(MainActivity.this, UpdateActivity.class);
        startActivity(update);
    }

    public void showTickets(View v){
        if (Objects.equals(session, "True")) {
            Intent showTickets = new Intent(MainActivity.this, ShowsTicketsActivity.class);
            startActivity(showTickets);

        } else {
            Toast.makeText(MainActivity.this, "LOGIN FIRST", Toast.LENGTH_LONG).show();
            Intent login = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(login);
        }

    }

}