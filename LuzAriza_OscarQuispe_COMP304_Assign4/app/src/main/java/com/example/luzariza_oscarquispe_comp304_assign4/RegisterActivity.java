package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

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

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(RegisterActivity.this, "User successfully registered", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Error registering user", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void register(View view){
        firstName = findViewById(R.id.firstName);
        String fName = firstName.getText().toString();
        lastName = findViewById(R.id.lastName);
        String lName = lastName.getText().toString();
        address = findViewById(R.id.address);
        String add = address.getText().toString();
        city = findViewById(R.id.city);
        String ct = city.getText().toString();
        postalCode = findViewById(R.id.code);
        String pCode = postalCode.getText().toString();
        email = findViewById(R.id.email);
        String mail = email.getText().toString();
        phone = findViewById(R.id.email);
        String phoneNumber = phone.getText().toString();
        password = findViewById(R.id.password);
        String pwd = password.getText().toString();
        passwordConfirmation = findViewById(R.id.passwordConfirmation);
        String pwdConfirmation = passwordConfirmation.getText().toString();
        if(!fName.equals("") || !lName.equals("") || !add.equals("") ||
                !ct.equals("") || !pCode.equals("") || !mail.equals("") || !phoneNumber.equals("")
                || !pwd.equals(""))
        {
            if(pwd.equals(pwdConfirmation)){
                user = new User(pwd, fName, lName, add, ct, pCode, mail, false);
                userViewModel.insert(user);
            }else
                Toast.makeText(RegisterActivity.this, "Passwords DO NOT match", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(RegisterActivity.this, "Missing Information", Toast.LENGTH_LONG).show();
        }
    }
}