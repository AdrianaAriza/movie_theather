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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
        firstName = findViewById(R.id.firstName);
        firstName.setText(preferences.getString("firstName", ""));
        lastName = findViewById(R.id.lastName);
        lastName.setText(preferences.getString("lastName", ""));
        address = findViewById(R.id.address);
        address.setText(preferences.getString("address", ""));
        city = findViewById(R.id.city);
        city.setText(preferences.getString("city", ""));
        postalCode = findViewById(R.id.code);
        postalCode.setText(preferences.getString("postalCode", ""));
        email = findViewById(R.id.email);
        email.setText(preferences.getString("email", ""));
        phone = findViewById(R.id.phone);
        phone.setText(preferences.getString("phone", ""));
        password = findViewById(R.id.password);
        password.setText(preferences.getString("password", ""));
        passwordConfirmation = findViewById(R.id.passwordConfirmation);
        passwordConfirmation.setText(preferences.getString("password", ""));
    }

    public void update(View view) {
        SharedPreferences myPref = getSharedPreferences("PrefFile", 0);
        String emailText = myPref.getString("email", "");

        userViewModel.getUserByEmail(emailText).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> userList) {
                SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                try {
                    User user = userList.get(0);

                    String fName = firstName.getText().toString();
                    user.setFirstName(fName);
                    editor.putString("firstName", fName);
                    editor.commit();


                    String lName = lastName.getText().toString();
                    user.setLastName(lName);
                    editor.putString("lastName", lName);
                    editor.commit();

                    String add = address.getText().toString();
                    user.setAddress(add);
                    editor.putString("address", add);
                    editor.commit();

                    String ct = city.getText().toString();
                    user.setCity(ct);
                    editor.putString("city", ct);
                    editor.commit();

                    String pCode = postalCode.getText().toString();
                    user.setPostalCode(pCode);
                    editor.putString("postalCode", pCode);
                    editor.commit();

                    String mail = email.getText().toString();
                    user.setEmail(mail);
                    editor.putString("email", mail);
                    editor.commit();

                    String phoneNumber = phone.getText().toString();
                    user.setPhone(phoneNumber);
                    editor.putString("phone", phoneNumber);
                    editor.commit();

                    String pass = password.getText().toString();
                    String passConf = passwordConfirmation.getText().toString();
                    if(pass.equals(passConf)){
                        user.setPassword(pass);
                        editor.putString("password", pass);
                        editor.commit();
                        userViewModel.update(user);
                    }else{
                        Toast.makeText(UpdateActivity.this, "PASSWORDS DON'T MATCH", Toast.LENGTH_LONG).show();
                    }
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