package com.example.simple_app;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;   // ← правильный импорт

public class ToolbarManager {

    public static Toolbar create(Context context) {
        Toolbar toolbar = new Toolbar(context);
        toolbar.setTitle("Моё приложение");
        toolbar.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return toolbar;
    }
}