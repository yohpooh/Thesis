package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class health_care extends AppCompatActivity {

    Button Back_healthCare_page1;
    LinearLayout rehab,counselling,mental,sup_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_care_1);

        Back_healthCare_page1 = findViewById(R.id.btnBack_healthCare_page1);
        rehab = findViewById(R.id.btn_rehab);
        counselling = findViewById(R.id.btn_counselling);
        mental = findViewById(R.id.btn_mental);
        sup_group = findViewById(R.id.btn_sup_group);

        Back_healthCare_page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
            }
        });

        rehab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                            Intent intent = new Intent(getApplicationContext(), health_care_rehab.class);
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
        });

        counselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                            Intent intent = new Intent(getApplicationContext(), health_care_counselling.class);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("email", emailFromDB);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //Intent intent = new Intent(health_care.this, health_care_counselling.class);
                //startActivity(intent);
            }
        });

        mental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(health_care.this, health_care_mentalHealth.class);
                //startActivity(intent);
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
                            Intent intent = new Intent(getApplicationContext(), health_care_mentalHealth.class);
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
        });

        sup_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(health_care.this, health_care_support.class);
                //startActivity(intent);

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
                            Intent intent = new Intent(getApplicationContext(), health_care_support.class);
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
        });


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
                    Intent intent = new Intent(getApplicationContext(), dashboardPage.class);
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