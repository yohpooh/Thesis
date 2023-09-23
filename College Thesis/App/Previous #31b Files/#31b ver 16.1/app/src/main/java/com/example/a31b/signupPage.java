package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class signupPage extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com/");


    private Button backButton;

    //private Button registerButton;
    String senderEmail, senderPassword, senderTo;
    //public Dialog popUpWindow;
    private FirebaseAuth mAuth;

    public static int code;

    private Button confirmButton;

    public boolean emailCheckerFinal = false;
    public boolean userCheckerFinal = false;

    //popUpWindow popUpWindow = new popUpWindow();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        mAuth = FirebaseAuth.getInstance();

        //popUpWindow =  new Dialog(this);

        backButton = (Button) findViewById(R.id.btnBack_signupPage);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        //verification variables declaration
        senderEmail = "31b.emergency@gmail.com"; //sender email
        senderPassword = "C02emergencyapp"; // sender password
        //senderTo = "31b.emergency@gmail.com"; //recepient email

        final EditText lastname = findViewById(R.id.edtLastName_signupPage);
        final EditText firstname = findViewById(R.id.edtFirstName_signupPage);
        final EditText middlename = findViewById(R.id.edtMidName_signupPage);
        final EditText username = findViewById(R.id.edtUsername_signupPage);
        final EditText address = findViewById(R.id.edtAddress_signupPage);
        final EditText mm = findViewById(R.id.edtmm_signupPage);
        final EditText dd = findViewById(R.id.edtdd_signupPage);
        final EditText yy = findViewById(R.id.edtyy_signupPage);
        final EditText gender = findViewById(R.id.edtGender_signupPage);
        final EditText contact = findViewById(R.id.edtContactNumber_signupPage);
        final EditText email = findViewById(R.id.edtEmail_signupPage);
        final EditText password = findViewById(R.id.edtPassword_signupPage);


        final CheckBox registerCheckBox = findViewById(R.id.chkAgree_signupPage);
        final Button registerButton = findViewById(R.id.btnRegister_signupAccount);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from edittext into string variables
                final String lastnameTxt = lastname.getText().toString();
                final String firstnameTxt = firstname.getText().toString();
               final String middlenameTxt = middlename.getText().toString();
                final String usernameTxt = username.getText().toString();
                final String addressTxt = address.getText().toString();
                final String birthdayTxt = mm.getText().toString() +"/"+ dd.getText().toString() +"/"+ yy.getText().toString();
                final String genderTxt = gender.getText().toString();
                final String contactTxt = contact.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();

                senderTo = emailTxt;

                if(lastnameTxt.isEmpty()) {
                    lastname.setError("Last Name is Required");
                    lastname.requestFocus();
                    return;
                }if(firstnameTxt.isEmpty()) {
                    firstname.setError("First Name is Required");
                    firstname.requestFocus();
                    return;
                }if(middlenameTxt.isEmpty()) {
                    middlename.setError("Middle Name is Required");
                    middlename.requestFocus();
                    return;
                } if(birthdayTxt.isEmpty()) {
                    mm.setError("Birthday is Required");
                    mm.requestFocus();
                    dd.setError("Birthday is Required");
                    dd.requestFocus();
                    yy.setError("Birthday is Required");
                    yy.requestFocus();
                    return;
                } if(genderTxt.isEmpty()) {
                    gender.setError("Gender is Required");
                    gender.requestFocus();
                    return;
                }if(contactTxt.isEmpty()) {
                    contact.setError("Contact Number is Required");
                    contact.requestFocus();
                    return;
                }if(addressTxt.isEmpty()) {
                    address.setError("Address is Required");
                    address.requestFocus();
                    return;
                }if(usernameTxt.isEmpty()) {
                    username.setError("Username is Required");
                    username.requestFocus();
                    return;
                }if(passwordTxt.isEmpty()) {
                    password.setError("Password is Required");
                    password.requestFocus();
                    return;
                }if(passwordTxt.length() < 6) {
                    password.setError("Password is too weak");
                    password.requestFocus();
                    return;
                }if(emailTxt.isEmpty()) {
                    email.setError("Email is Required");
                    email.requestFocus();
                    return;
                }if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) {
                    email.setError("Please set a proper email");
                    email.requestFocus();
                    return;
                }if(!registerCheckBox.isChecked()){
                    Toast.makeText(signupPage.this, "You must agree with the Terms and Condition!", Toast.LENGTH_SHORT).show();
                    registerCheckBox.requestFocus();
                    return;
                    //Toast.makeText(signupPage.this, "You must agree with the Terms and Condition!", Toast.LENGTH_SHORT).show();
                }if(!emailTxt.isEmpty()){
                    mAuth.fetchSignInMethodsForEmail(emailTxt).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                            boolean checkEmail = !task.getResult().getSignInMethods().isEmpty();
                            if(checkEmail){
                                email.setError("Email is already registered");
                                email.requestFocus();
                                return;
                            }else {
                                emailCheckerFinal = true;
                            }
                        }
                    });
                }if(!usernameTxt.isEmpty()){
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if phone is not registered before
                            if (snapshot.hasChild(usernameTxt)) {
                                //Toast.makeText(signupPage.this, "Username is already registered", Toast.LENGTH_SHORT).show();
                                username.setError("Username already exist!");
                                username.requestFocus();
                                return;
                            }else{
                                userCheckerFinal = true;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }if(userCheckerFinal == true && emailCheckerFinal == true && popUpWindow.passCodeCorrect == false){
                    openPopUpWindow();

                    //verification code
                    Properties properties = new Properties();

                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(senderEmail, senderPassword);
                        }
                    });

                    try {

                        Random random = new Random();
                        code = random.nextInt(8999)+1000;


                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(senderEmail));
                        message.setRecipient(Message.RecipientType.TO, new InternetAddress(senderTo));
                        message.setSubject("Verification Code");
                        message.setText("\nWelcome to #31b Emergency App \n\nThis is your VERIFICATION CODE : " + String.valueOf(code) + "\n\nPlease enter your code immediately.");

                        new sendMail().execute(message);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else if(popUpWindow.passCodeCorrect == true) {

                    mAuth.createUserWithEmailAndPassword(emailTxt,passwordTxt)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                        Users users = new Users(lastnameTxt, firstnameTxt, middlenameTxt, usernameTxt, addressTxt, birthdayTxt, genderTxt, contactTxt, emailTxt, passwordTxt, firebaseUser.getUid());

                                        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                //check if phone is not registered before
                                                if (snapshot.hasChild(usernameTxt)) {
                                                    Toast.makeText(signupPage.this, "Username is already registered", Toast.LENGTH_SHORT).show();
                                                }
                                                else {
                                                    databaseReference.child("Users").child(usernameTxt).setValue(users);
                                                    Toast.makeText(signupPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                                    openLogin();
                                                }

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                }
                            });
                }/*else{

                    mAuth.createUserWithEmailAndPassword(emailTxt,passwordTxt)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                        Users users = new Users(lastnameTxt, firstnameTxt, middlenameTxt, usernameTxt, addressTxt, ageTxt, genderTxt, contactTxt, emailTxt, passwordTxt, firebaseUser.getUid());

                                        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                //check if phone is not registered before
                                                if (snapshot.hasChild(usernameTxt)) {
                                                    Toast.makeText(signupPage.this, "Username is already registered", Toast.LENGTH_SHORT).show();
                                                }
                                                else {
                                                    databaseReference.child("Users").child(usernameTxt).setValue(users);
                                                    //Toast.makeText(signupPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();


                                                }

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                }
                            });

                }
                }*/
            }
        });

        final TextView editDataPrivacy = findViewById(R.id.txtDataPrivacy_signUp);
        editDataPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDataPrivacy();
                //on save instance state
            }
        });


    }

    public void openPopUpWindow(){
        Intent intent = new Intent(this, popUpWindow.class);
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        startActivity(intent);
    }

    public void openLogin(){
        Intent intent = new Intent(this, loginPage.class);
        startActivity(intent);
    }

    public void openDataPrivacy() {
        Intent intent = new Intent(this, data_privacy_signUp.class);
        startActivity(intent);

    }

    private class sendMail extends AsyncTask<Message, String,String> {
        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }
    }








}

