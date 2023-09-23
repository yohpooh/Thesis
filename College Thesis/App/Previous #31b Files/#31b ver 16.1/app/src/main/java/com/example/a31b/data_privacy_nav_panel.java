package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class data_privacy_nav_panel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_privacy_nav_panel);

        final Button bck_dptcm = findViewById(R.id.btnBack_DPTCm);
        bck_dptcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
            }
        });

        final Button bck_dptc = findViewById(R.id.btnBack_DPTC);
        bck_dptc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
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