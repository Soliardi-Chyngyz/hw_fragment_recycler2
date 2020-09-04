package com.example.hw_fragment_recycler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    FirstFragment firstFragment = FirstFragment.newInstance();
    Title textResult, textChangeResult;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.first_layout, firstFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void openActivityForResult(){
        Intent intent = new Intent(this,ActivityTwo.class);
        startActivityForResult(intent, 100);
    }
    // обработка items

    // отправляем recycler item на изменения
    public void changeElement (Title title, int position){
        this.position = position;
        Intent intent = new Intent(this, ActivityTwo.class);
        // передаем в активити2 имеющий title
        intent.putExtra("changeKey", title);
        // а при нажатии на button второго фрагмента он возвращает измнененные данные
        startActivityForResult(intent, 110);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            /* добавили полученый результат */
            textResult = (Title) data.getSerializableExtra("title");
            /* вложили textResult в первый фрагмент*/
            firstFragment.addData(textResult);
        }
        else if (requestCode == 110 && resultCode == RESULT_OK) {
            // получаем уже измененые данные и сетим в recycler
            textChangeResult = (Title) data.getSerializableExtra("title");
            firstFragment.changeElement(textChangeResult, position);
        }
    }
}