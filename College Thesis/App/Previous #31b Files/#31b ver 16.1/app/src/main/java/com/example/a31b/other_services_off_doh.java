package com.example.a31b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class other_services_off_doh extends AppCompatActivity {

    private Button back_other_doh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_services_off_doh);

        final Button back_doh = findViewById(R.id.btnBack_other_a);

        back_doh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_services_off_doh.this, other_sevices_offered.class);
                startActivity(intent);
            }
        });

        back_other_doh = (Button) findViewById(R.id.btnBack_other_1);

        back_other_doh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_services_off_doh.this, other_sevices_offered.class);
                startActivity(intent);
            }
        });

    }
}