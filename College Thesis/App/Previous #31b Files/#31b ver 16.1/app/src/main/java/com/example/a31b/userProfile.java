package com.example.a31b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class userProfile extends AppCompatActivity {

    private TextView userFullName;
    private TextView userUserName;

    private EditText userFirstName;
    private EditText userMiddleName;
    private EditText userLastName;
    private EditText userAddress;
    private EditText userGender;
    private EditText userDateOfBirth;
    private EditText userContactNumber;

    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        userFullName = findViewById(R.id.txtFullname_userProfile);
        userUserName = findViewById(R.id.txtUserName_userProfile);

        userFirstName = findViewById(R.id.edtFirstName_userProfile);
        userMiddleName = findViewById(R.id.edtMiddleName_userProfile);
        userLastName = findViewById(R.id.edtLastName_userProfile);
        userAddress = findViewById(R.id.edtAddress_userProfile);
        userGender = findViewById(R.id.edtGender_userProfile);
        userDateOfBirth = findViewById(R.id.edtDOB_userProfile);
        userContactNumber = findViewById(R.id.edtContactNumber_userProfile);

        showAllUserData();

        backButton = (Button) findViewById(R.id.btnBack_userProfile);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboard();
            }
        });
    }

    private void openDashboard() {
        Intent intent = new Intent(getApplicationContext(),dashboardPage.class);
    }

    private void showAllUserData() {
        Intent intent = getIntent();

        String xUserName = intent.getStringExtra("username");
        String xFirstName = intent.getStringExtra("firstname");
        String xMiddleName = intent.getStringExtra("middlename");
        String xLastName = intent.getStringExtra("lastname");
        String xAddress = intent.getStringExtra("address");
        String xGender = intent.getStringExtra("gender");
        String xDateOfBirth = intent.getStringExtra("birthday");
        String xContactNumber = intent.getStringExtra("contact");
        String xFullName = xFirstName + " " + xMiddleName + " " + xLastName;

        userFullName.setText(xFullName);
        userUserName.setText(xUserName);
        userFirstName.setText(xFirstName);
        userMiddleName.setText(xMiddleName);
        userLastName.setText(xLastName);
        userAddress.setText(xAddress);
        userGender.setText(xGender);
        userDateOfBirth.setText(xDateOfBirth);
        userContactNumber.setText(xContactNumber);
    }
}