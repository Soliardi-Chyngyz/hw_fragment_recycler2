package com.example.hw_fragment_recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    public ArrayList<Title> data = new ArrayList<>();
    public ItemClickListener listener;

    public void addApplication (Title title){
        // он уже вкладывает данные в массив
        data.add(title);
        // необходимо вызвать этот метод, чтобы recycler мог записать новый item и "внести" его
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recyclerview, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtSurname, txtAge;
        Title title;
        public int position;   // | 1

        public MainViewHolder(@NonNull View itemView){
            super (itemView);
            txtName = itemView.findViewById(R.id.textName);
            txtSurname = itemView.findViewById(R.id.textSurname);
            txtAge = itemView.findViewById(R.id.textAge);
            // действие при нажатии itemView - айтем ресайклера | 1
            itemView.setOnClickListener(this);
        }

        void onBind(Title title, int position){
            if (title != null) {
                this.title = title;
                this.position = position;
                txtName.setText(title.name);
                txtSurname.setText(title.surname);
                txtAge.setText(title.age);
            }
        }

        @Override
        public void onClick(View view) {
            if (listener != null)
                listener.onItemClick(title, getAdapterPosition());
        }
    }
    // имзенять поля с помощью позиции (заполняет поля) | 3
    public void setElement (int position, Title title) {
        if (title != null) {
            data.set(position, title);
            // объявили что позиция item была изменена
            notifyItemChanged(position);
        }
    }

    //метод отправки данных с другого активити | 2
    public void setOnClickListener(ItemClickListener mListener){
        this.listener = mListener;
    }

    // благодаря нему можем обращаться к элементу в списке - вьюшкам | 1
    public interface ItemClickListener {
        void onItemClick(Title title, int position);
    }
}
