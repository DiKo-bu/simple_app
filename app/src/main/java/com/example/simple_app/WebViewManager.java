package com.example.simple_app;

import android.app.Activity;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebViewManager {

    public static WebView create(
            Activity activity) {

        WebView webView =
                new WebView(activity);

        webView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        configure(webView);

        openStartPage(webView);

        return webView;
    }

    private static void configure(
            WebView webView) {

        webView.getSettings()
                .setJavaScriptEnabled(true);

        webView.getSettings()
                .setDomStorageEnabled(true);

        webView.getSettings()
                .setAllowUniversalAccessFromFileURLs(true);

        webView.setWebViewClient(
                new WebViewClient()
        );
    }

    private static void openStartPage(
            WebView webView) {

        webView.loadUrl(
                "file:///android_asset/login.html"
        );
    }
}
