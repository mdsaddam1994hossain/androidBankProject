package com.example.bankproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


  //  MyDbHelper myDbHelper;

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
     //   display = findViewById(R.id.display_data_button);

        loginButton.setOnClickListener(this);
        regButton.setOnClickListener(this);
     //   display.setOnClickListener(this);
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
//        if(v.getId() == R.id.display_data_button){
//
//          Cursor cursor = myDbHelper.displayData();
//            if (cursor.getCount() == 0 ){
//
//                showData("Error", "Data not found");
//                return;
//
//
//
//            }
//            StringBuffer buffer = new StringBuffer();
//
//            while (cursor.moveToNext()){
//                buffer.append("ID "+ cursor.getString(0)+"\n");
//                buffer.append("First Name "+ cursor.getString(1)+"\n");
//                buffer.append("Last Name "+ cursor.getString(2)+"\n");
//                buffer.append("Phone "+ cursor.getString(3)+"\n");
//                buffer.append("Email "+ cursor.getString(4)+"\n");
//                buffer.append("password "+ cursor.getString(5)+"\n\n");
//            }
//            showData("ResultSet", buffer.toString());
//
//        }
    }

//    void showData(String title,String message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(title);
//            builder.setMessage(message);
//            builder.setCancelable(true);
//            builder.show();
//
//    }
}