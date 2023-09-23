package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class auth_changePass_page extends AppCompatActivity {

    TextInputLayout Nuname, Nemail;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_change_pass_page);

        Nuname = findViewById(R.id.Nuname);
        Nemail = findViewById(R.id.Nemail);
        back = findViewById(R.id.btnBack_auth_changePassPage);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(auth_changePass_page.this, loginPage.class);
                startActivity(intent);
            }
        });
    }

    public void auth_changepass(View view) {

        if (!validateUsername() || !validatePassword()) {
            return;
        }else {
            isUser();
        }

       // Intent intent = new Intent(this, forgotpassword.class);
       // startActivity(intent);

    }

    private Boolean validateUsername() {
        String val = Nuname.getEditText().getText().toString();
        if (val.isEmpty()) {
            Nuname.setError("Field cannot be empty");
            return false;
        } else {
            Nuname.setError(null);
            Nuname.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword() {
        String val = Nemail.getEditText().getText().toString();
        if (val.isEmpty()) {
            Nemail.setError("Field cannot be empty");
            return false;
        } else {
            Nemail.setError(null);
            Nemail.setErrorEnabled(false);
            return true;
        }
    }

    private void isUser() {
        final String userEnteredUsername = Nuname.getEditText().getText().toString().trim();
        final String userEnteredEmail = Nemail.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Nuname.setError(null);
                    Nuname.setErrorEnabled(false);
                    String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                    if (emailFromDB.equals(userEnteredEmail))
                    {
                        Nuname.setError(null);
                        Nuname.setErrorEnabled(false);

                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                       // String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), forgotpassword.class);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else
                    {
                        Nemail.setError("Wrong Email");
                        Nemail.requestFocus();
                    }
                } else {

                    Nuname.setError("No such User exist");
                    Nuname.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseerror) {

            }
        });

    }
}