package com.example.simple_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
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

        // Пункты меню
        Item[] items = {
                new Item("Главная", new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("file:///android_asset/index.html");
                    }
                }),
                new Item("О нас", new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("file:///android_asset/about.html");
                    }
                }),
                new Item("Сменить аккаунт", new Runnable() {
                    @Override
                    public void run() {
                        // Очищаем флаг
                        SharedPreferences prefs = context.getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE);
                        prefs.edit().remove("logged_in").apply();
                        // Переход на Login
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                        // Закрываем текущую Activity
                        ((Activity) context).finish();
                    }
                }),
                new Item("Выход", new Runnable() {
                    @Override
                    public void run() {
                        // Закрываем приложение (завершаем все Activity)
                        ((Activity) context).finishAffinity();
                    }
                })
        };

        for (Item item : items) {
            TextView tv = new TextView(context);
            tv.setText(item.text);
            tv.setTextSize(20);
            tv.setPadding(10, 20, 10, 20);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.action.run();
                    drawer.closeDrawers();
                }
            });
            menu.addView(tv);
        }

        return menu;
    }

    private static class Item {
        String text;
        Runnable action;
        Item(String text, Runnable action) {
            this.text = text;
            this.action = action;
        }
    }
}