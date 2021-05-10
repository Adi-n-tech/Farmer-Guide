package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding mActivityLoginBinding;
    private DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        mDbHelper = new DBHelper(this);
        Initialize();
    }

    private void Initialize() {
        mActivityLoginBinding.Register.setOnClickListener(this::onClick);
        mActivityLoginBinding.Login.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.Register: intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.Login:

                String userPhone = mActivityLoginBinding.phone.getText().toString();
                String userPassward = mActivityLoginBinding.passward.getText().toString();
                if (userPhone.equals("")||userPassward.equals("")){
                    Toast.makeText(this, "Please entered all fields", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUserPass = mDbHelper.checkNamePassward(userPhone,userPassward);
                    if (checkUserPass==true){
                        Toast.makeText(this, "sign in successfull", Toast.LENGTH_SHORT).show();
                        intent = new Intent(LoginActivity.this,DashboardActivity.class);
                        startActivity(intent);
                    }else Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}