package com.example.simple_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private Button btnLogin;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE);
        if (prefs.getBoolean("logged_in", false)) {
            // Если уже залогинен – сразу в MainActivity (но без типа – используем default)
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(50, 50, 50, 50);
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        TextView title = new TextView(this);
        title.setText("Авторизация");
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 50);
        root.addView(title);

        etLogin = new EditText(this);
        etLogin.setHint("Логин");
        etLogin.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        etLogin.setPadding(20, 20, 20, 20);
        root.addView(etLogin);

        etPassword = new EditText(this);
        etPassword.setHint("Пароль");
        etPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPassword.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        etPassword.setPadding(20, 20, 20, 20);
        root.addView(etPassword);

        btnLogin = new Button(this);
        btnLogin.setText("Войти");
        btnLogin.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        btnLogin.setPadding(20, 20, 20, 20);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etLogin.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();

                String userType = null;

                // Проверка пар (логин -> пароль -> тип)
                if (login.equals("admin") && pass.equals("admin")) {
                    userType = "admin";
                } else if (login.equals("engineer") && pass.equals("engineer")) {
                    userType = "engineer";
                }

                if (userType != null) {
                    // Сохраняем флаг
                    prefs.edit().putBoolean("logged_in", true).apply();
                    // Переход на главный экран с типом
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

        setContentView(root);
    }
}