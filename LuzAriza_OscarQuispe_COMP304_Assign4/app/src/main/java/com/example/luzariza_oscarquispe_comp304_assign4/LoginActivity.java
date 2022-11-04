package com.example.luzariza_oscarquispe_comp304_assign4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private TextView emailText;
    private TextView password;
    private UserRepository userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }
     public void goToRegister(View view){
        Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(register);
     }

     public void login(View view){
        emailText = findViewById(R.id.email);
        password = findViewById(R.id.password);

         userViewModel.getPasswordByEmail(emailText.getText().toString()).observe(this, new Observer<List<User>>() {
             @Override
             public void onChanged(@Nullable List<User> userList) {
                 SharedPreferences preferences = getSharedPreferences("PrefFile", MODE_PRIVATE);
                 SharedPreferences.Editor editor = preferences.edit();
                 String output = "";
                 Log.d("empty", String.valueOf(userList));
                 try {
                     User user = userList.get(0);
                     output = user.getPassword();
                     if (Objects.equals(output, password.getText().toString())) {
                         //saving movie information in shared file
                         editor.putString("session", "True");
                         editor.putString("userName", user.getName());
                         editor.putInt("custId", user.getCustId());
                         editor.putString("email", user.getEmail());
                         editor.commit();
                         finish();
                     } else {
                         Toast.makeText(LoginActivity.this, "WRONG PASSWORD", Toast.LENGTH_LONG).show();
                     }
                 }catch (Exception e){
                     Toast.makeText(LoginActivity.this, "USER NOT FOUND", Toast.LENGTH_LONG).show();
                 }


             }
         });
     }

}