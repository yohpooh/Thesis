package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class health_care_rehab extends AppCompatActivity {

    private Button back_health_rehab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_care_rehab);

        final Button back_rehab = findViewById(R.id.btnBack_soc_ser_a);

        back_rehab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(health_care_rehab.this, health_care.class);
                //startActivity(intent);
                backButton();

            }
        });

        back_health_rehab = (Button) findViewById(R.id.btnBack_soc_ser_1);
        back_health_rehab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(health_care_rehab.this, health_care.class);
                //startActivity(intent);
                backButton();
            }
        });

        setupHyperlink();
        setupHyperlink2();
    }
    private void setupHyperlink() {
        TextView dswdhavenro3 = findViewById(R.id.dswdhavenro3);
        dswdhavenro3.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void setupHyperlink2() {
        TextView shelter = findViewById(R.id.shelter);
        shelter.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void backButton() {
        // MAGLALAGAY NG GANITO PER CARDVIEW!! WAG KALIMUTAN!!!!
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // String email = intent.getStringExtra("email");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                    // PINAPALITAN TO!! ITO UNG DESTINATION PAGE!!
                    Intent intent = new Intent(getApplicationContext(), health_care.class);
                    intent.putExtra("username", usernameFromDB);
                    intent.putExtra("email", emailFromDB);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}