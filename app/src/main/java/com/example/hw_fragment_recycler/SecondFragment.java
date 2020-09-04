package com.example.hw_fragment_recycler;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SecondFragment extends Fragment  {
    public EditText etName, etSurname, etAge;
    public Button btnSend;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etName = view.findViewById(R.id.etName);
        etSurname = view.findViewById(R.id.etSurname);
        etAge = view.findViewById(R.id.etAge);
        btnSend = view.findViewById(R.id.button);
        // эта кнопка заверщает эизненный цикл Активити 2
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleName = etName.getText().toString().trim();
                String titleSurname = etSurname.getText().toString().trim();
                String titleAge = etAge.getText().toString().trim();

                ActivityTwo activityTwo = (ActivityTwo) getActivity();
                Title title = new Title();
                title.setName(titleName);
                title.setSurname(titleSurname);
                title.setAge(titleAge);

                activityTwo.sendResult(title);
            }
        });
    }

    // получение новых данных на обработку и возврат с этими данными по кнопке
    public void getElement (Title title) {
        if (title != null) {
            etName.setText(title.name);
            etSurname.setText(title.surname);
            etAge.setText(title.age);
        }
    }
}