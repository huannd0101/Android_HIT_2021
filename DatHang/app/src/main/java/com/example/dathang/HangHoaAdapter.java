package com.example.btvn_buoi4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HangHoaAdapter extends RecyclerView.Adapter<HangHoaAdapter.ViewHolder> {
    List<HangHoa> list;
    Context context;

    public HangHoaAdapter(List<HangHoa> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public HangHoaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HangHoaAdapter.ViewHolder holder, int position) {
        HangHoa s = list.get(position);

        holder.imgAvt.setImageResource(s.getAvt());
        holder.tvName.setText(s.getName());
        holder.tvDescribe.setText(s.getDescribe());
        holder.tvPrice.setText(s.getPrice()+"");
        holder.tvSoLuong.setText(s.getSoLuong()+"");
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
            imgAvt = itemView.findViewById(R.id.ivAvt);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescribe = itemView.findViewById(R.id.tvMota);
            tvPrice = itemView.findViewById(R.id.tvDonGia);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        }
    }
}
