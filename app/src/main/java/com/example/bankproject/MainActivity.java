package com.example.bankproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bankproject.model.Customer;
import com.example.bankproject.restClient.RestClient;
import com.example.bankproject.restService.CustomerRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyDbHelper myDbHelper = new MyDbHelper(this);
    EditText fname,lname,phone,email,password,repassword;
    Button regButton,logButton;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.fname_id);
        lname = findViewById(R.id.lname_id);
        phone = findViewById(R.id.phone_id);
        email = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);
        repassword = findViewById(R.id.repassword_id);
        regButton = findViewById(R.id.regButtonId);
        logButton = findViewById(R.id.loginButtonId);

        regButton.setOnClickListener(this);
        logButton.setOnClickListener(this);

        CustomerRepo customerRepo = RestClient.getRetrofitInstance().create(CustomerRepo.class);
        customerRepo.getAllCustomer().enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if(response.body().size() > 0 ){
                    fname.setText(response.body().get(0).getFirstName());
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                System.out.println("Error "+ t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.regButtonId){
            String finame = fname.getText().toString();
            String laname = lname.getText().toString();
            String rphone = phone.getText().toString();
            String remail = email.getText().toString();
            String rpassword = password.getText().toString();
            myDbHelper.insertDateForRegistration(finame,laname,rphone,remail,rpassword);
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.loginButtonId){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);

        }
    }




}