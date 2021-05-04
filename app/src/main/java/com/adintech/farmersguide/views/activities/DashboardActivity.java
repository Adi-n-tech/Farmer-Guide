package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding mActivityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        mActivityDashboardBinding.cropsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CropListActivity.class);
                startActivity(intent);
            }
        });

        mActivityDashboardBinding.cardVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, YoutubeVideosActivity.class);
                startActivity(intent);
            }
        });

        mActivityDashboardBinding.cardModernInstruments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ModernInstrumentsActivity.class);
                startActivity(intent);
            }
        });

        mActivityDashboardBinding.cropsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CropListActivity.class);
                startActivity(intent);
            }
        });
    }
}