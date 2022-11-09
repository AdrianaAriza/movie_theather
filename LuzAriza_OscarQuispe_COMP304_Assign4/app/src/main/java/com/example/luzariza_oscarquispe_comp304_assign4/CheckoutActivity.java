package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //retrieving from shared preferences
        SharedPreferences myPref = getSharedPreferences("PrefFile", 0);
        String fname = myPref.getString("userName","");
        TextView tView1 = (TextView)findViewById(R.id.fn);
        tView1.setText("Customer Name: " +fname);

        String emai = myPref.getString("email","");
        TextView tView4 = (TextView)findViewById(R.id.em);
        tView4.setText("Customer Email: " +emai);

        String mona=myPref.getString("MovieName","");
        TextView txtView1=findViewById(R.id.movien);
        txtView1.setText("Movie Name: " +mona);

        String stime=myPref.getString("ShowTime","");
        TextView txtView2=findViewById(R.id.shti);
        txtView2.setText("Show time: " +stime);

        String sdate=myPref.getString("ShowDate","");
        TextView txtView3=findViewById(R.id.shda);
        txtView3.setText("Show Date: " +sdate);

        String prict=myPref.getString("Price","");
        TextView txtView4=findViewById(R.id.pt);
        txtView4.setText("Price: $" +prict);
    }
    public void backToMain(View view){
        Intent main = new Intent(CheckoutActivity.this, MainActivity.class);
        main.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(main, 0);
    }
}