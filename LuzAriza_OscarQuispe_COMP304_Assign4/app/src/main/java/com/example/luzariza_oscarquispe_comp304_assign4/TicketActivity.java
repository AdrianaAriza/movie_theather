package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

public class TicketActivity extends AppCompatActivity {
    private TicketViewModel ticketViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);

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
    }
}