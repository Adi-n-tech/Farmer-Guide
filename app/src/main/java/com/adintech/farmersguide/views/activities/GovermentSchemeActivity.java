package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityGovermentSchemeBinding;

public class GovermentSchemeActivity extends AppCompatActivity {

    private ActivityGovermentSchemeBinding mActivityGovermentSchemeBinding;
    private String url ="https://vikaspedia.in/schemesall/schemes-for-farmers#:~:text=Pradhan%20Mantri%20Krishi%20Sinchai%20Yojana,PM%20Kisan%20Maan%20Dhan%20Yojana";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.goverment_schenes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivityGovermentSchemeBinding = DataBindingUtil.setContentView(this,R.layout.activity_goverment_scheme);
        mActivityGovermentSchemeBinding.WebView.loadUrl(url);
        mActivityGovermentSchemeBinding.WebView.setWebViewClient(new WebView());
    }
}