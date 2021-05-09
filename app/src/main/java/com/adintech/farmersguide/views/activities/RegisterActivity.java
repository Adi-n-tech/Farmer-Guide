package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityRegisterBinding mActivityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        Initialize();
    }

    private void Initialize() {
        mActivityRegisterBinding.AlAccount.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.AlAccount: intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            break;
        }
    }
}