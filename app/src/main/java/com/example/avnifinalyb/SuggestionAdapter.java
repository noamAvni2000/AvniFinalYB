package com.example.avnifinalyb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder> {

    private ArrayList<MyItem> suggestionList;
    private OnItemClickListener listener;

    public SuggestionAdapter(ArrayList<MyItem> suggestionList) {
        this.suggestionList = suggestionList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void updateList(ArrayList<MyItem> newList) {
        suggestionList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.suggestion_item, parent, false);
        return new SuggestionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionViewHolder holder, int position) {
        MyItem currentItem = suggestionList.get(position);
        holder.tvSuggestionCountry.setText(currentItem.getCountry());
    }

    @Override
    public int getItemCount() {
        return suggestionList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(MyItem item);
    }

    public class SuggestionViewHolder extends RecyclerView.ViewHolder {
        TextView tvSuggestionCountry;

        public SuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSuggestionCountry = itemView.findViewById(R.id.tvSuggestionCountry);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(suggestionList.get(position));
                        }
                    }
                }
            });
        }
    }
}
