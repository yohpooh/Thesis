package com.example.a31b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class other_services_off_wcpd extends AppCompatActivity {

    private Button back_other_wcpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_services_off_wcpd);

        final Button back_wcpd = findViewById(R.id.btnBack_other_b);

        back_wcpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_services_off_wcpd.this, other_sevices_offered.class);
                startActivity(intent);
            }
        });

        back_other_wcpd = (Button) findViewById(R.id.btnBack_other_2);

        back_other_wcpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(other_services_off_wcpd.this, other_sevices_offered.class);
                startActivity(intent);
            }
        });
        
    }
}