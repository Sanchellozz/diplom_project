package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diplom.adapter.CustomPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Инициализация ViewPager и TabLayout
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Создание собственного адаптера для ViewPager
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Установка иконок и текста для каждой вкладки
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.custom_tab_layout);
                ImageView tabIcon = tab.getCustomView().findViewById(R.id.tab_icon);
                TextView tabText = tab.getCustomView().findViewById(R.id.tab_text);
                switch (i) {
                    case 0:
                        tabIcon.setImageResource(R.drawable.ic_calendar);
                        break;
                    case 1:
                        tabIcon.setImageResource(R.drawable.ic_todo_list);
                        break;
                    case 2:
                        tabIcon.setImageResource(R.drawable.ic_timeline_big);
                        break;
                    case 3:
                        tabIcon.setImageResource(R.drawable.ic_habit);
                        break;
                    case 4:
                        tabIcon.setImageResource(R.drawable.ic_general);
                        break;
                }
            }
        }

        // Добавление слушателя событий для изменения размера вкладок
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    customView.setScaleX(1.2f);
                    customView.setScaleY(1.2f);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    customView.setScaleX(1.0f);
                    customView.setScaleY(1.0f);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Ничего не делать при повторном выборе вкладки
            }
        });
    }
}