package com.example.avnifinalyb;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GuessesAdapter extends RecyclerView.Adapter<GuessesAdapter.MyViewHolder> {

    private ArrayList<MyItem> itemList;
    private MyItem targetCountry;


    public GuessesAdapter(ArrayList<MyItem> itemList) {
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
        holder.tvCountry.setTextColor(Color.parseColor("gold")); // Golden color

        if (targetCountry != null) {
            String continentResult = guessed.getContinent().equalsIgnoreCase(targetCountry.getContinent())
                    ? "Same"
                    : "Different";
            setResultText(holder.tvContinent, continentResult);

            String religionResult = guessed.getReligion().equalsIgnoreCase(targetCountry.getReligion())
                    ? "Same"
                    : "Different";
            setResultText(holder.tvReligion, religionResult);

            String landLockedResult = guessed.getLandLocked().equalsIgnoreCase(targetCountry.getLandLocked())
                    ? "Same"
                    : "Different";
            setResultText(holder.tvLandLocked, landLockedResult);

            String hasNoamResult = guessed.getHasNoam().equalsIgnoreCase(targetCountry.getHasNoam())
                    ? "Same"
                    : "Different";
            setResultText(holder.tvHasNoam, hasNoamResult);

            String popResult = comparePopulationText(guessed, targetCountry);
            setResultText(holder.tvPopulation, popResult);
        }
    }

    /// a helper function for the onBindViewHolder compares the population of the 2 countries
    /// and returns more if the guessed country has more people, less if less and else same
    private String comparePopulationText(MyItem guess, MyItem target) {
        long g = parsePop(guess.getPopulation());
        long t = parsePop(target.getPopulation());

        if (g > t) return "↑";
        if (g < t) return "↓";
        return "✓";
    }

    private long parsePop(String pop) {
        if (pop.endsWith("B")) {
            return (long) (Double.parseDouble(pop.replace("B", "")) * 1_000_000_000);///מכיוון שאני שולט בקלט אין בעיה קליטה
        }
        if (pop.endsWith("M")) {
            return (long) (Double.parseDouble(pop.replace("M", "")) * 1_000_000);
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
            tv.setTextColor(Color.parseColor("orange")); // כתום
        }
    }



}
