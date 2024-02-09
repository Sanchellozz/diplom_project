package com.example.diplom.item;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.diplom.R;

public class ProfileItem {
    private String name;
    private String email;

    public ProfileItem(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}