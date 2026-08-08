package com.example.simple_app;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Получаем тип пользователя
        String userType = getIntent().getStringExtra("user_type");
        if (userType == null) {
            userType = "admin";
        }

        drawer = new DrawerLayout(this);
        drawer.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Основной контент
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(new DrawerLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Тулбар
        Toolbar toolbar = ToolbarManager.create(this);
        setSupportActionBar(toolbar);
        content.addView(toolbar);

        // WebView
        WebView webView = WebViewManager.create(this);
        content.addView(webView);

        drawer.addView(content); // обязательно первым

        // Меню
        drawer.addView(MenuManager.create(this, drawer, webView));

        // Настройка гамбургера
        toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.open_drawer,   // строка для доступности
                R.string.close_drawer   // строка для доступности
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();  // синхронизируем состояние (гамбургер ↔ стрелка)

        setContentView(drawer);

        // Загружаем страницу по типу
        String page;
        if ("admin".equals(userType)) {
            page = "index.html";
        } else if ("engineer".equals(userType)) {
            page = "engineer.html";
        } else {
            page = "index.html";
        }
        webView.loadUrl("file:///android_asset/" + page);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}