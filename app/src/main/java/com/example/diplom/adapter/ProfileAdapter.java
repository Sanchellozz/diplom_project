package com.example.diplom.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.diplom.R;
import com.example.diplom.item.ProfileItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<ProfileItem> profileItemList;

    public ProfileAdapter(List<ProfileItem> profileItemList) {
        this.profileItemList = profileItemList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        ProfileItem item = profileItemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return profileItemList.size();
    }

    static class ProfileViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewValue;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewName);
            textViewValue = itemView.findViewById(R.id.textViewEmail);
        }

        public void bind(ProfileItem item) {
            textViewTitle.setText(item.getName());
            textViewValue.setText(item.getEmail());
        }
    }
}