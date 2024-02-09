package com.example.diplom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.diplom.adapter.ProfileAdapter;
import com.example.diplom.item.ProfileItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Инициализация FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        // Получение ссылки на базу данных Firebase Realtime
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Инициализация элементов пользовательского интерфейса
        editTextName = findViewById(R.id.editTextName);

        // Настройка кнопки сохранения имени
        Button saveNameButton = findViewById(R.id.saveNameButton);
        saveNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNameToDatabase();
            }
        });

        // Настройка кнопки выхода из аккаунта
        ImageButton signOutButton = findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finishAffinity();
            }
        });

        // Загрузка имени пользователя из базы данных и отображение его в EditText
        loadNameFromDatabase();
    }

    private void saveNameToDatabase() {
        // Получаем введенное имя пользователя из EditText
        String name = editTextName.getText().toString().trim();

        // Получаем уникальный идентификатор текущего пользователя
        String userId = mAuth.getCurrentUser().getUid();

        // Создаем объект Map для добавления данных в базу данных
        Map<String, Object> updates = new HashMap<>();
        updates.put("users/" + userId + "/name", name);

        // Выполняем обновление данных в базе данных
        mDatabase.updateChildren(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfileActivity.this, "Имя сохранено успешно", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileActivity.this, "Ошибка при сохранении имени: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadNameFromDatabase() {
        // Получаем уникальный идентификатор текущего пользователя
        String userId = mAuth.getCurrentUser().getUid();

        // Получаем ссылку на узел с именем пользователя
        DatabaseReference userRef = mDatabase.child("users").child(userId).child("name");

        // Добавляем слушатель для получения имени пользователя из базы данных
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Проверяем, существует ли имя пользователя в базе данных
                if (dataSnapshot.exists()) {
                    // Получаем имя пользователя и устанавливаем его в EditText
                    String name = dataSnapshot.getValue(String.class);
                    editTextName.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Обработка ошибки загрузки данных из базы данных
                Toast.makeText(ProfileActivity.this, "Ошибка при загрузке имени: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}