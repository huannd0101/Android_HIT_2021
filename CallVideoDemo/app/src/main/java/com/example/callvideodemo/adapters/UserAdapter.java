package com.example.callvideodemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callvideodemo.R;
import com.example.callvideodemo.listeners.UserListener;
import com.example.callvideodemo.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;
    private UserListener userListener;

    public UserAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.user_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

     class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView call, video_call;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvUserName);
            call = itemView.findViewById(R.id.call);
            video_call = itemView.findViewById(R.id.video_call);
        }

        void setUserData(User user){
            tvName.setText(user.getUsername());
            call.setOnClickListener(v -> userListener.initiateAudioMeeting(user));
            video_call.setOnClickListener(v -> userListener.initiateVideoMeeting(user));
        }
    }
}
