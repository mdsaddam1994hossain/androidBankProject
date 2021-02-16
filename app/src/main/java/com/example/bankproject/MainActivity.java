package com.example.bankproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    EditText fname,lname,phone,email,password,repassword,search;
    Button regButton,logButton,searchButton,display;
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
        search = findViewById(R.id.search_id);
        searchButton = findViewById(R.id.searchButtonId);
        display = findViewById(R.id.display_data_button);

        regButton.setOnClickListener(this);
        logButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        display.setOnClickListener(this);

        CustomerRepo customerRepo = RestClient.getRetrofitInstance().create(CustomerRepo.class);
        customerRepo.getAllCustomer().enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if(response.body().size() > 0 ){
                    fname.setText(response.body().get(0).getFirstName());
                    lname.setText(response.body().get(0).getLastName());
                    phone.setText(response.body().get(0).getPhone());
                    email.setText(response.body().get(0).getEmail());
                    password.setText(response.body().get(0).getPassword());
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

        if(v.getId() == R.id.searchButtonId){
            CustomerRepo customerRepo = RestClient.getRetrofitInstance().create(CustomerRepo.class);
            customerRepo.getCustomerById(Integer.parseInt(search.getText().toString())).enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                    if(response.body() != null){
                        fname.setText(response.body().getFirstName());
                        lname.setText(response.body().getLastName());
                        phone.setText(response.body().getPhone());
                        email.setText(response.body().getEmail());
                        password.setText(response.body().getPassword());
                    }
                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }

        if(v.getId() == R.id.display_data_button){

            Cursor cursor = myDbHelper.displayData();
            if (cursor.getCount() == 0 ){

                showData("Error", "Data not found");
                return;



            }
            StringBuffer buffer = new StringBuffer();

            while (cursor.moveToNext()){
                buffer.append("ID "+ cursor.getString(0)+"\n");
                buffer.append("First Name "+ cursor.getString(1)+"\n");
                buffer.append("Last Name "+ cursor.getString(2)+"\n");
                buffer.append("Phone "+ cursor.getString(3)+"\n");
                buffer.append("Email "+ cursor.getString(4)+"\n");
                buffer.append("password "+ cursor.getString(5)+"\n\n");
            }
            showData("ResultSet", buffer.toString());


        }

    }


    void showData(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();

    }

}