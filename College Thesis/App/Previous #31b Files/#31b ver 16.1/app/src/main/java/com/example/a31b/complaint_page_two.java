package com.example.a31b;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class complaint_page_two extends AppCompatActivity {

    private CardView physicalabuse;
    private CardView sexualabuse;
    private CardView financialabuse;
    private CardView psyaabuse;
    private CardView ukabuse;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_page_two);

        final Button back_button_select = findViewById(R.id.btnBack_select_abuse);
        back_button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
            }
        });

        physicalabuse = (CardView) findViewById(R.id.physicalabuse);

        physicalabuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PAusername();
             //   Intent intent = new Intent(complaint_page_two.this, comp_form1_pa.class);
               // startActivity(intent);

            }
        });


        sexualabuse = (CardView) findViewById(R.id.sexualabuse);

        sexualabuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SAusername();
                // Intent intent = new Intent(complaint_page_two.this, comp_form_2_SA.class);
                //startActivity(intent);
            }
        });


        financialabuse = (CardView) findViewById(R.id.financialabuse);

        financialabuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FAusername();
               // Intent intent = new Intent(complaint_page_two.this, comp_form_3_FA.class);
                //startActivity(intent);
            }
        });

        psyaabuse = (CardView) findViewById(R.id.psychoabuse);

        psyaabuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PSYusername();
                // Intent intent = new Intent(complaint_page_two.this, comp_form_4_PSYA.class);
                //startActivity(intent);
            }
        });

        ukabuse = (CardView) findViewById(R.id.unknown_type);

        ukabuse.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UKusername();
                //Intent intent = new Intent(complaint_page_two.this, comp_form5_ua.class);
                //startActivity(intent);
            }
        }));
    }


    // 4 DAPAT GANITO, PER TYPE OF ABUSE, TAPOS ICCALL DUN SA BUTTON!!
    private void PAusername() { // PHYSICAL ABUSE
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // String email = intent.getStringExtra("email");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    //String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                     String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                                                                        // pinapalitan to, kase ito yung destination page
                    Intent intent = new Intent(getApplicationContext(), comp_form1_pa.class);
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

    //----
    private void SAusername() { // PHYSICAL ABUSE
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // String email = intent.getStringExtra("email");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    //String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                    // pinapalitan to, kase ito yung destination page
                    Intent intent = new Intent(getApplicationContext(), comp_form_2_SA.class);
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

    //----
    private void FAusername() { // PHYSICAL ABUSE
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // String email = intent.getStringExtra("email");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    //String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                    // pinapalitan to, kase ito yung destination page
                    Intent intent = new Intent(getApplicationContext(), comp_form_3_FA.class);
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

    //----
    private void PSYusername() { // PHYSICAL ABUSE
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // String email = intent.getStringExtra("email");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    //String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                    // pinapalitan to, kase ito yung destination page
                    Intent intent = new Intent(getApplicationContext(), comp_form_4_PSYA.class);
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

    //----
    private void UKusername() { // PHYSICAL ABUSE
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // String email = intent.getStringExtra("email");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    //String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                    // pinapalitan to, kase ito yung destination page
                    Intent intent = new Intent(getApplicationContext(), comp_form5_ua.class);
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

    private void backButton() { // PHYSICAL ABUSE
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // String email = intent.getStringExtra("email");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    //String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                    // pinapalitan to, kase ito yung destination page
                    Intent intent = new Intent(complaint_page_two.this, complaintPage.class);
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