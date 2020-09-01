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
        data.add(title);
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
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtSurname, txtAge;
        Title title;

        public MainViewHolder(@NonNull View itemView){
            super (itemView);
            txtName = itemView.findViewById(R.id.textName);
            txtSurname = itemView.findViewById(R.id.textSurname);
            txtAge = itemView.findViewById(R.id.textAge);
        }
        void onBind(Title title){
            this.title = title;
            txtName.setText(title.name);
            txtSurname.setText(title.surname);
            txtAge.setText(title.age);
        }

        @Override
        public void onClick(View view) {
            if (listener != null)
                listener.onItemClick(title, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(Title view, int position);
    }
}
