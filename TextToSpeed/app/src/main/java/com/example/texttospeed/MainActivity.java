package com.example.texttospeed;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

@RequiresApi(api = Build.VERSION_CODES.DONUT)
public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    TextToSpeech textToSpeech;
    Button btnSpeak;
    EditText edtSpeak;
    ImageView imgSpeak;
    String toSpeak;
    static final int RESULT_SPEECH =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSpeak = findViewById(R.id.btnSpeak);
        edtSpeak = findViewById(R.id.edtSpeak);
        imgSpeak = findViewById(R.id.imgSpeak);

        textToSpeech = new TextToSpeech(MainActivity.this,this::onInit);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSpeak = edtSpeak.getText().toString();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        imgSpeak.setOnClickListener(v -> {
            speak();
        });
    }
    private void speak() {
        Intent intent1 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent1.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say some thing: ");
        try {
            startActivityForResult(intent1,RESULT_SPEECH);
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this,"Không thể dùng chức năng này",Toast.LENGTH_LONG).show();;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH:
                if(resultCode == RESULT_OK && data !=null)
                {
                    ArrayList<String> hi = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtSpeak.setText(hi.get(0));
                    //xử lý text sau khi nói
                    if(hi.get(0).contains("Huân")){
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("content", hi.get(0));
                        startActivity(intent);
                    }
                    textToSpeech = new TextToSpeech(MainActivity.this,MainActivity.this::onInit);
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR) {
            textToSpeech.setLanguage(new Locale("vi"));
        }else{
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}