package com.example.diplom.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diplom.CalendarFragment;
import com.example.diplom.GeneralFragment;
import com.example.diplom.HabitFragment;
import com.example.diplom.R;
import com.example.diplom.TimelineFragment;
import com.example.diplom.TodoListFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CustomPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGES = 5;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CalendarFragment();
            case 1:
                return new TodoListFragment();
            case 2:
                return new TimelineFragment();
            case 3:
                return new HabitFragment();
            case 4:
                return new GeneralFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}