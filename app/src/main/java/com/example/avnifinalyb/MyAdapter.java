package com.example.avnifinalyb;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<MyItem> itemList;
    private MyItem targetCountry;


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
        MyItem guessed = itemList.get(position);

        holder.tvCountry.setText(guessed.getCountry());

        if (targetCountry != null) {
            holder.tvContinent.setText(
                    guessed.getContinent().equalsIgnoreCase(targetCountry.getContinent())
                            ? "Same"
                            : "Different"
            );

            holder.tvReligion.setText(
                    guessed.getReligion().equalsIgnoreCase(targetCountry.getReligion())
                            ? "Same"
                            : "Different"
            );

            holder.tvLandLocked.setText(
                    guessed.getLandLocked().equalsIgnoreCase(targetCountry.getLandLocked())
                            ? "Same"
                            : "Different"
            );

            holder.tvPopulation.setText(
                    comparePopulationText(guessed, targetCountry)
            );

            String popResult = comparePopulationText(guessed, targetCountry);
            setResultText(holder.tvPopulation, popResult);
        }
    }

    /// a helper function for the onBindViewHolder compares the population of the 2 countries
    /// and returns more if the guessed country has more people, less if less and else same
    private String comparePopulationText(MyItem guess, MyItem target) {
        int g = parsePop(guess.getPopulation());
        int t = parsePop(target.getPopulation());

        if (g > t) return "↑";
        if (g < t) return "↓";
        return "✓";
    }

    private int parsePop(String pop) {
        if (pop.endsWith("M")) {
            return (int)(Double.parseDouble(pop.replace("M", "")) * 1_000_000);
        }
        return 0;
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

    public void setTargetCountry(MyItem targetCountry) {
        this.targetCountry = targetCountry;
    }

    /// checks what the text returned is, and sets a color based on the result
    private void setResultText(TextView tv, String text) {
        tv.setText(text);

        if (text.equals("Same")) {
            tv.setTextColor(Color.GREEN);
        } else if (text.equals("Different")) {
            tv.setTextColor(Color.RED);
        } else {
            tv.setTextColor(Color.parseColor("#FFA500")); // כתום
        }
    }



}

