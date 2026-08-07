package com.example.app;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);

        root.setOrientation(LinearLayout.VERTICAL);

        root.setGravity(Gravity.CENTER);

        TextView hello = new TextView(this);

        hello.setText("Hello, Android!");

        hello.setTextSize(24);

        root.addView(hello);

        setContentView(root);

    }

}