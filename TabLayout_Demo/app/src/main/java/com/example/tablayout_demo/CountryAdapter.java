package com.example.tablayout_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{
    List<Country> list;
    Context context;

    public CountryAdapter(List<Country> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item_box, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        Animation animation = AnimationUtils.loadAnimation(this.context, R.anim.anim_list);
        view.startAnimation(animation);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        Country country = list.get(position);
        holder.Country_Region.setText(country.getCountry_Region());
        holder.Confirmed.setText("Confirmed: " + country.getConfirmed());
        holder.Deaths.setText("Deaths: " + country.getDeaths());
        holder.Recovered.setText("Recovered: " + country.getRecovered());
        if(Long.parseLong(country.getDeaths()) > 1000){
            holder.Status.setText("Status: " + "Warring");
        }else {
            holder.Status.setText("Status: " + "Normal");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Country_Region;
        TextView Confirmed;
        TextView Deaths;
        TextView Recovered;
        TextView Status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Country_Region = itemView.findViewById(R.id.Country_Region);
            Confirmed = itemView.findViewById(R.id.Confirmed);
            Deaths = itemView.findViewById(R.id.Deaths);
            Recovered = itemView.findViewById(R.id.Recovered);
            Status = itemView.findViewById(R.id.Status);
        }
    }
}
