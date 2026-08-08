package com.example.simple_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.webkit.WebView;
import android.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends Activity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        content.addView(toolbar);

        // WebView
        WebView webView = WebViewManager.create(this);
        content.addView(webView);

        drawer.addView(content); // обязательно первым

        // Меню с передачей WebView
        drawer.addView(MenuManager.create(this, drawer, webView));

        // Настройка тулбара как ActionBar
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        setContentView(drawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawer.openDrawer(Gravity.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}