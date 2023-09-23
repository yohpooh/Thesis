package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//import com.shuhart.stepview.StepView;

public class complaintPage extends AppCompatActivity {

    private Button backButton;
    private Button callButton;
    private Button authorize;
    //StepView stepView;
    private ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_page_one);

        authorize = (Button) findViewById(R.id.btnAccept_complaintPage);

        authorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserFullName();
              //  Intent intent = new Intent(complaintPage.this, complaint_page_two.class);
               // startActivity(intent);
            }
        });

      //  viewFlipper = findViewById(R.id.vwflprPlaceHolder_complaintPage);
        //view flipper

      backButton = (Button) findViewById(R.id.btnBack_complaintPage);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create back method per slide
                //openDashboard();
                backusername();
            }
        });
    }

    private void showUserFullName() {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        //String email = intent.getStringExtra("email");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                                                                        // PINAPALITAN TO!! ITO UNG DESTINATION PAGE!!
                    Intent intent = new Intent(getApplicationContext(), complaint_page_two.class);
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

    //PARA MATRANSFER PARIN YUNG USERNAME PAG NAGBACK BUTTON
    private void backusername() {
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

    public void openDashboard(){
        Intent intent = new Intent(this, dashboardPage.class);
        startActivity(intent);
    }

    public void acceptButton(View view) {
        viewFlipper.setInAnimation(this, R.anim.enter_right_to_left);
        viewFlipper.setOutAnimation(this, R.anim.exit_right_to_left);
        viewFlipper.setDisplayedChild(1);
    }

    public void abuseNumberFive(View view) {
        viewFlipper.setInAnimation(this, R.anim.enter_right_to_left);
        viewFlipper.setOutAnimation(this, R.anim.exit_right_to_left);
        viewFlipper.setDisplayedChild(2);
    }

    public void abuseNumberFour(View view) {
        viewFlipper.setInAnimation(this, R.anim.enter_right_to_left);
        viewFlipper.setOutAnimation(this, R.anim.exit_right_to_left);
        viewFlipper.setDisplayedChild(2);
    }

    public void abuseNumberThree(View view) {
        viewFlipper.setInAnimation(this, R.anim.enter_right_to_left);
        viewFlipper.setOutAnimation(this, R.anim.exit_right_to_left);
        viewFlipper.setDisplayedChild(2);
    }

    public void abuseNumberTwo(View view) {
        viewFlipper.setInAnimation(this, R.anim.enter_right_to_left);
        viewFlipper.setOutAnimation(this, R.anim.exit_right_to_left);
        viewFlipper.setDisplayedChild(2);
    }

    public void abuseNumberOne(View view) {
        viewFlipper.setInAnimation(this, R.anim.enter_right_to_left);
        viewFlipper.setOutAnimation(this, R.anim.exit_right_to_left);
        viewFlipper.setDisplayedChild(2);
    }

    public void nextButton(View view) {
        viewFlipper.setInAnimation(this, R.anim.enter_right_to_left);
        viewFlipper.setOutAnimation(this, R.anim.exit_right_to_left);
        viewFlipper.setDisplayedChild(3);
    }

    public void doneButton(View view) {
        //change to success full anims
        viewFlipper.setInAnimation(this, R.anim.enter_right_to_left);
        viewFlipper.setOutAnimation(this, R.anim.exit_right_to_left);
        viewFlipper.setDisplayedChild(0);
    }
}