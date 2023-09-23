package com.example.a31b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class other_sevices_offered extends AppCompatActivity {

    Button Back_others_1;
    LinearLayout doh,wcpd,brgy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_sevices_offered);

        Back_others_1 = findViewById(R.id.btnBack_others_1);
        doh = findViewById(R.id.btn_doh);
        wcpd = findViewById(R.id.btn_wcpd);
        brgy = findViewById(R.id.btn_brgy);

        Back_others_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_sevices_offered.this, dashboardPage.class);
                startActivity(intent);
            }
        });

        doh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_sevices_offered.this, other_services_off_doh.class);
                startActivity(intent);
            }
        });

        wcpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_sevices_offered.this, other_services_off_wcpd.class);
                startActivity(intent);
            }
        });

        brgy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_sevices_offered.this, other_services_off_brgy.class);
                startActivity(intent);
            }
        });

    }
}