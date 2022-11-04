package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signOut = findViewById(R.id.signOut);
        signOut.setVisibility(View.INVISIBLE);
        SharedPreferences myPref=getSharedPreferences("PrefFile", 0);
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("session", "False");
        editor.commit();
        session = myPref.getString("session", "False");
    }
    @Override
    public void onResume() {
        super.onResume();
        login_btn = findViewById(R.id.login);
        update_btn = findViewById(R.id.update_profile);
        SharedPreferences myPref=getSharedPreferences("PrefFile", 0);
        session = myPref.getString("session", "False");
        String userName = myPref.getString("userName", "");
        String gret = "Welcome " + userName;
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

}