package com.example.login_demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {
    EditText userName, password;
    TextView forgetPass;
    Button btnLogin;
    float v = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        userName = root.findViewById(R.id.edtUserName);
        password = root.findViewById(R.id.edtPass);
        forgetPass = root.findViewById(R.id.tvForgetPass);
        btnLogin = root.findViewById(R.id.btnLogin);

        userName.setTranslationY(800);
        password.setTranslationY(800);
        forgetPass.setTranslationY(800);
        btnLogin.setTranslationY(800);

        userName.setAlpha(v);
        password.setAlpha(v);
        forgetPass.setAlpha(v);
        btnLogin.setAlpha(v);

        userName.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btnLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }
}
