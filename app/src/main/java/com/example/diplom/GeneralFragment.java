package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.diplom.adapter.ProfileAdapter;
import com.example.diplom.item.ProfileItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class GeneralFragment extends Fragment {
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private ProfileAdapter profileAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general, container, false);

        // Инициализация FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        List<ProfileItem> profileItemList = new ArrayList<>();

        // Инициализация RecyclerView
        recyclerView = view.findViewById(R.id.profileRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        profileAdapter = new ProfileAdapter(profileItemList);
        recyclerView.setAdapter(profileAdapter);

        Button profileButton = view.findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на страницу профиля (ProfileActivity)
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });

        return view;
    }
}