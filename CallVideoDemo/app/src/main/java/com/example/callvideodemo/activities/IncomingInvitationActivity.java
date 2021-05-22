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
import com.example.callvideodemo.databinding.ActivityIncomingInvitationBinding;
import com.example.callvideodemo.network.ApiClient;
import com.example.callvideodemo.network.ApiService;
import com.example.callvideodemo.utilities.Constants;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomingInvitationActivity extends AppCompatActivity {
    ActivityIncomingInvitationBinding binding;
    String typeMeeting = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomingInvitationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        typeMeeting = getIntent().getStringExtra(Constants.REMOTE_MSG_MEETING_TYPE);
        String userName = getIntent().getStringExtra(Constants.KEY_USER_NAME);

        if(typeMeeting != null){
            if(typeMeeting.equals("video")){
                binding.imageMeetingType.setImageResource(R.drawable.ic_video_call);
            }else {
                binding.imageMeetingType.setImageResource(R.drawable.call);
            }
        }

        if(userName != null){
            binding.tvUserName.setText(userName);
        }

        //nhận cuộc gọi
        binding.imageStartInvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInvitationResponse(
                        Constants.REMOTE_MSG_INVITATION_ACCEPTED,
                        getIntent().getStringExtra(Constants.REMOTE_MSG_INVITER_TOKEN)
                );
            }
        });
        //k nhận
        binding.imageStopInvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInvitationResponse(
                        Constants.REMOTE_MSG_INVITATION_REJECTED,
                        getIntent().getStringExtra(Constants.REMOTE_MSG_INVITER_TOKEN)
                );
            }
        });
    }

    private void sendInvitationResponse(String type, String receiverToken){
        try {
            JSONArray tokens = new JSONArray();
            tokens.put(receiverToken);

            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_INVITATION_RESPONSE);
            data.put(Constants.REMOTE_MSG_INVITATION_RESPONSE, type);

            body.put(Constants.REMOTE_MSG_DATA, data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS, tokens);

            sendRemoteMessage(body.toString(), type);
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        };
    }

    private void sendRemoteMessage(String remoteMessageBody, String type){
        ApiClient.getClient().create(ApiService.class).sendRemoteMessage(
                Constants.getRemoteMessageHeaders(),
                remoteMessageBody
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()){
                    if(type.equals(Constants.REMOTE_MSG_INVITATION_ACCEPTED)){
                        try {
                            URL urlServer = new URL("https://meet.jit.si");
//                            JitsiMeetConferenceOptions conferenceOptions =
//                                    new JitsiMeetConferenceOptions.Builder()
//                                    .setServerURL(urlServer)
//                                    .setWelcomePageEnabled(false)
//                                    .setRoom(getIntent().getStringExtra(Constants.REMOTE_MSG_MEETING_ROOM))
//                                    .build();
                            JitsiMeetConferenceOptions.Builder builder = new JitsiMeetConferenceOptions.Builder();
                            builder.setServerURL(urlServer);
                            builder.setWelcomePageEnabled(false);
                            builder.setRoom(getIntent().getStringExtra(Constants.REMOTE_MSG_MEETING_ROOM));
                            if(typeMeeting.equals("audio")){
                                builder.setVideoMuted(true);
                            }
//                            JitsiMeetActivity.launch(IncomingInvitationActivity.this, conferenceOptions);
                            JitsiMeetActivity.launch(IncomingInvitationActivity.this, builder.build());
                            finish();
                        }catch (Exception e){
                            Toast.makeText(IncomingInvitationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }else{
                        Toast.makeText(IncomingInvitationActivity.this, "Invitation rejected", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    Toast.makeText(IncomingInvitationActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(IncomingInvitationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private BroadcastReceiver invitationResponseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type = intent.getStringExtra(Constants.REMOTE_MSG_INVITATION_RESPONSE);
            if(type != null){
                if(type.equals(Constants.REMOTE_MSG_INVITATION_CANCELLED)){
                    Toast.makeText(context, "Invitation cancelled", Toast.LENGTH_SHORT).show();
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