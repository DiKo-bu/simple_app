package com.example.simple_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
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

        // Корневой контейнер
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(50, 50, 50, 50);
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // ---- СОВРЕМЕННЫЙ ДИЗАЙН ----

        // 1. Градиентный фон (светло-голубой → белый)
        GradientDrawable gradientBg = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{0xFFE3F2FD, 0xFFFFFFFF}
        );
        root.setBackground(gradientBg);

        // 2. Заголовок
        TextView title = new TextView(this);
        title.setText("Авторизация");
        title.setTextSize(32);
        title.setTextColor(0xFF1A237E); // тёмно-синий
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 50);
        root.addView(title);

        // 3. Поле "Логин" – с закруглённым фоном и обводкой
        etLogin = new EditText(this);
        etLogin.setHint("Логин");
        etLogin.setHintTextColor(0xFF90A4AE);
        etLogin.setTextColor(0xFF212121);
        etLogin.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        etLogin.setPadding(30, 30, 30, 30);
        // фон
        GradientDrawable editBg = new GradientDrawable();
        editBg.setColor(0xFFFFFFFF);
        editBg.setCornerRadius(40);
        editBg.setStroke(2, 0xFF42A5F5);
        etLogin.setBackground(editBg);
        // тень для полей (только API 21+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            etLogin.setElevation(4);
        }
        root.addView(etLogin);

        // 4. Поле "Пароль" – аналогично
        etPassword = new EditText(this);
        etPassword.setHint("Пароль");
        etPassword.setHintTextColor(0xFF90A4AE);
        etPassword.setTextColor(0xFF212121);
        etPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPassword.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        etPassword.setPadding(30, 30, 30, 30);
        GradientDrawable editBg2 = new GradientDrawable();
        editBg2.setColor(0xFFFFFFFF);
        editBg2.setCornerRadius(40);
        editBg2.setStroke(2, 0xFF42A5F5);
        etPassword.setBackground(editBg2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            etPassword.setElevation(4);
        }
        root.addView(etPassword);

        // 5. Кнопка "Войти" – цветная, закруглённая, с тенью
        btnLogin = new Button(this);
        btnLogin.setText("Войти");
        btnLogin.setTextColor(0xFFFFFFFF);
        btnLogin.setTextSize(18);
        btnLogin.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        btnLogin.setPadding(30, 30, 30, 30);
        GradientDrawable btnBg = new GradientDrawable();
        btnBg.setColor(0xFF1976D2); // синий
        btnBg.setCornerRadius(40);
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

        // 6. Кнопка "Выход" – прозрачная, с обводкой
        btnExit = new Button(this);
        btnExit.setText("Выход");
        btnExit.setTextColor(0xFF1976D2);
        btnExit.setTextSize(18);
        btnExit.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        btnExit.setPadding(30, 30, 30, 30);
        GradientDrawable btnExitBg = new GradientDrawable();
        btnExitBg.setColor(0x00000000); // прозрачный
        btnExitBg.setCornerRadius(40);
        btnExitBg.setStroke(2, 0xFF1976D2);
        btnExit.setBackground(btnExitBg);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // закрывает приложение
            }
        });
        root.addView(btnExit);

        setContentView(root);
    }
}