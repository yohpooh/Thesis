package com.example.a31b;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class popUpWindow extends AppCompatActivity {

    Dialog messageDialog;

    EditText passCodeOne;
    EditText passCodeTwo;
    EditText passCodeThree;
    EditText passCodeFour;

    public static boolean passCodeCorrect = false;

    private Button confirmButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_window);

        passCodeOne = findViewById(R.id.digitOne);
        passCodeTwo = findViewById(R.id.digitTwo);
        passCodeThree = findViewById(R.id.digitThree);
        passCodeFour = findViewById(R.id.digitFour);

        //pop up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //getWindow().setLayout((int) (width*.7),(int) (height*.5));
        //getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);

        //check code
        passCodeOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    passCodeTwo.requestFocus();
                }
            }
        });

        passCodeTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    passCodeThree.requestFocus();
                }
            }
        });

        passCodeThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    passCodeFour.requestFocus();
                }
            }
        });

        //confirm button

        confirmButton = (Button) findViewById(R.id.btnConfirm_popUpWindow);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputCode;
                inputCode = passCodeOne.getText().toString() +  passCodeTwo.getText().toString() + passCodeThree.getText().toString() + passCodeFour.getText().toString();

                if(inputCode.equals(String.valueOf(signupPage.code))){
                    //Toast.makeText(popUpWindow.this, "Success",Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                    //openLogin();
                    passCodeCorrect = true;
                    finish();
                }else{
                    //Toast.makeText(popUpWindow.this, "Failed",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /*
    public void checkCode(View view){
        String inputCode;
        inputCode = passCodeOne.getText().toString() +  passCodeTwo.getText().toString() + passCodeThree.getText().toString() + passCodeFour.getText().toString();

        if(inputCode.equals(String.valueOf(code))){
            Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Failed",Toast.LENGTH_SHORT).show();
        }
    }*/

    public void openLogin(){
        Intent intent = new Intent(this, loginPage.class);
        startActivity(intent);
    }
}
























