package com.example.bookish;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ------------------
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        // _________________
        tabLayout = findViewById(R.id.tablayout);


        // ===================================
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, new UponnashFragment());
        fragmentTransaction.commit();
        // ===================================




        // ++++++++++++++++++On Click Listeners+++++++++++++++
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();

                if (tabPosition == 0 ){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout, new UponnashFragment());
                    fragmentTransaction.commit();

                } else if (tabPosition == 1) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout, new KobitaFragment());
                    fragmentTransaction.commit();

                }
                else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout, new ShahittoFragment());
                    fragmentTransaction.commit();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    }
}