package com.example.dathang;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HangHoaAdapter extends RecyclerView.Adapter<HangHoaAdapter.ViewHolder> {
    List<HangHoa> list;
    Context context;
    IOnClickHangHoa iOnClickHangHoa;

    public void setiOnClickHangHoa(IOnClickHangHoa iOnClickHangHoa) {
        this.iOnClickHangHoa = iOnClickHangHoa;
    }

    public HangHoaAdapter(List<HangHoa> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public HangHoaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_hanghoa, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_list);
        view.startAnimation(animation);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HangHoaAdapter.ViewHolder holder, int position) {
        HangHoa s = list.get(position);

        holder.imgAvt.setImageResource(s.getAvt());
        holder.tvName.setText(s.getName());
        holder.tvDescribe.setText(s.getDescribe());
        holder.tvPrice.setText(s.getPrice()+" $");
        holder.tvSoLuong.setText(s.getSoLuong()+"");

        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickHangHoa.onClickBtnPlus(s);

            }
        });
        holder.tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickHangHoa.onClickBtnSub(s);

            }
        });
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
        TextView tvSub;
        TextView tvPlus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvt = itemView.findViewById(R.id.avtItem);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescribe = itemView.findViewById(R.id.tvMota);
            tvPrice = itemView.findViewById(R.id.tvDonGia);
            tvSoLuong = itemView.findViewById(R.id.tvSoChon);
            tvSub = itemView.findViewById(R.id.tvSub);
            tvPlus = itemView.findViewById(R.id.tvPlus);
        }
    }
}
