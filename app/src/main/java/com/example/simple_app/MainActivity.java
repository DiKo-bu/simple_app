package com.example.simple_app;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Получаем тип пользователя из Intent (если нет – по умолчанию "admin")
        String userType = getIntent().getStringExtra("user_type");
        if (userType == null) {
            // Если не передан (например, при автозаходе через SharedPreferences),
            // можно считать, что это admin (или взять из сохранённых настроек)
            userType = "admin";
        }

        DrawerLayout drawer = new DrawerLayout(this);
        drawer.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(new DrawerLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Тулбар
        Toolbar toolbar = ToolbarManager.create(this);
        setSupportActionBar(toolbar);
        content.addView(toolbar);

        // WebView (без загрузки)
        WebView webView = WebViewManager.create(this);
        content.addView(webView);

        drawer.addView(content);

        // Меню (передаём WebView для возможных действий)
        drawer.addView(MenuManager.create(this, drawer, webView));

        setContentView(drawer);

        // Загружаем страницу в зависимости от типа пользователя
        String page;
        if ("admin".equals(userType)) {
            page = "index.html";
        } else if ("engineer".equals(userType)) {
            page = "engineer.html";
        } else {
            page = "error.html"; // fallback
        }
        webView.loadUrl("file:///android_asset/" + page);
    }
}