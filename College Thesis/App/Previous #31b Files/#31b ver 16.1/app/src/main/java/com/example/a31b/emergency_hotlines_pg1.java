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

import java.security.PrivateKey;

public class emergency_hotlines_pg1 extends AppCompatActivity {

    Button btnBack_hotline_page1;
    LinearLayout santarita,porac,florida,guagua, lubao,sasmuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_hotlines_pg1);

        btnBack_hotline_page1 = findViewById(R.id.btnBack_hotline_page1);
        santarita = findViewById(R.id.sta_rita_opt);
        porac = findViewById(R.id.porac_opt);
        florida = findViewById(R.id.florida_opt);
        guagua = findViewById(R.id.guagua_opt);
        lubao = findViewById(R.id.lubao_opt);
        sasmuan = findViewById(R.id.sasmuan_opt);

        sasmuan.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = new Intent(getApplicationContext(), esh_6_sasmuan.class);
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

        lubao.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = new Intent(getApplicationContext(), esh_5_lubao.class);
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

        guagua.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = new Intent(getApplicationContext(), esh_4_guagua.class);
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

        florida.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = new Intent(getApplicationContext(), esh_3_floridablanca.class);
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

        porac.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = new Intent(getApplicationContext(), esh_2_porac.class);
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

        santarita.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = new Intent(getApplicationContext(), esh_1_starita.class);
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

        btnBack_hotline_page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
            }
        });
    }

    private void backButton() {
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