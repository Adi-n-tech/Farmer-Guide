package com.adintech.farmersguide.views.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "User.db";

    public DBHelper( Context context) {
        super(context,DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users(phone TEXT primary key,name TEXT ,address TEXT,passward TEXT , cpassward TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String phone, String name, String address, String passward , String cpassward)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone",phone);
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("passward",passward);
        contentValues.put("cpassward",cpassward);
        long result = MyDB.insert("users",null,contentValues);
        if (result==-1){
            return false;
        }else return true;
    }
    public Boolean checkUserphone(String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where phone =?",new String[]{phone});
        if (cursor.getCount()>0){
            return true;
        }else return false;
    }
    public void Updatedata(String Originalname, String name,String phone, String address) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("name", name);
        values.put("phone", phone);
        values.put("address", address);

        db.update("users", values, "name=?", new String[]{Originalname});
        db.close();
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users", null);
        return cursor;
    }

    public Boolean checkNamePassward(String phone , String passward){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("select * from users where phone =? and passward=?",new String[]{phone , passward});
        if (cursor.getCount()>0){
            return true;
        }else  return false;
    }
}
