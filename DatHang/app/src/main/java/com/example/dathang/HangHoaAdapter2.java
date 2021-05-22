package com.example.dathang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HangHoaAdapter2 extends RecyclerView.Adapter<HangHoaAdapter2.ViewHolder> {
    List<HangHoa> list;
    Context context;

    public HangHoaAdapter2(List<HangHoa> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
//    public HangHoaAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.item_hanghoa2, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
    public HangHoaAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_hanghoa2, parent, false);
        HangHoaAdapter2.ViewHolder viewHolder = new HangHoaAdapter2.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull HangHoaAdapter2.ViewHolder holder, int position) {
        HangHoa s = list.get(position);

        holder.imgAvt.setImageResource(s.getAvt());
        holder.tvName.setText(s.getName());
        holder.tvDescribe.setText(s.getDescribe());
        holder.tvPrice.setText(s.getPrice());
        holder.tvSoLuong.setText(s.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvt;
        TextView tvName;
        TextView tvDescribe;
        TextView tvPrice;
        TextView tvSoLuong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvt = itemView.findViewById(R.id.avtItem);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescribe = itemView.findViewById(R.id.tvMota);
            tvPrice = itemView.findViewById(R.id.tvDonGia);
            tvSoLuong = itemView.findViewById(R.id.tvSoChon);
        }
    }
}
