package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Boolean user_logged = false; // This status will come from the data base
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login_btn = findViewById(R.id.login);
        Button update_btn = findViewById(R.id.update_profile);

        if (user_logged) {
            login_btn.setVisibility(View.INVISIBLE);
            update_btn.setVisibility(View.VISIBLE);
        } else {
            login_btn.setVisibility(View.VISIBLE);
            update_btn.setVisibility(View.INVISIBLE);
        }
    }
}