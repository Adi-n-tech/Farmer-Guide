package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.databinding.ActivityExpertCallServiceBinding;

public class ExpertCallService extends AppCompatActivity {
    private ActivityExpertCallServiceBinding mActivityExpertCallServiceBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Expert Advice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivityExpertCallServiceBinding = DataBindingUtil.setContentView(this, R.layout.activity_expert_call_service);
        Initialize();
    }

    private void Initialize() {
        mActivityExpertCallServiceBinding.Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+18001801551";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);

            }
        });
    }

}