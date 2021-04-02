package com.example.foldingcellanimation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    List<Book> list;

    public void setData(List<Book> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        Book book = list.get(position);
        if(book == null){
            return;
        }

        Glide.with(holder.foldingCell.getContext())
                .load(book.getImageLink())
                .into(holder.imgView);

        Glide.with(holder.foldingCell.getContext())
                .load(book.getImageLink())
                .into(holder.imgView2);

        holder.tvTitle.setText(book.getTitle());
        holder.tvTitle2.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());

//        holder.tvPrice.setText(book.getPrice().intValue() + "đ");

        double temp = 0;
        try {
            temp = 1.0*book.getRateStar()/book.getNumOfReview();
        }catch (Exception e){
            temp = 0;
        }

        holder.tvRateStar.setText(String.valueOf(temp));
        holder.tvContentDiscription.setText(book.getDescription());
        holder.tvNumOfReview.setText(String.valueOf(book.getNumOfReview()));
        holder.tvCategoty.setText(book.getCategoty());
        holder.tvNumOfPage.setText(String.valueOf(book.getNumOfPage()));

        //
        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.foldingCell.toggle(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        FoldingCell foldingCell;
        TextView tvTitle2;
        TextView tvTitle, tvAuthor, tvPrice, tvRateStar, tvContentDiscription, tvNumOfReview, tvCategoty, tvNumOfPage;
        ImageView imgView, imgView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foldingCell = itemView.findViewById(R.id.folding_cell);
            tvTitle2 = itemView.findViewById(R.id.tvTitle2);
            imgView2 = itemView.findViewById(R.id.imgView2);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRateStar = itemView.findViewById(R.id.tvRateStar);
            tvContentDiscription = itemView.findViewById(R.id.tvContentDiscription);
            tvNumOfReview = itemView.findViewById(R.id.tvNumOfReview);
            tvCategoty = itemView.findViewById(R.id.tvCategoty);
            tvNumOfPage = itemView.findViewById(R.id.tvNumOfPage);
            imgView = itemView.findViewById(R.id.imgView);

        }
    }

}
