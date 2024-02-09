package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // Пользователь не вошел в систему, перенаправляем на страницу входа
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish(); // Завершаем текущую активность, чтобы пользователь не мог вернуться на нее
        } else {
            // Пользователь уже вошел в систему, перенаправляем на главный экран
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish(); // Завершаем текущую активность, чтобы пользователь не мог вернуться на нее
        }
    }
}