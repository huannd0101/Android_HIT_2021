package com.example.buoi3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;

public class DialogCustome extends Dialog {

    public DialogCustome(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(1);

        setContentView(R.layout.dialog);
    }
}
