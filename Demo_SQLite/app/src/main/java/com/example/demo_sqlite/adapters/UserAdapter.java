package com.example.demo_sqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_sqlite.R;
import com.example.demo_sqlite.enteties.User;
import com.example.demo_sqlite.event.IOnClickUser;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<User> list = new ArrayList<>();
    IOnClickUser iOnClickUser;

    public UserAdapter(IOnClickUser iOnClickUser) {
        this.iOnClickUser = iOnClickUser;
    }

    public void setData(List<User> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = list.get(position);
        if(user == null)
            return;
        holder.tvUser.setText(user.getName());
        holder.tvAddress.setText(user.getAddress());
        holder.tvYear.setText(user.getDateOfBirthday());

        //click to update
        holder.btnUpdate.setOnClickListener(v -> iOnClickUser.updateUser(user));
        //click to delete
        holder.btnDelete.setOnClickListener(v -> iOnClickUser.deleteUser(user));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUser, tvAddress, tvYear;
        Button btnUpdate, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvYear = itemView.findViewById(R.id.tvYear);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
