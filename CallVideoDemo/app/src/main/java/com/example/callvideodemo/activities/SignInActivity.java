package com.example.callvideodemo.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.callvideodemo.databinding.ActivitySignInBinding;
import com.example.callvideodemo.utilities.Constants;
import com.example.callvideodemo.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FB = 11;
    ActivitySignInBinding binding;
    FirebaseFirestore firestore;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        initAnimate();
        firestore = FirebaseFirestore.getInstance();
        preferenceManager = new PreferenceManager(getApplicationContext());

        //ktra nếu đã đăng nhập thì chuyển đến MainActivity
        if(preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        //xử dụng 1 lần để add 1 user thôi :v
        /*
        database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put("first_name", "Huan");
        user.put("last_name", "Nguyen");
        user.put("email", "huannd0101@gmail.com");
        database.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(SignInActivity.this, "User inserted", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this, "Error adding user: "  + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        */


        //Giả sử dữ liệu đầu vào oke :v (ngại check)
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });




        //click vào chưa có tài khoản rồi thì chuyển
        binding.tvHaveAccount.setOnClickListener(v -> {
            startActivity( new Intent(SignInActivity.this, SignUpActivity.class));
        });

    }

    private void signIn() {
        firestore.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_EMAIL, binding.tiedtUser.getText().toString().trim())
                .whereEqualTo(Constants.KEY_PASSWORD, binding.tiedtPass.getText().toString().trim())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0){
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                            preferenceManager.putString(Constants.KEY_USER_ID, documentSnapshot.getId());
                            preferenceManager.putString(Constants.KEY_USER_NAME, documentSnapshot.getString(Constants.KEY_USER_NAME));
                            preferenceManager.putString(Constants.KEY_EMAIL, documentSnapshot.getString(Constants.KEY_EMAIL));
                            preferenceManager.putString(Constants.KEY_PASSWORD, documentSnapshot.getString(Constants.KEY_PASSWORD));
                            //thành công thì chuyển sang MainActivity
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else {
                            Toast.makeText(SignInActivity.this, "Unable to sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initAnimate() {
        binding.tiedtUser.setTranslationY(800);
        binding.tiedtPass.setTranslationY(800);
        binding.tvHaveAccount.setTranslationY(800);
        binding.btnSignIn.setTranslationY(800);

        binding.tiedtUser.setAlpha(0);
        binding.tiedtPass.setAlpha(0);
        binding.tvHaveAccount.setAlpha(0);
        binding.btnSignIn.setAlpha(0);

        binding.tiedtUser.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(600).start();
        binding.tiedtPass.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(800).start();
        binding.tvHaveAccount.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1100).start();
        binding.btnSignIn.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1300).start();

        binding.fabFb.setTranslationX(800);
        binding.btnSignUpWithGoogle.setTranslationX(800);
        binding.fabTwitter.setTranslationX(800);

        binding.fabFb.setAlpha((float) 0);
        binding.btnSignUpWithGoogle.setAlpha((float) 0);
        binding.fabTwitter.setAlpha((float) 0);

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