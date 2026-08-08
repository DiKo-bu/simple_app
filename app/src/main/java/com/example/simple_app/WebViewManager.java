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

        // === НОВЫЕ НАСТРОЙКИ ===
        webView.getSettings().setJavaScriptEnabled(true);          // включаем JS
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true); // разрешаем fetch из file://

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/index.html");
        return webView;
    }
}