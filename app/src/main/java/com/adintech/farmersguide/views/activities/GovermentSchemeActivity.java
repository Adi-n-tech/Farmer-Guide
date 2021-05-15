package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityGovermentSchemeBinding;

public class GovermentSchemeActivity extends AppCompatActivity {

    private ActivityGovermentSchemeBinding mActivityGovermentSchemeBinding;
    private ProgressDialog mProgressDialog;
    private String url = "https://vikaspedia.in/schemesall/schemes-for-farmers#:~:text=Pradhan%20Mantri%20Krishi%20Sinchai%20Yojana,PM%20Kisan%20Maan%20Dhan%20Yojana";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.goverment_schenes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProgressDialog = new ProgressDialog(GovermentSchemeActivity.this);
        mProgressDialog.setMessage("Loading...");
        mActivityGovermentSchemeBinding = DataBindingUtil.setContentView(this, R.layout.activity_goverment_scheme);
        mActivityGovermentSchemeBinding.WebView.setWebViewClient(new WebView(mProgressDialog));
        mActivityGovermentSchemeBinding.WebView.getSettings().setLoadsImagesAutomatically(true);
        mActivityGovermentSchemeBinding.WebView.getSettings().setJavaScriptEnabled(true);
        mActivityGovermentSchemeBinding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mActivityGovermentSchemeBinding.WebView.loadUrl(url);
    }

    public class WebView extends WebViewClient {
        ProgressDialog progressDialog;


        @Override
        public void onPageFinished(android.webkit.WebView view, String url) {
            super.onPageFinished(view, url);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        public WebView(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
            progressDialog.show();

        }
    }
}