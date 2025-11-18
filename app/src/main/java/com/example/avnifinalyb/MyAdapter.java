package com.example.avnifinalyb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<MyItem> items;

    public MyAdapter(ArrayList<MyItem> items) {
        this.items = items;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvRow;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvRow = itemView.findViewById(R.id.tvRow);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyItem item = items.get(position);
        holder.tvRow.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
