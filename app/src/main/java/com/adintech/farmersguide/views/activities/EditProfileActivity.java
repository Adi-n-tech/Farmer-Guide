package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adintech.farmersguide.Models.SharesPreferences;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityEditProfileBinding;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityEditProfileBinding mActivityEditProfileBinding;
    private DBHelper dbHelper;
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEditProfileBinding = DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);
        getSupportActionBar().hide();
        dbHelper = new DBHelper(EditProfileActivity.this);
        Initialize();

    }

    private void Initialize() {
        // get Variable



        SharesPreferences sharesPreferences = new SharesPreferences(this);
        HashMap<String,String> userDetail = sharesPreferences.getUserDetailFromSession();
        String fullname = userDetail.get(sharesPreferences.NAME);
        String phone = userDetail.get(sharesPreferences.PHONE);
        String Address = userDetail.get(sharesPreferences.ADDRESS);
        mActivityEditProfileBinding.name.setText(fullname);
        mActivityEditProfileBinding.phone.setText(phone);
        mActivityEditProfileBinding.address.setText(Address);

        mActivityEditProfileBinding.update.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        String uname = getIntent().getStringExtra("name");
        String uPhone = getIntent().getStringExtra("phone");
        String uAddress =  getIntent().getStringExtra("address");


        switch (v.getId()){
            case R.id.update:
                dbHelper.Updatedata(uname,mActivityEditProfileBinding.name.getText().toString(),mActivityEditProfileBinding.phone.getText().toString(),mActivityEditProfileBinding.address.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(EditProfileActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}