package com.example.a31b;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class nav_settings_page extends AppCompatActivity {

    private Button change_password, change_username, btn_logout;
    private FirebaseAuth mAuth;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_settings_page);

        mAuth = FirebaseAuth.getInstance();

        back = findViewById(R.id.Bck_settings_p1);
        change_password = (Button) findViewById(R.id.change_pword);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nav_settings_page.this, nav_settings_change_pword.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nav_settings_page.this, dashboardPage.class);
                startActivity(intent);
            }
        });


       /* change_username = (Button) findViewById(R.id.change_username);
        change_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nav_settings_page.this, nav_settings_change_username.class);
                startActivity(intent);
            }
        }); */

        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences myPrefs = getSharedPreferences("MY",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();

                mAuth.signOut();
                Intent intent = new Intent(nav_settings_page.this, loginPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });


    }




}