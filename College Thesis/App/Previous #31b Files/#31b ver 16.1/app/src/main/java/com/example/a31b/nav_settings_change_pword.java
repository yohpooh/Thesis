package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class nav_settings_change_pword extends AppCompatActivity {

    TextInputLayout Cuname, Cemail, Cpassword;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_settings_change_pword);

        Cuname = findViewById(R.id.Cuname);
        Cemail = findViewById(R.id.Cemail);
        Cpassword = findViewById(R.id.Cpassword);
        back = findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent =  new Intent(nav_settings_change_pword.this, dashboardPage.class);
                //startActivity(intent);
                backButton();
            }
        });
    }

    public void newpassword(View view) {
        if (!validateUsername() || !validatePassword()) {
            return;
        }else {
            isUser();
        }
       // Intent intent = new Intent(this,nav_settings_page.class);
      //  startActivity(intent);
    }

    private Boolean validateUsername() {
        String val = Cuname.getEditText().getText().toString();
        if (val.isEmpty()) {
            Cuname.setError("Field cannot be empty");
            return false;
        } else {
            Cuname.setError(null);
            Cuname.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword() {
        String val = Cemail.getEditText().getText().toString();
        if (val.isEmpty()) {
            Cemail.setError("Field cannot be empty");
            return false;
        } else {
            Cemail.setError(null);
            Cemail.setErrorEnabled(false);
            return true;
        }
    }

    private void isUser() {
        final String userEnteredUsername = Cuname.getEditText().getText().toString().trim();
        final String userEnteredEmail = Cemail.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Cuname.setError(null);
                    Cuname.setErrorEnabled(false);
                    String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                    if (emailFromDB.equals(userEnteredEmail))
                    {
                        Cuname.setError(null);
                        Cuname.setErrorEnabled(false);

                        reference.child(userEnteredUsername).child("password").setValue(Cpassword.getEditText().getText().toString());
                        Toast.makeText(nav_settings_change_pword.this, "Password Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(nav_settings_change_pword.this, loginPage.class);
                        startActivity(intent);
                        finish();
                    } else
                    {
                        Cemail.setError("Wrong Email");
                        Cemail.requestFocus();
                    }
                } else {

                    Cuname.setError("No such User exist");
                    Cuname.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseerror) {

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
                    Intent intent =  new Intent(nav_settings_change_pword.this, dashboardPage.class);
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