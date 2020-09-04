package com.example.hw_fragment_recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class ActivityTwo extends AppCompatActivity {
    SecondFragment secondFragment = new SecondFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.second_layout, secondFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // получение новых данных на обработку и возврат с этими данными по кнопке
    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        if (intent != null) {
            // получили значение через ключ
            Title title = (Title) intent.getSerializableExtra("changeKey");
            if (title != null) {
                // положил и передал в фрагмент2
                secondFragment.getElement(title);
            }
        }
    }

    public void sendResult(Title title){
        // через intent передаем поля
        Intent intent = new Intent();
        intent.putExtra("title", title);
        setResult(RESULT_OK, intent);
        finish();
    }
}