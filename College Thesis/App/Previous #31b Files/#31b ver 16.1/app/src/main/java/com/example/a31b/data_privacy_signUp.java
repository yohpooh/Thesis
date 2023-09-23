package com.example.a31b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class data_privacy_signUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_privacy_sign_up);

        final Button bck_dptcm = findViewById(R.id.btnBack_DPTCm);

        bck_dptcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(data_privacy_signUp.this, signupPage.class);
                startActivity(intent);
                finish();
            }
        });

        final Button bck_dptc = findViewById(R.id.btnBack_DPTC);

        bck_dptc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(data_privacy_signUp.this, signupPage.class);
                startActivity(intent);
                finish();
            }
        });

    }

}