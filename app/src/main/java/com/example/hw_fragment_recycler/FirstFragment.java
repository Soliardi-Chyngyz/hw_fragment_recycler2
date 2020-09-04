package com.example.hw_fragment_recycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
// имплементировали адаптер чтобы вызвать метод onItemClick | 4
public class FirstFragment extends Fragment implements MainAdapter.ItemClickListener{
    public RecyclerView recyclerView;
    public MainAdapter mainAdapter;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);
        mainAdapter = new MainAdapter();
        recyclerView.setAdapter(mainAdapter);
        // его необходимо указать | 5
        mainAdapter.setOnClickListener(this);

        Button btnOpen = view.findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.openActivityForResult();
            }
        });
    }

    public void addData(Title title){
        // вкладываю полученые данные title в адаптаер а тот в свою очередь - >
        mainAdapter.addApplication(title);
    }

    @Override
    // для открытия второго активити (чтобы изменить) реализуем interface
    public void onItemClick(Title title, int position) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.changeElement(title, position);

    }
    // функция обновить экран с новыми данными
    public void changeElement(Title textChangeResult, int position) {
        mainAdapter.setElement(position, textChangeResult);
        mainAdapter.notifyDataSetChanged();
    }
}