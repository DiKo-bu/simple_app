package com.example.simple_app;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;

public class MenuManager {

    public static LinearLayout create(Context context, DrawerLayout drawer) {
        LinearLayout menu = new LinearLayout(context);
        menu.setOrientation(LinearLayout.VERTICAL);
        menu.setBackgroundColor(0xFFF0F0F0);
        menu.setPadding(50, 50, 50, 50);

        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
                400,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.START;
        menu.setLayoutParams(params);

        String[] items = {"Главная", "Настройки", "О нас"};
        for (String item : items) {
            TextView tv = new TextView(context);
            tv.setText(item);
            tv.setTextSize(20);
            tv.setPadding(10, 20, 10, 20);
            tv.setOnClickListener(v -> {
                // Здесь можно добавить логику, например, сменить URL WebView
                drawer.closeDrawers();
            });
            menu.addView(tv);
        }
        return menu;
    }
}