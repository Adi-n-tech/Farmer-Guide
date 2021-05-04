package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {
private ActivityDashboardBinding mActivityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDashboardBinding = DataBindingUtil.setContentView(this,R.layout.activity_dashboard);

        mActivityDashboardBinding.cropsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CropListActivity.class);
                startActivity(intent);
            }
        });
    }
}