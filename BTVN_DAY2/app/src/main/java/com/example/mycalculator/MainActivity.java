package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult, tvDisplay;
    Button btnZezo, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSeven, btnSix, btnEight, btnNight;
    Button btnDot, btnDiv, btnMul, btnPlus, btnSub, btnC, btnCE, btnDEL, btnEqual, btnPOrS;

    private double firNum=0, secNum=0, res=0;
    private String operation="", ans="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();


        btnZezo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnZezo.getText().toString();
                tvDisplay.setText(text);
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnOne.getText().toString();
                tvDisplay.setText(text);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnTwo.getText().toString();
                tvDisplay.setText(text);
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnThree.getText().toString();
                tvDisplay.setText(text);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnFour.getText().toString();
                tvDisplay.setText(text);
            }
        });


        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnFive.getText().toString();
                tvDisplay.setText(text);
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnSix.getText().toString();
                tvDisplay.setText(text);
            }
        });


        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnSeven.getText().toString();
                tvDisplay.setText(text);
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnEight.getText().toString();
                tvDisplay.setText(text);
            }
        });

        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = tvDisplay.getText().toString() + btnNight.getText().toString();
                tvDisplay.setText(text);
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvDisplay.getText().toString() + ".";
                tvDisplay.setText(text);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText(null);
                tvResult.setText(null);
            }
        });


        btnDEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String backSpace = null;
                if(tvDisplay.getText().length() > 0){
                    StringBuilder stringBuilder = new StringBuilder(tvDisplay.getText());
                    stringBuilder.deleteCharAt(tvDisplay.getText().length()-1);
                    backSpace = stringBuilder.toString();
                    tvDisplay.setText(backSpace);
                }
            }
        });


        //code for operator
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primary;
//                firNum = Double.parseDouble(tvResult.getText().toString());
//                primary = String.format("%f", firNum);
                primary = tvResult.getText().toString();
                firNum = ParseDouble(primary);
                tvResult.setText(primary);
                tvDisplay.setText("");
                operation = "/";
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primary;
//                firNum = Double.parseDouble(tvResult.getText().toString());
//                primary = String.format("%.2f", firNum);
                primary = tvResult.getText().toString();
                firNum = ParseDouble(primary);
                tvResult.setText(primary);
                tvDisplay.setText("");
                operation = "*";
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primary;
//                firNum = Double.parseDouble(tvResult.getText().toString());
//                primary = String.format("%f", firNum);
                primary = tvResult.getText().toString();
                firNum = ParseDouble(primary);
                tvResult.setText(primary);
                tvDisplay.setText("");
                operation = "-";
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primary;
//                firNum = Double.parseDouble(tvResult.getText().toString());
//                primary = String.format("%f", firNum);
                primary = tvResult.getText().toString();
                firNum = ParseDouble(primary);
                tvResult.setText(primary);
                tvDisplay.setText("");
                operation = "+";
            }
        });



        //equal start from here
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secNum = ParseDouble(tvDisplay.getText().toString());
                if(operation.compareTo("")==0){
                    ans = ans = String.format("%.2f", secNum);
                    tvResult.setText(ans);
                    tvDisplay.setText("");
                }

                if(operation.compareTo("+")==0){
                    res = firNum + secNum;
                    ans = String.format("%.2f", res);
                    tvResult.setText(ans);
                    tvDisplay.setText("");
                }

                if(operation.compareTo("-")==0){
                    res = firNum - secNum;
                    ans = String.format("%.2f", res);
                    tvResult.setText(ans);
                    tvDisplay.setText("");
                }

                if(operation.compareTo("*")==0){
                    res = firNum * secNum;
                    ans = String.format("%.2f", res);
                    tvResult.setText(ans);
                    tvDisplay.setText("");
                }

                if(operation.compareTo("/")==0){
                    res = firNum / secNum;
                    ans = String.format("%.2f", res);
                    tvResult.setText(ans);
                    tvDisplay.setText("");
                }


                operation="";
            }
        });



    }


    public void AnhXa() {
        tvResult = findViewById(R.id.tvResult);
        tvDisplay = findViewById(R.id.tvDisplay);
        btnZezo = findViewById(R.id.btnZezo);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNight = findViewById(R.id.btnNight);
        btnDot = findViewById(R.id.btnDot);
        btnDiv = findViewById(R.id.btnDiv);
        btnMul = findViewById(R.id.btnMul);
        btnPlus = findViewById(R.id.btnPlus);
        btnSub = findViewById(R.id.btnSub);
        btnC = findViewById(R.id.btnC);
        btnCE = findViewById(R.id.btnCE);
        btnDEL = findViewById(R.id.btnDEL);
        btnEqual = findViewById(R.id.btnEqual);
        btnPOrS = findViewById(R.id.btnPOrS);
    }

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return 0;
    }

}