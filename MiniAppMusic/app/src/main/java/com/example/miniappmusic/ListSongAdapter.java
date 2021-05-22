package com.example.miniappmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder> {
    List<Song> list;
    Context context;
    IOnClickSong iOnClickSong;

    public ListSongAdapter(List<Song> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setIOnClickSong(IOnClickSong iOnClickSong){
        this.iOnClickSong = iOnClickSong;
    }

    @NonNull
    @Override
    public ListSongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_song, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListSongAdapter.ViewHolder holder, int position) {
        Song s = list.get(position);

        holder.nameOfSong.setText(s.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameOfSong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfSong = itemView.findViewById(R.id.nameOfSong);
        }
    }
}
