package com.adintech.farmersguide.views.activities;

import android.content.ContentValues;
import android.content.Context;
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
/*    public Boolean checkUsername(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where name =?",new String[]{name});
        if (cursor.getCount()>0){
            return true;
        }else return false;
    }*/

    public Boolean checkNamePassward(String phone , String passward){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("select * from users where phone =? and passward=?",new String[]{phone , passward});
        if (cursor.getCount()>0){
            return true;
        }else  return false;
    }
}
