package com.example.msgproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Camera_frag camera_frag;
    private Search_frag search_frag;
    private Main_frag main_frag;
    private Rank_frag rank_frag;
    private User_frag user_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("MSG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.actionbar_icon);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.action_main);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_camera:
                        setFrag(0);
                        break;

                    case R.id.action_search:
                        setFrag(1);
                        break;

                    case R.id.action_main:
                        setFrag(2);
                        break;

                    case R.id.action_rank:
                        setFrag(3);
                        break;

                    case R.id.action_user:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });
        camera_frag = new Camera_frag();
        search_frag = new Search_frag();
        main_frag = new Main_frag();
        rank_frag = new Rank_frag();
        user_frag = new User_frag();
        
        setFrag(2); //앱 시작시 화면 설정
    }

    // 프래그먼트 교체 발생 실행문
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, camera_frag);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.main_frame, search_frag);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.main_frame, main_frag);
                ft.commit();
                break;

            case 3:
                ft.replace(R.id.main_frame, rank_frag);
                ft.commit();
                break;

            case 4:
                ft.replace(R.id.main_frame, user_frag);
                ft.commit();
                break;
        }

    }
}