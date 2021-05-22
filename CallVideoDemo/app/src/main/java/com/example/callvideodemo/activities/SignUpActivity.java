package com.example.callvideodemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.callvideodemo.databinding.ActivitySignUpBinding;
import com.example.callvideodemo.utilities.Constants;
import com.example.callvideodemo.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    static Pattern pattern;
    static String regUserName = "^[a-zA-Z0-9]{5,}$";
    static String regPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$";
    static String regPhone = "^[0-9\\-\\+]{9,15}$";
    static String regEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{3,4}$";

    ActivitySignUpBinding binding;

    PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        initAnimate();

        preferenceManager = new PreferenceManager(getApplicationContext());

        //giả sử tất cả dữ liệu đều điền đủ :v
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });






        //click vào có tài khoản rồi thì chuyển
        binding.tvHaveAccount.setOnClickListener(v -> {
//                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//                startActivity(intent);
            onBackPressed(); //nó sẽ lưu trạng thái cũ và k load lại nữa
        });
    }

    private void signUp() {
        String username = binding.tiedtUser.getText().toString().trim();
        String email = binding.tiedtEmail.getText().toString().trim();
        String password = binding.tiedtPass.getText().toString().trim();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(Constants.KEY_USER_NAME, username);
        hashMap.put(Constants.KEY_EMAIL, email);
        hashMap.put(Constants.KEY_PASSWORD, password);

        firestore.collection(Constants.KEY_COLLECTION_USERS)
                .add(hashMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Thành công thì lưu vào SharedPreference
                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                        preferenceManager.putString(Constants.KEY_USER_ID, documentReference.getId());
                        preferenceManager.putString(Constants.KEY_USER_NAME, username);
                        preferenceManager.putString(Constants.KEY_EMAIL, email);
                        preferenceManager.putString(Constants.KEY_PASSWORD, password);
                        //Xong thì chuyển đến MainActivity
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initAnimate(){
        binding.tiedtUser.setTranslationY(800);
        binding.tiedtPass.setTranslationY(800);
        binding.tvHaveAccount.setTranslationY(800);
        binding.btnSignUp.setTranslationY(800);
        binding.tiedtEmail.setTranslationY(800);

        binding.tiedtUser.setAlpha(0);
        binding.tiedtPass.setAlpha(0);
        binding.tvHaveAccount.setAlpha(0);
        binding.btnSignUp.setAlpha(0);
        binding.tiedtEmail.setAlpha(0);

        binding.tiedtUser.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(600).start();
        binding.tiedtEmail.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(900).start();
        binding.tiedtPass.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1200).start();
        binding.tvHaveAccount.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1400).start();
        binding.btnSignUp.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1600).start();

        binding.fabFb.setAlpha(0f);
        binding.btnSignUpWithGoogle.setAlpha(0f);
        binding.fabTwitter.setAlpha(0f);

        binding.fabFb.setTranslationX(800);
        binding.btnSignUpWithGoogle.setTranslationX(800);
        binding.fabTwitter.setTranslationX(800);

        binding.fabFb.animate().translationX(0).alpha(1).setDuration(1800).setStartDelay(1400).start();
        binding.btnSignUpWithGoogle.animate().translationX(0).alpha(1).setDuration(1800).setStartDelay(1600).start();
        binding.fabTwitter.animate().translationX(0).alpha(1).setDuration(1800).setStartDelay(1800).start();

        binding.imgApp.setTranslationY(-400);
        binding.imgApp.setAlpha(0f);
        binding.imgApp.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1400).start();

        binding.tvName.setTranslationY(-400);
        binding.tvName.setAlpha(0f);
        binding.tvName.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1800).start();

        binding.constraintLayout.setTranslationY(800);
        binding.constraintLayout.setAlpha(0f);
        binding.constraintLayout.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(400).start();
    }

}