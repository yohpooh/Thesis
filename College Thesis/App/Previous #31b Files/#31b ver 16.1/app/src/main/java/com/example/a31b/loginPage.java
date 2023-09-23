package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginPage extends AppCompatActivity {

    TextInputLayout uname, password;
    private Button loginButton;
    private TextView signupTextButton, forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        uname = findViewById(R.id.uname);
        password = findViewById(R.id.password);

        loginButton = (Button) findViewById(R.id.btnLogin_loginPage);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDashboard();
                //Validate Login Info

                if (!validateUsername() || !validatePassword()) {
                    return;
                }else {
                    isUser();

                }
                //openDashboard();

            }
        });

        signupTextButton = (TextView) findViewById(R.id.txtSignUp_loginPage);
        signupTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    public void openDashboard(){
        Intent intent = new Intent(this, dashboardPage.class);
        startActivity(intent);
    }

    public void openSignUp(){
        Intent intent = new Intent(this, signupPage.class);
        startActivity(intent);
    }

    public void forgotpassword(View view) {
        Intent intent = new Intent(this, auth_changePass_page.class);
        startActivity(intent);
    }

    private Boolean validateUsername() {
        String val = uname.getEditText().getText().toString();
        if (val.isEmpty()) {
            uname.setError("Field cannot be empty");
            return false;
        } else {
            uname.setError(null);
            uname.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private void isUser() {
        final String userEnteredUsername = uname.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    uname.setError(null);
                    uname.setErrorEnabled(false);
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword))
                    {
                        uname.setError(null);
                        uname.setErrorEnabled(false);
                        String lnameFromDB = dataSnapshot.child(userEnteredUsername).child("lastname").getValue(String.class);
                        String fnameFromDB = dataSnapshot.child(userEnteredUsername).child("firstname").getValue(String.class);
                        String mnameFromDB = dataSnapshot.child(userEnteredUsername).child("middlename").getValue(String.class);
                        String addressFromDB = dataSnapshot.child(userEnteredUsername).child("address").getValue(String.class);
                        String ageFromDB = dataSnapshot.child(userEnteredUsername).child("age").getValue(String.class);
                        String dobFromDB = dataSnapshot.child(userEnteredUsername).child("birthday").getValue(String.class);
                        String genderFromDB = dataSnapshot.child(userEnteredUsername).child("gender").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String contactFromDB = dataSnapshot.child(userEnteredUsername).child("contact").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        //  Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        Intent intent = new Intent(getApplicationContext(), dashboardPage.class);
                        intent.putExtra("lastname", lnameFromDB);
                        intent.putExtra("firstname", fnameFromDB);
                        intent.putExtra("middlename", mnameFromDB);
                        intent.putExtra("address", addressFromDB);
                        intent.putExtra("age", ageFromDB);
                        intent.putExtra("birthday", dobFromDB);
                        intent.putExtra("gender", genderFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("contact", contactFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                        //Toast.makeText(getApplicationContext(), "Welcome " + fnameFromDB + " " + lnameFromDB, Toast.LENGTH_LONG).show();
                    } else
                    {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {

                    uname.setError("No such User exist");
                    uname.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseerror) {

            }
        });

    }

}