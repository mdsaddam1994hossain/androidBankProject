package com.example.bankproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email,password;
    Button loginButton,regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);
        loginButton = findViewById(R.id.login_Button_Id);
        regButton = findViewById(R.id.reg_Button_Id);

        loginButton.setOnClickListener(this);
        regButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_Button_Id){
            String useremail = email.getText().toString();
            String userpass = password.getText().toString();
            Intent intent = new Intent(this,HomeActivity.class);
            intent.putExtra("email",useremail);
            startActivity(intent);
        }
        if(v.getId() == R.id.reg_Button_Id){
            Intent intent =new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}