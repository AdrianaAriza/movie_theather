package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private Button btnInsert;
    private EditText editTextName;
    private TextView textViewDisplay;

    //New code
    TextView firstName;
    TextView lastName;
    TextView address;
    TextView city;
    TextView postalCode;
    TextView email;
    TextView phone;
    TextView password;
    TextView passwordConfirmation;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    public void update(View view) {
        SharedPreferences myPref = getSharedPreferences("PrefFile", 0);
        String emailText = myPref.getString("email", "");

        userViewModel.getPasswordByEmail(emailText).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> userList) {
                SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                try {
                    User user = userList.get(0);
                    firstName = findViewById(R.id.firstName);
                    String fName = firstName.getText().toString();
                    if (!fName.equals("")) {
                        user.setFirstName(fName);
                        editor.putString("userName", fName);
                        editor.commit();
                    }
                    lastName = findViewById(R.id.lastName);
                    String lName = lastName.getText().toString();
                    if (!lName.equals("")) {
                        user.setLastName(lName);
                    }
                    address = findViewById(R.id.address);
                    String add = address.getText().toString();
                    if (!add.equals("")) {
                        user.setAddress(add);
                    }
                    city = findViewById(R.id.city);
                    String ct = city.getText().toString();
                    if (!ct.equals("")) {
                        user.setCity(ct);
                    }
                    postalCode = findViewById(R.id.code);
                    String pCode = postalCode.getText().toString();
                    if (!pCode.equals("")) {
                        user.setPostalCode(pCode);
                    }
                    email = findViewById(R.id.email);
                    String mail = email.getText().toString();
                    if (!mail.equals("")) {
                        user.setEmail(mail);
                        editor.putString("email", mail);
                        editor.commit();
                    }
                    phone = findViewById(R.id.phone);
                    String phoneNumber = phone.getText().toString();
                    if (!phoneNumber.equals("")) {
                        user.setPhone(phoneNumber);
                    }
                    userViewModel.update(user);
                } catch (Exception e) {
                    Toast.makeText(UpdateActivity.this, "USER NOT FOUND", Toast.LENGTH_LONG).show();
                }
            }
        });

        userViewModel.getUpdateResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(UpdateActivity.this, "User successfully updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Error updating user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}