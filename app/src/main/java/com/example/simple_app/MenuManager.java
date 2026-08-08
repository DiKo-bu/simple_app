package com.example.simple_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;

public class MenuManager {

    public static LinearLayout create(Context context, DrawerLayout drawer, WebView webView, String userType) {
        LinearLayout menu = new LinearLayout(context);
        menu.setOrientation(LinearLayout.VERTICAL);
        menu.setBackgroundColor(Color.WHITE);
        menu.setPadding(0, 0, 0, 20);
        menu.setLayoutParams(new DrawerLayout.LayoutParams(
                550,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.START));

        // ----------------------
        // 1. Шапка с аватаром пользователя
        // ----------------------
        LinearLayout header = new LinearLayout(context);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setPadding(25, 40, 25, 30);
        header.setBackgroundColor(0xFF1976D2); // синий фон
        header.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Аватар – круг с первой буквой
        TextView avatar = new TextView(context);
        avatar.setText(String.valueOf(userType.charAt(0)).toUpperCase());
        avatar.setTextColor(Color.WHITE);
        avatar.setTextSize(28);
        avatar.setGravity(Gravity.CENTER);
        avatar.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
        GradientDrawable avatarBg = new GradientDrawable();
        avatarBg.setShape(GradientDrawable.OVAL);
        avatarBg.setColor(0xFF0D47A1);
        avatar.setBackground(avatarBg);

        // Имя пользователя
        TextView name = new TextView(context);
        name.setText(userType.equals("admin") ? "Администратор" : "Инженер");
        name.setTextColor(Color.WHITE);
        name.setTextSize(20);
        name.setPadding(20, 0, 0, 0);
        name.setLayoutParams(new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        header.addView(avatar);
        header.addView(name);
        menu.addView(header);

        // ----------------------
        // 2. Разделитель
        // ----------------------
        View divider = new View(context);
        divider.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1));
        divider.setBackgroundColor(0xFFE0E0E0);
        menu.addView(divider);

        // ----------------------
        // 3. Пункты меню
        // ----------------------
        // Массив пунктов: {заголовок, иконка-символ, действие}
        MenuItem[] items = {
                new MenuItem("", "🏠", new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("file:///android_asset/index.html");
                    }
                }),
                new MenuItem("", "❓", new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("file:///android_asset/about.html");
                    }
                }),
                new MenuItem("", "🔁", new Runnable() {
                    @Override
                    public void run() {
                        // Очищаем данные (если нужно)
                        SharedPreferences prefs = context.getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE);
                        prefs.edit().clear().apply();
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }
                }),
                new MenuItem("", "❌", new Runnable() {
                    @Override
                    public void run() {
                        ((Activity) context).finishAffinity();
                    }
                })
        };

        for (MenuItem item : items) {
            // Горизонтальный контейнер для иконки + текста
            LinearLayout row = new LinearLayout(context);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setPadding(30, 20, 30, 20);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            // Эффект нажатия (ripple) для Android 5.0+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                row.setBackgroundResource(android.R.drawable.btn_default);
            } else {
                row.setBackgroundColor(0x00000000);
            }
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.action.run();
                    drawer.closeDrawers();
                }
            });

            // Иконка
            TextView icon = new TextView(context);
            icon.setText(item.icon);
            icon.setTextSize(24);
            icon.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            icon.setPadding(0, 0, 20, 0);

            // Текст пункта
            TextView text = new TextView(context);
            text.setText(item.title);
            text.setTextSize(18);
            text.setTextColor(0xFF212121);
            text.setLayoutParams(new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            row.addView(icon);
            row.addView(text);
            menu.addView(row);

            // Разделитель после каждого пункта
            View sep = new View(context);
            sep.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 1));
            sep.setBackgroundColor(0xFFEEEEEE);
            menu.addView(sep);
        }

        return menu;
    }

    // Вспомогательный класс для пунктов меню
    private static class MenuItem {
        String title;
        String icon;
        Runnable action;
        MenuItem(String title, String icon, Runnable action) {
            this.title = title;
            this.icon = icon;
            this.action = action;
        }
    }
}