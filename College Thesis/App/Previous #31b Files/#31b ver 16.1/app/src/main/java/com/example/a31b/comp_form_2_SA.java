package com.example.a31b;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class comp_form_2_SA extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnersa;
    String choicesa;

    String senderEmail, senderPassword, senderTo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_form2_sa);

        //verification variables declaration
        senderEmail = "31b.emergency@gmail.com"; //sender email
        senderPassword = "C02emergencyapp"; // sender password
        //senderTo = "31b.emergency@gmail.com"; //recepient email

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        senderTo = email;


        final Button submitsa = findViewById(R.id.btnNext_offender_2);
        final TextView refnumsa = findViewById(R.id.refnum_sa);
        final EditText sa_vname = findViewById(R.id.edtVicName_comp_form2_sa);
        final EditText sa_vsex = findViewById(R.id.edtVicGender_comp_form2_sa);
        final EditText sa_vage = findViewById(R.id.edtVicAge_comp_form2_sa);
        final EditText sa_vaddress = findViewById(R.id.edtVicAdd_comp_form2_sa);
        final EditText sa_vcontact = findViewById(R.id.edtVicCon_comp_form2_sa);
        final EditText sa_oname = findViewById(R.id.edtOffName_comp_form2_sa);
        final EditText sa_osex = findViewById(R.id.edtOffGender_comp_form2_sa);
        final EditText sa_oage = findViewById(R.id.edtOffAge_comp_form2_sa);
        final EditText sa_oaddress = findViewById(R.id.edtOffAdd_comp_form2_sa);
      //  final EditText sa_owork = findViewById(R.id.edtOffOccup_comp_form2_sa);
        final EditText sa_ostate = findViewById(R.id.edtConditionOff_comp_form2_sa);
        final EditText sa_nature = findViewById(R.id.edtDes_comp_form2_sa);

        Calendar calendar = Calendar.getInstance();
       // String currentdate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        String currentdate = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH).format(calendar.getTime());

        Random random = new Random();
        int val = random.nextInt(89999)+10000;
        //(bound: 89999)+10000
        refnumsa.setText("SA_"+Integer.toString(val));

        spinnersa = findViewById(R.id.spinner_sa);//lagi pinapalitan
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersa.setAdapter(adapter);

        spinnersa.setOnItemSelectedListener(this);



        submitsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");

                //get data from edittext into string variables
                final String victimnameTxt = sa_vname.getText().toString();
                final String victimsexTxt = sa_vsex.getText().toString();
                final String victimageTxt = sa_vage.getText().toString();
                final String victimaddressTxt = sa_vaddress.getText().toString();
                final String victimcontactTxt = sa_vcontact.getText().toString();
                final String offnameTxt = sa_oname.getText().toString();
                final String offageTxt = sa_oage.getText().toString();
                final String offsexTxt = sa_osex.getText().toString();
                final String offaddressTxt = sa_oaddress.getText().toString();
             //   final String offworkTxt = sa_owork.getText().toString();
                final String offstateTxt = sa_ostate.getText().toString();
                final String offnatureTxt = sa_nature.getText().toString();
                final String codeTxt = refnumsa.getText().toString();
                final String munitxt = choicesa;
                final String dateTxt = currentdate;
                DatabaseReference Reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com").child("History");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com").child(munitxt);
                //DatabaseReference Reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com");


                //check if user fill all the fields before sending data to firebase
                if(victimnameTxt.isEmpty() || victimageTxt.isEmpty() || victimaddressTxt.isEmpty() || victimcontactTxt.isEmpty())
                {
                    Toast.makeText(comp_form_2_SA.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                //check if passwords are matching with each other
                //if not matching with each other then show a toast message
                else
                {
                   Reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if phone is not registered before
                            if (snapshot.hasChild(codeTxt)){
                                Toast.makeText(comp_form_2_SA.this, "Complaint is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //sending data to firebase Realtime Database
                                Reference.child(username).child(codeTxt).child("Reference_Number").setValue(codeTxt);
                                Reference.child(username).child(codeTxt).child("Date_Filed").setValue(dateTxt);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    databaseReference.child("Sexual_Abuse").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if phone is not registered before
                            if (snapshot.hasChild(codeTxt)){
                                Toast.makeText(comp_form_2_SA.this, "Complaint is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //sending data to firebase Realtime Database
                                //using phone number as unique identifier
                                // all other details of users come under phone number
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Victim_Fullname").setValue(victimnameTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Victim_Sex").setValue(victimsexTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Victim_Address").setValue(victimaddressTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Victim_Age").setValue(victimageTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Victim_ContactNumber").setValue(victimcontactTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Offender_Fullname").setValue(offnameTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Offender_Sex").setValue(offsexTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Offender_Age").setValue(offageTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Offender_Address").setValue(offaddressTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Offender_State").setValue(offstateTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Nature_of_Complaint").setValue(offnatureTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Reference_Number").setValue(codeTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Place_of_Incident").setValue(munitxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("Date_Filed").setValue(dateTxt);
                                databaseReference.child("Sexual_Abuse").child(codeTxt).child("username").setValue(username);


                                // show a success message then finish the activity
                                Toast.makeText(comp_form_2_SA.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), complaint_page_four_sa.class);
                                intent.putExtra("Reference_Number", codeTxt);
                                intent.putExtra("Place_of_Incident", munitxt);
                                intent.putExtra("username", username);

                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

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


                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(senderEmail));
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(senderTo));
                    message.setSubject(codeTxt);
                    message.setText(
                            "Date Filed " + dateTxt + "\n" +
                            "Victim Full Name " + victimnameTxt + "\n" +
                            "Victim Sex " + victimsexTxt + "\n" +
                            "Victim Address " + victimaddressTxt + "\n" +
                            "Victim Age " + victimageTxt + "\n" +
                            "Victim Contact Number " + victimcontactTxt + "\n" +
                            "Offender Full Name " + offnameTxt + "\n" +
                            "Offender Sex " + offsexTxt + "\n" +
                            "Offender Age " + offageTxt + "\n" +
                            "Offender Address " + offaddressTxt + "\n" +
                            "Offender State " + offstateTxt + "\n" +
                            "Nature of Complain " + offnatureTxt + "\n" +
                            "Place of Incident " + munitxt + "\n"
                    );

                    new sendMail().execute(message);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

              //  Intent intent = new Intent(comp_form_2_SA.this, complaint_page_four_pa.class);
               // startActivity(intent);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        choicesa = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
