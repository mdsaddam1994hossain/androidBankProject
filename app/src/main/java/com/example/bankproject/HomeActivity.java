package com.example.bankproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        emailText = findViewById(R.id.emailTestId);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            String email = bundle.getString("email");
            emailText.setText(email);
        }
    }
}