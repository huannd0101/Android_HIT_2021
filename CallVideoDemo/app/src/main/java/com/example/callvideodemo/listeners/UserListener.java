package com.example.callvideodemo.listeners;

import com.example.callvideodemo.models.User;

public interface UserListener {
    void initiateVideoMeeting(User user);

    void initiateAudioMeeting(User user);
}
