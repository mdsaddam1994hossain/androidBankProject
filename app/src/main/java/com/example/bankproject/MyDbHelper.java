package com.example.bankproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    static  final String DB_NAME = "onlineBank";
    static final int VERSION =1;
    static final String TABLE_NAME = "customer";
    static final String CREATE_TABLE = "create table if not exists "+TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,first_name VARCHAR,last_name VARCHAR,phone VARCHAR,email VARCHAR, password VARCHAR)";
    static final String DROP_TABLE = "drop table if exists "+ TABLE_NAME ;

    public MyDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            }catch (Exception e){
                System.out.println("Exception "+ e);
            }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            System.out.println("Exception "+ e);
        }
    }

    public void insertDateForRegistration(String first_name,String last_name,String phone, String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues content = new ContentValues();
            content.put("first_name",first_name);
            content.put("last_name",last_name);
            content.put("phone",phone);
            content.put("email",email);
            content.put("password",password);
            sqLiteDatabase.insert(TABLE_NAME,null,content);

    }


    public  Cursor displayData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" select * from customer ",null);
        return  cursor;
    }
}
