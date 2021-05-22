package com.example.dropanddroprecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {
    List<String> list;

    public void setData(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.Holder holder, int position) {
        String item = list.get(position);
        holder.tview.setText(item);
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tview;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tview = itemView.findViewById(R.id.tv_item);
        }
    }
}
