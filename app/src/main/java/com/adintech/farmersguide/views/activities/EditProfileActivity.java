package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityEditProfileBinding mActivityEditProfileBinding;
    private DBHelper dbHelper;
    private String userName, phone, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEditProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        getSupportActionBar().hide();
        dbHelper = new DBHelper(EditProfileActivity.this);
        Initialize();

    }

    private void Initialize() {
        //get data
        Cursor cursor = dbHelper.getData();
        if (cursor.moveToFirst()) {
            userName = cursor.getString(cursor.getColumnIndex("name"));
            phone = cursor.getString(cursor.getColumnIndex("phone"));
            address = cursor.getString(cursor.getColumnIndex("address"));
        }

        //set data
        mActivityEditProfileBinding.address.setText(address);
        mActivityEditProfileBinding.phone.setText(phone);
        mActivityEditProfileBinding.name.setText(userName);

        // get Variable
        mActivityEditProfileBinding.update.setOnClickListener(this::onClick);
        mActivityEditProfileBinding.logout.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.update:
                dbHelper.updateData(mActivityEditProfileBinding.phone.getText().toString(), mActivityEditProfileBinding.name.getText().toString(), mActivityEditProfileBinding.address.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(EditProfileActivity.this, "Data Updated..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(EditProfileActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(this, LoginActivity.class);
                startActivity(it);
                break;

        }
    }
}