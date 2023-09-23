package com.example.a31b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class other_services_off_brgy extends AppCompatActivity {

    private Button back_other_brgy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_services_off_brgy);

        final Button back_brgy = findViewById(R.id.btnBack_other_c);

        back_brgy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_services_off_brgy.this, other_sevices_offered.class);
                startActivity(intent);
            }
        });

        back_other_brgy = (Button) findViewById(R.id.btnBack_other_3);

        back_other_brgy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_services_off_brgy.this, other_sevices_offered.class);
                startActivity(intent);
            }
        });

    }
}