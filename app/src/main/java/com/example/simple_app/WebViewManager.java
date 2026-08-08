package com.example.simple_app;

import android.content.Context;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebViewManager {

    public static WebView create(Context context) {
        WebView webView = new WebView(context);
        webView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setDomStorageEnabled(true);   // <-- ЭТО СТРОКА

        webView.setWebViewClient(new WebViewClient());
        // Загрузка страницы происходит в MainActivity, не здесь
        return webView;
    }
}