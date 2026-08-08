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

        DrawerLayout drawer = new DrawerLayout(this);
        drawer.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Основной контент (тулбар + WebView)
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

        drawer.addView(content); // обязательно первым

        // Меню
        drawer.addView(MenuManager.create(this, drawer, webView));

        // Настройка гамбургера (без ActionBarDrawerToggle – оставим как было)
        // Мы не используем ActionBarDrawerToggle, значит гамбургер не появится.
        // Если нужно – добавим позже.

        setContentView(drawer);
    }
}