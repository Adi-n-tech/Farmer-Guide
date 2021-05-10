package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityRegisterBinding mActivityRegisterBinding;
    private DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mDbHelper = new DBHelper(this);
        Initialize();
    }

    private void Initialize() {
        mActivityRegisterBinding.AlAccount.setOnClickListener(this::onClick);
        mActivityRegisterBinding.UserRegister.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.AlAccount:
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.UserRegister:
                String userName = mActivityRegisterBinding.name.getText().toString();
                String userPhone = mActivityRegisterBinding.phone.getText().toString();
                String userAddress = mActivityRegisterBinding.address.getText().toString();
                String userPaaward = mActivityRegisterBinding.passward.getText().toString();
                String userRepassward = mActivityRegisterBinding.rePassward.getText().toString();
                Boolean checkuserphone = mDbHelper.checkUserphone(userPhone);
                if (checkuserphone == true) {
                    Toast.makeText(this, "Already Register", Toast.LENGTH_SHORT).show();
                }
                else if (userName.equals("") || userPhone.equals("") || userAddress.equals("") || userPaaward.equals("") || userRepassward.equals("")) {
                    Toast.makeText(this, "Please enter all the field", Toast.LENGTH_SHORT).show();
                } else {
                    if (userPaaward.equals(userRepassward)) {
                        Boolean checkuser = mDbHelper.checkUserphone(userPhone);
                        if (checkuser == false) {
                            Boolean insert = mDbHelper.insertData(userPhone,userName , userAddress, userPaaward, userRepassward);
                            if (insert == true) {
                                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                break;
        }
    }
}