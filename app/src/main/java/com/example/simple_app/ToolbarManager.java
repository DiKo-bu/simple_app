package com.example.simple_app;

import android.content.Context;
import android.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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