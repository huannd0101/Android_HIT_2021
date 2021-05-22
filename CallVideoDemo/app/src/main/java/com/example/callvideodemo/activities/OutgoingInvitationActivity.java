package com.example.callvideodemo.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.callvideodemo.R;
import com.example.callvideodemo.databinding.ActivityOutgoingIntvitationBinding;
import com.example.callvideodemo.models.User;
import com.example.callvideodemo.network.ApiClient;
import com.example.callvideodemo.network.ApiService;
import com.example.callvideodemo.utilities.Constants;
import com.example.callvideodemo.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutgoingInvitationActivity extends AppCompatActivity {
    ActivityOutgoingIntvitationBinding binding;

    PreferenceManager preferenceManager;
    String inviterToken = null;
    String meetingRoom = null;
    String typeMeeting = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutgoingIntvitationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(getApplicationContext());

        getSupportActionBar().hide();


        //hiển thị type icon meeting
        typeMeeting = getIntent().getStringExtra("type");
        if(typeMeeting != null){
            if(typeMeeting.equals("video")){
                binding.imageMeetingType.setImageResource(R.drawable.ic_video_call);
            }else {
                binding.imageMeetingType.setImageResource(R.drawable.call);
            }
        }
        //hiển thị user name mình đang gọi
        User user = (User) getIntent().getSerializableExtra("user");
        if(user != null){
            binding.tvUserName.setText(user.getUsername());
        }
        //nút stop
        binding.imageStopInvitation.setOnClickListener(v -> {
            if(user != null){
                cancelInvitation(user.getToken());
            }
        });

        //
        //lấy token
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isSuccessful() && task.getResult() != null){
                    inviterToken = task.getResult().getToken();

                    if(typeMeeting != null && user != null){
                        initiateMeeting(typeMeeting, user.getToken());
                    }
                }
            }
        });

    }

    private void initiateMeeting(String meetingType, String receiverToken){
        try {
            JSONArray tokens = new JSONArray();
            tokens.put(receiverToken);

            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_INVITATION);
            data.put(Constants.REMOTE_MSG_MEETING_TYPE, meetingType);
            data.put(Constants.KEY_USER_NAME, preferenceManager.getString(Constants.KEY_USER_NAME));
            data.put(Constants.KEY_EMAIL, preferenceManager.getString(Constants.KEY_EMAIL));
            data.put(Constants.REMOTE_MSG_INVITER_TOKEN, inviterToken);

            //
            meetingRoom = preferenceManager.getString(Constants.KEY_USER_ID) + "_" + UUID.randomUUID().toString().substring(0, 5);
            data.put(Constants.REMOTE_MSG_MEETING_ROOM, meetingRoom);

            body.put(Constants.REMOTE_MSG_DATA, data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS, tokens);

            sendRemoteMessage(body.toString(), Constants.REMOTE_MSG_INVITATION);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void sendRemoteMessage(String remoteMessageBody, String type){
        ApiClient.getClient().create(ApiService.class).sendRemoteMessage(
                Constants.getRemoteMessageHeaders(),
                remoteMessageBody
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()){
                    if(type.equals(Constants.REMOTE_MSG_INVITATION)){
                        Toast.makeText(OutgoingInvitationActivity.this, "Invitation sent successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(OutgoingInvitationActivity.this, "Invitation canceled", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    Toast.makeText(OutgoingInvitationActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(OutgoingInvitationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void cancelInvitation(String receiverToken){
        try {
            JSONArray tokens = new JSONArray();
            tokens.put(receiverToken);

            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_INVITATION_RESPONSE);
            data.put(Constants.REMOTE_MSG_INVITATION_RESPONSE, Constants.REMOTE_MSG_INVITATION_CANCELLED);

            body.put(Constants.REMOTE_MSG_DATA, data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS, tokens);

            sendRemoteMessage(body.toString(), Constants.REMOTE_MSG_INVITATION_CANCELLED);
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        };
    }

    private BroadcastReceiver invitationResponseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type = intent.getStringExtra(Constants.REMOTE_MSG_INVITATION_RESPONSE);
            if(type != null){
                if(type.equals(Constants.REMOTE_MSG_INVITATION_ACCEPTED)){
//                    Toast.makeText(context, "Invitation accepted", Toast.LENGTH_SHORT).show();
                    try {
                        URL urlServer = new URL("https://meet.jit.si");
//                        JitsiMeetConferenceOptions jitsiMeetConferenceOptions = new JitsiMeetConferenceOptions.Builder()
//                                .setServerURL(urlServer)
//                                .setWelcomePageEnabled(false)
//                                .setRoom(meetingRoom)
//                                .build();
                        JitsiMeetConferenceOptions.Builder builder = new JitsiMeetConferenceOptions.Builder();
                        builder.setServerURL(urlServer);
                        builder.setWelcomePageEnabled(false);
                        builder.setRoom(meetingRoom);
                        if(typeMeeting.equals("audio")){
                            builder.setVideoMuted(true);
                        }
//                        JitsiMeetActivity.launch(OutgoingInvitationActivity.this, jitsiMeetConferenceOptions);
                        JitsiMeetActivity.launch(OutgoingInvitationActivity.this, builder.build());
                    } catch (MalformedURLException e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    Toast.makeText(context, "Invitation rejected", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(
                invitationResponseReceiver,
                new IntentFilter(Constants.REMOTE_MSG_INVITATION_RESPONSE)
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(
                invitationResponseReceiver
        );
    }
}