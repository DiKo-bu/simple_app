package com.example.simple_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.Gravity;
import android.text.Html;   // ← добавил для HTML

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView left = new TextView(this);
        left.setText("Слева");
        left.setGravity(Gravity.START);
        left.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        TextView center = new TextView(this);
        center.setText(Html.fromHtml("<h1>Центр</h1>", Html.FROM_HTML_MODE_LEGACY));
        center.setGravity(Gravity.CENTER);
        center.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        TextView right = new TextView(this);
        right.setText("Справа");
        right.setGravity(Gravity.END);
        right.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        layout.addView(left);
        layout.addView(center);
        layout.addView(right);
        setContentView(layout);
    }
}