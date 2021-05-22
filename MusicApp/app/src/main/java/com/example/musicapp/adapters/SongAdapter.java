package com.example.musicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.models.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    Context context;
    List<Song> songs;

    public SongAdapter(Context context, List<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull SongAdapter.ViewHolder holder, int position) {
        Song song = songs.get(position);
        if(song != null){
            holder.imageView.setImageResource(song.getImage());
            holder.tvNameOfSong.setText(song.getNameOfSong());
            holder.tvNameOfSingle.setText(song.getSingleOfSong());
        }
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvNameOfSong, tvNameOfSingle;
        ImageView imgViewPre, imgViewPauseOrPlay, imgViewNext, imgViewCancel;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSong);
            imgViewPre = itemView.findViewById(R.id.imageViewPre);
            imgViewPauseOrPlay = itemView.findViewById(R.id.imageViewPlayOrPause);
            imgViewNext = itemView.findViewById(R.id.imageViewNext);
            imgViewCancel = itemView.findViewById(R.id.imageViewCancel);
            tvNameOfSong = itemView.findViewById(R.id.tvNameOfSong);
            tvNameOfSingle = itemView.findViewById(R.id.tvNameOfSingle);
        }
    }

}
