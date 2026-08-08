package com.example.simple_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private EditText etLogin, etPassword;
    private Button btnLogin, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(50, 50, 50, 50);
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Градиентный фон
        GradientDrawable gradientBg = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{0xFFE3F2FD, 0xFFFFFFFF}
        );
        root.setBackground(gradientBg);

        // ---- Заголовок ----
        TextView title = new TextView(this);
        title.setText("Введите данные");
        title.setTextSize(22);
        title.setTextColor(0xFF1A237E);
        title.setGravity(Gravity.CENTER);
        // Внутренний отступ снизу
        title.setPadding(0, 0, 0, 15);
        root.addView(title);

        // ---- Поле "Логин" ----
        etLogin = new EditText(this);
        etLogin.setHint("Логин");
        etLogin.setHintTextColor(0xFF90A4AE);
        etLogin.setTextColor(0xFF212121);
        // Создаём параметры с отступом снизу (marginBottom = 30px)
        LinearLayout.LayoutParams loginParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        loginParams.setMargins(0, 0, 0, 30);
        etLogin.setLayoutParams(loginParams);
        etLogin.setPadding(30, 20, 30, 20); // внутренние отступы

        GradientDrawable editBg = new GradientDrawable();
        editBg.setColor(0xFFFFFFFF);
        editBg.setCornerRadius(40);
        editBg.setStroke(2, 0xFF42A5F5);
        etLogin.setBackground(editBg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            etLogin.setElevation(4);
        }
        root.addView(etLogin);

        // ---- Поле "Пароль" ----
        etPassword = new EditText(this);
        etPassword.setHint("Пароль");
        etPassword.setHintTextColor(0xFF90A4AE);
        etPassword.setTextColor(0xFF212121);
        etPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        LinearLayout.LayoutParams passParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        passParams.setMargins(0, 0, 0, 40); // больший отступ перед кнопками
        etPassword.setLayoutParams(passParams);
        etPassword.setPadding(30, 20, 30, 20);

        GradientDrawable editBg2 = new GradientDrawable();
        editBg2.setColor(0xFFFFFFFF);
        editBg2.setCornerRadius(15);
        editBg2.setStroke(2, 0xFF42A5F5);
        etPassword.setBackground(editBg2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            etPassword.setElevation(4);
        }
        root.addView(etPassword);

        // ---- Кнопка "Войти" ----
        btnLogin = new Button(this);
        btnLogin.setText("Войти");
        btnLogin.setTextColor(0xFFFFFFFF);
        btnLogin.setTextSize(18);
        LinearLayout.LayoutParams btnLoginParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        btnLoginParams.setMargins(0, 0, 0, 20);
        btnLogin.setLayoutParams(btnLoginParams);
        btnLogin.setPadding(30, 30, 30, 30);

        GradientDrawable btnBg = new GradientDrawable();
        btnBg.setColor(0xFF1976D2);
        btnBg.setCornerRadius(15);
        btnLogin.setBackground(btnBg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnLogin.setElevation(8);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etLogin.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();

                String userType = null;
                if (login.equals("admin") && pass.equals("admin")) {
                    userType = "admin";
                } else if (login.equals("engineer") && pass.equals("engineer")) {
                    userType = "engineer";
                }

                if (userType != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user_type", userType);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });
        root.addView(btnLogin);

        // ---- Кнопка "Выход" ----
        btnExit = new Button(this);
        btnExit.setText("Выход");
        btnExit.setTextColor(0xFF1976D2);
        btnExit.setTextSize(18);
        // Для последней кнопки отступ снизу можно не ставить, чтобы не было лишнего пространства
        LinearLayout.LayoutParams btnExitParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        btnExitParams.setMargins(0, 0, 0, 0);
        btnExit.setLayoutParams(btnExitParams);
        btnExit.setPadding(30, 30, 30, 30);

        GradientDrawable btnExitBg = new GradientDrawable();
        btnExitBg.setColor(0x00000000);
        btnExitBg.setCornerRadius(15);
        btnExitBg.setStroke(2, 0xFF1976D2);
        btnExit.setBackground(btnExitBg);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        root.addView(btnExit);

        setContentView(root);
    }
}