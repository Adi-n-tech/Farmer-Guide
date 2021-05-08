package com.adintech.farmersguide.views.activities;

import android.webkit.WebResourceRequest;
import android.webkit.WebViewClient;

public class WebView extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
