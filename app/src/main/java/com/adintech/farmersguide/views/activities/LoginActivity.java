package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.Util.preference.AppPreferencesManager;
import com.adintech.farmersguide.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding mActivityLoginBinding;
    private DBHelper mDbHelper;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        getSupportActionBar().hide();
        mDbHelper = new DBHelper(this);
        Initialize();
    }

    private void Initialize() {
        mActivityLoginBinding.Register.setOnClickListener(this::onClick);
        mActivityLoginBinding.Login.setOnClickListener(this::onClick);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent = new Intent(LoginActivity.this, SelectLanguageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.Login:
                String userPhone = mActivityLoginBinding.phone.getText().toString();
                AppPreferencesManager.putString(AppConstants.PREFERENCE_KEYS.PHONE, userPhone, LoginActivity.this);
                String userPassward = mActivityLoginBinding.passward.getText().toString();
                //Create SheredPreference

                if (userPhone.equals("") || userPassward.equals("")) {
                    Toast.makeText(this, "Please entered all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = mDbHelper.checkPhoneNoPassward(userPhone, userPassward);
                    if (checkUserPass) {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                        AppPreferencesManager.putBoolean(AppConstants.PREFERENCE_KEYS.ISLOGIN, true, this);
                        intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);

                    } else Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}