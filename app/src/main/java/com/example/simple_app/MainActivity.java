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

        // Получаем тип пользователя (передан из LoginActivity)
        String userType = getIntent().getStringExtra("user_type");
        if (userType == null) {
            userType = "admin"; // fallback
        }

        // Создаём DrawerLayout
        drawer = new DrawerLayout(this);
        drawer.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Основной контент (LinearLayout с тулбаром и WebView)
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(new DrawerLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Тулбар (androidx)
        Toolbar toolbar = ToolbarManager.create(this);
        setSupportActionBar(toolbar);
        content.addView(toolbar);

        // WebView
        WebView webView = WebViewManager.create(this);
        content.addView(webView);

        // Добавляем контент в DrawerLayout (обязательно первым)
        drawer.addView(content);

        // Добавляем меню (с передачей userType)
        drawer.addView(MenuManager.create(this, drawer, webView, userType));

        // Настраиваем гамбургер (ActionBarDrawerToggle)
        toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.open_drawer,   // строка из strings.xml
                R.string.close_drawer   // строка из strings.xml
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState(); // синхронизируем состояние иконки

        setContentView(drawer);

        // Загружаем страницу в зависимости от роли
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
        // Обработка нажатия гамбургера (передаём событие в toggle)
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}