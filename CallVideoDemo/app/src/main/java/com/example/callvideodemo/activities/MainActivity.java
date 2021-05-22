package com.example.callvideodemo.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.callvideodemo.adapters.UserAdapter;
import com.example.callvideodemo.databinding.ActivityMainBinding;
import com.example.callvideodemo.listeners.UserListener;
import com.example.callvideodemo.models.User;
import com.example.callvideodemo.utilities.Constants;
import com.example.callvideodemo.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserListener {
    ActivityMainBinding binding;
    PreferenceManager preferenceManager;
    List<User> users;
    UserAdapter adapter;

    private int REQUEST_CODE_BATTERY_OPTIMIZATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        preferenceManager = new PreferenceManager(getApplicationContext());

        //display name: thích hiển thị nhiều hơn thì fomat nhiều hơn(đây chỉ là ví dụ format chứ không thì setText là xong r :v)
        binding.tvName.setText(String.format(
                "%s",
                preferenceManager.getString(Constants.KEY_USER_NAME)
        ));

        //sign out
        binding.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        //Vâng và đây là lấy token và update
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful() && task.getResult() != null){
                    sendFCMTokenToDatabase(task.getResult().getToken());
                }
            }
        });

        //hiển thị danh sách user
        users = new ArrayList<>();
        adapter = new UserAdapter(users, this);
        binding.rclView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rclView.setAdapter(adapter);

        //refesh lại trạng thái của các user
        binding.swiperefreshlayout.setOnRefreshListener(this::getUsers);

        getUsers(); //bên trong có cập nhập adapter....

        checkForBatteryOptimizations();
    }

    private void getUsers(){
        //bật refresh trạng thái user
        binding.swiperefreshlayout.setRefreshing(true);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //tắt refresh trạng thái user
                        binding.swiperefreshlayout.setRefreshing(false);

                        String myUserId = preferenceManager.getString(Constants.KEY_USER_ID);
                        if(task.isSuccessful() && task.getResult() != null){
                            users.clear();
                            //lấy list user gán vào users
                            for(QueryDocumentSnapshot snapshot : task.getResult()){
                                if(myUserId.equals(snapshot.getId())){
                                    continue;
                                    //bỏ qua bản thân :v
                                }
                                User user = new User();
                                user.setUsername(snapshot.getString(Constants.KEY_USER_NAME));
                                user.setEmail(snapshot.getString(Constants.KEY_EMAIL));
                                user.setToken(snapshot.getString(Constants.KEY_FCM_TOKEN));
                                users.add(user);
                            }
                            if(users.size() > 0){
                                adapter.notifyDataSetChanged();
                            }else {
                                binding.tvErrorMessage.setVisibility(View.VISIBLE);
                                binding.tvErrorMessage.setText("No user available");
                            }
                        }else {
                            binding.tvErrorMessage.setVisibility(View.VISIBLE);
                            binding.tvErrorMessage.setText("No user available");
                        }
                    }
                });
    }

    private void sendFCMTokenToDatabase(String token){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                firestore.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        documentReference.update(Constants.KEY_FCM_TOKEN, token)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(MainActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signOut(){
        Toast.makeText(this, "Sign out....", Toast.LENGTH_SHORT).show();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection(Constants.KEY_COLLECTION_USERS).document(
                preferenceManager.getString(Constants.KEY_USER_ID)
        );

        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Xóa dữ liệu của SharedPreferences và chuyển đến activity signIn
                        preferenceManager.clearPreferences();
                        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Unable to sign out", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void initiateVideoMeeting(User user) {
        if(user.getToken() == null || user.getToken().trim().isEmpty()){
            Toast.makeText(this, user.getUsername() + " is not available for meeting", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(this, "Video meeting with " + user.getUsername(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), OutgoingInvitationActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("type", "video");
            startActivity(intent);
        }
    }

    @Override
    public void initiateAudioMeeting(User user) {
        if(user.getToken() == null || user.getToken().trim().isEmpty()){
            Toast.makeText(this, user.getUsername() + " is not available for meeting", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(this, "Audio meeting with " + user.getUsername(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), OutgoingInvitationActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("type", "audio");
            startActivity(intent);
        }
    }

    //check cho chạy ngầm
    private void checkForBatteryOptimizations(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            if(!powerManager.isIgnoringBatteryOptimizations(getPackageName())){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Warning");
                builder.setMessage("Battery optimization is enabled. It can interrupt running background services");
                builder.setPositiveButton("Disable", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                        startActivityForResult(intent, REQUEST_CODE_BATTERY_OPTIMIZATION);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_BATTERY_OPTIMIZATION){
            checkForBatteryOptimizations();
        }
    }
}