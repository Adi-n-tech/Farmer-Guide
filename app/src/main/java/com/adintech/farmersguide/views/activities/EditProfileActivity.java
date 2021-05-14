package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
        mActivityEditProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        getSupportActionBar().hide();
        dbHelper = new DBHelper(EditProfileActivity.this);
        Initialize();

    }

    private void Initialize() {
        // get Variable


        SharesPreferences sharesPreferences = new SharesPreferences(this);
        HashMap<String, String> userDetail = sharesPreferences.getUserDetailFromSession();
        String fullname = userDetail.get(sharesPreferences.NAME);
        String phone = userDetail.get(sharesPreferences.PHONE);
        String Address = userDetail.get(sharesPreferences.ADDRESS);
        mActivityEditProfileBinding.name.setText(fullname);
        mActivityEditProfileBinding.phone.setText(phone);
        mActivityEditProfileBinding.address.setText(Address);

        mActivityEditProfileBinding.update.setOnClickListener(this::onClick);
        mActivityEditProfileBinding.logout.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        String uname = getIntent().getStringExtra("name");
        String uPhone = getIntent().getStringExtra("phone");
        String uAddress = getIntent().getStringExtra("address");


        switch (v.getId()) {
            case R.id.update:
                dbHelper.Updatedata(uname, mActivityEditProfileBinding.name.getText().toString(), mActivityEditProfileBinding.phone.getText().toString(), mActivityEditProfileBinding.address.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(EditProfileActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(EditProfileActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                SharesPreferences.logout();

                Intent it = new Intent(this, LoginActivity.class);
                startActivity(it);
                break;

        }
    }
}