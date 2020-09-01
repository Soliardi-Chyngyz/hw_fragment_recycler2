package com.example.hw_fragment_recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentInterface {
    SecondFragment secondFragment = new SecondFragment();
    FirstFragment firstFragment = FirstFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.second_layout, secondFragment);
        transaction.add(R.id.first_layout, firstFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void sendInfo(Title title) {
        firstFragment.addData(title);
    }
}