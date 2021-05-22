package com.example.testapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder> {
    Context context;
    List<LinkGrils> listUrl;

    public GirlAdapter() {
    }

    @NonNull
    @Override
    public GirlAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GirlAdapter.ViewHolder holder, int position) {
        LinkGrils linkGrils = listUrl.get(position);

        Glide.with(context).load(linkGrils.getUrl()).into(holder.cirView);

    }

    @Override
    public int getItemCount() {
        return listUrl.size();
    }

    public GirlAdapter(Context context, List<LinkGrils> listUrl) {
        this.context = context;
        this.listUrl = listUrl;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<LinkGrils> getListUrl() {
        return listUrl;
    }

    public void setListUrl(List<LinkGrils> listUrl) {
        this.listUrl = listUrl;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CircleImageView cirView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            imageView = itemView.findViewById(R.id.img);
            cirView = itemView.findViewById(R.id.cirView);
        }
    }
}
