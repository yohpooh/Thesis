package com.example.a31b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class nav_settings_change_username extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_settings_change_username);


    }

    public void newusername(View view) {

        Intent intent = new Intent(this,nav_settings_page.class);
        startActivity(intent);
    }

}