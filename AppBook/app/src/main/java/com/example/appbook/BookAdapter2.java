package com.example.appbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.ViewHolder>{
    List<Book> bookList = new ArrayList<>();
    Context context;
    IOnClickItem iOnClickItem;

    public void setiOnClickItem(IOnClickItem iOnClickItem) {
        this.iOnClickItem = iOnClickItem;
    }

    public BookAdapter2() {
    }

    public BookAdapter2(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_2, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter2.ViewHolder holder, int position) {

        Book book = bookList.get(position);
            holder.tvTitle.setText(book.getTitle());
            //img
            Glide.with(context)
                    .load(book.getImageLink())
                    .into(holder.imgView);

            holder.imgView.setOnClickListener(v -> {
                iOnClickItem.onClickItem(book);
            });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvAuthor, tvPrice, tvRateStar, tvContentDiscription, tvNumOfReview, tvCategoty, tvNumOfPage;
        ImageView imgView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
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
