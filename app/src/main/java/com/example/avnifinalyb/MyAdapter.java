package com.example.avnifinalyb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<MyItem> itemList;

    public MyAdapter(ArrayList<MyItem> itemList) {
        this.itemList = itemList;
    }

    public void addItem(MyItem item) {
        itemList.add(item);
        notifyItemInserted(itemList.size() - 1);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyItem item = itemList.get(position);

        holder.tvCountry.setText(item.getCountry());
        holder.tvContinent.setText(item.getContinent());
        holder.tvReligion.setText(item.getReligion());
        holder.tvLandLocked.setText(item.getLandLocked());
        holder.tvHasNoam.setText(item.getHasNoam());
        holder.tvPopulation.setText(item.getPopulation());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(item);
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvCountry, tvContinent, tvReligion, tvLandLocked, tvHasNoam, tvPopulation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvContinent = itemView.findViewById(R.id.tvContinent);
            tvReligion = itemView.findViewById(R.id.tvReligion);
            tvLandLocked = itemView.findViewById(R.id.tvLandLocked);
            tvHasNoam = itemView.findViewById(R.id.tvHasNoam);
            tvPopulation = itemView.findViewById(R.id.tvPopulation);
        }
    }

    public void updateList(ArrayList<MyItem> newList) {
        itemList = newList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(MyItem item);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}

