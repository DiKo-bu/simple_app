package com.example.simple_app;

import android.app.Activity;                     // ← добавлен импорт
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;

public class MenuManager {

    public static LinearLayout create(Context context, DrawerLayout drawer, WebView webView) {
        LinearLayout menu = new LinearLayout(context);
        menu.setOrientation(LinearLayout.VERTICAL);
        menu.setBackgroundColor(0xFFF0F0F0);
        menu.setPadding(50, 50, 50, 50);

        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
                400,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.START;
        menu.setLayoutParams(params);

        String[] items = {"Главная", "О нас", "Выход"};
        for (String item : items) {
            TextView tv = new TextView(context);
            tv.setText(item);
            tv.setTextSize(20);
            tv.setPadding(10, 30, 10, 30);
            tv.setOnClickListener(v -> {
                switch (item) {
                    case "Главная":
                        webView.loadUrl("file:///android_asset/index.html");
                        break;
                    case "О нас":
                        webView.loadUrl("file:///android_asset/about.html");
                        break;
                    case "Выход":
                        ((Activity) context).finish();
                        break;
                }
                drawer.closeDrawers();
            });
            menu.addView(tv);
        }
        return menu;
    }
}