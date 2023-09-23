package com.example.a31b;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class forgotpassword extends AppCompatActivity {

    TextInputLayout NPusername, NPemail, NPpassword;
    String _USERNAME, _EMAIL, _PASSWORD, _NAME;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        final Button back = findViewById(R.id.btnBack_changepassPage);

        //database
        reference = FirebaseDatabase.getInstance().getReference("Users");

        //hooks
        NPusername = findViewById(R.id.NPusername);
        NPemail = findViewById(R.id.NPemail);
        NPpassword = findViewById(R.id.NPpassword);

        showAllUserData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgotpassword.this, auth_changePass_page.class);
                startActivity(intent);
            }
        });
    }

    public void newpassword(View view) {
        if (isPasswordChange()){
            Toast.makeText(this, "Password has been updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(forgotpassword.this,loginPage.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Data is same and can not be updated", Toast.LENGTH_LONG).show();
        }
        //Intent intent = new Intent(this,loginPage.class);
        //startActivity(intent);
    }

    private void showAllUserData(){
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
      //  _NAME = intent.getStringExtra("fullname");
        _EMAIL = intent.getStringExtra("email");
        _PASSWORD = intent.getStringExtra("password");

        // fullNameLabel.setText(_NAME);
        // NPusername.setText(_USERNAME);
        NPusername.getEditText().setText(_USERNAME);
        NPemail.getEditText().setText(_EMAIL);
        NPpassword.getEditText().setText("");

    }


    private boolean isPasswordChange()
    {
        if (!_PASSWORD.equals(NPpassword.getEditText().getText().toString())){
            reference.child(_USERNAME).child("password").setValue(NPpassword.getEditText().getText().toString());
            return true;
        }
        else{
            return false;
        }
    }
}
