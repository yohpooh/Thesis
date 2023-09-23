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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class comp_form_4_PSYA extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerpsy;
    String choicepsy;

    String senderEmail, senderPassword, senderTo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_form4_psya);

        //verification variables declaration
        senderEmail = "31b.emergency@gmail.com"; //sender email
        senderPassword = "C02emergencyapp"; // sender password
        //senderTo = "31b.emergency@gmail.com"; //recepient email

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        senderTo = email;

        final Button submitpsya = findViewById(R.id.btnNext_offender_5);
        final TextView refnumpsy = findViewById(R.id.refnum_psy);
        final EditText psy_vname = findViewById(R.id.edtVicName_comp_form4_psy);
        final EditText psy_vsex = findViewById(R.id.edtVicGender_comp_form4_psy);
        final EditText psy_vage = findViewById(R.id.edtVicAge_comp_form4_psy);
        final EditText psy_vaddress = findViewById(R.id.edtVicAdd_comp_form4_psy);
        final EditText psy_vcontact = findViewById(R.id.edtVicCon_comp_form4_psy);
        final EditText psy_oname = findViewById(R.id.edtOffName_comp_form4_psy);
        final EditText psy_osex = findViewById(R.id.edtOffGender_comp_form4_psy);
        final EditText psy_oage = findViewById(R.id.edtOffAge_comp_form4_psy);
        final EditText psy_oaddress = findViewById(R.id.edtOffAdd_comp_form4_psy);
      //  final EditText psy_owork = findViewById(R.id.edtOffOccup_comp_form4_psy);
        final EditText psy_ostate = findViewById(R.id.edtConditionOff_comp_form4_psy);
        final EditText psy_nature = findViewById(R.id.edtDes_comp_form4_psy);

        Calendar calendar = Calendar.getInstance();
       //String currentdate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        String currentdate = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH).format(calendar.getTime());


        Random random = new Random();
        int val = random.nextInt(89999)+10000;
        //(bound: 89999)+10000
        refnumpsy.setText("PSY_"+Integer.toString(val));

        spinnerpsy = findViewById(R.id.spinner_psy);//lagi pinapalitan
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpsy.setAdapter(adapter);

        spinnerpsy.setOnItemSelectedListener(this);



        submitpsya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                String username = intent.getStringExtra("username");

                //get data from edittext into string variables
                final String victimnameTxt = psy_vname.getText().toString();
                final String victimsexTxt = psy_vsex.getText().toString();
                final String victimageTxt = psy_vage.getText().toString();
                final String victimaddressTxt = psy_vaddress.getText().toString();
                final String victimcontactTxt = psy_vcontact.getText().toString();
                final String offnameTxt = psy_oname.getText().toString();
                final String offageTxt = psy_oage.getText().toString();
                final String offsexTxt = psy_osex.getText().toString();
                final String offaddressTxt = psy_oaddress.getText().toString();
            //    final String offworkTxt = psy_owork.getText().toString();
                final String offstateTxt = psy_ostate.getText().toString();
                final String offnatureTxt = psy_nature.getText().toString();
                final String codeTxt = refnumpsy.getText().toString();
                final String munitxt = choicepsy;
                final String dateTxt = currentdate;
                DatabaseReference Reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com").child("History");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com").child(munitxt);
               // DatabaseReference Reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com");


                //check if user fill all the fields before sending data to firebase
                if(victimnameTxt.isEmpty() || victimageTxt.isEmpty() || victimaddressTxt.isEmpty() || victimcontactTxt.isEmpty())
                {
                    Toast.makeText(comp_form_4_PSYA.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                //check if passwords are matching with each other
                //if not matching with each other then show a toast message
                else
                {

                    Reference.child("Complaints").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if phone is not registered before
                            if (snapshot.hasChild(codeTxt)){
                                Toast.makeText(comp_form_4_PSYA.this, "Complaint is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //sending data to firebase Realtime Database
                                //using phone number as unique identifier
                                // all other details of users come under phone number
                               /*  Reference.child("Complaints").child(codeTxt).child("Victim_Fullname").setValue(victimnameTxt);
                                Reference.child("Complaints").child(codeTxt).child("Victim_Sex").setValue(victimsexTxt);
                                Reference.child("Complaints").child(codeTxt).child("Victim_Address").setValue(victimaddressTxt);
                                Reference.child("Complaints").child(codeTxt).child("Victim_Age").setValue(victimageTxt);
                                Reference.child("Complaints").child(codeTxt).child("Victim_ContactNumber").setValue(victimcontactTxt);
                                Reference.child("Complaints").child(codeTxt).child("Offender_Fullname").setValue(offnameTxt);
                                Reference.child("Complaints").child(codeTxt).child("Offender_Sex").setValue(offsexTxt);
                                Reference.child("Complaints").child(codeTxt).child("Offender_Age").setValue(offageTxt);
                                Reference.child("Complaints").child(codeTxt).child("Offender_Address").setValue(offaddressTxt);
                                Reference.child("Complaints").child(codeTxt).child("Offender_Work").setValue(offworkTxt);
                                Reference.child("Complaints").child(codeTxt).child("Offender_State").setValue(offstateTxt);
                                Reference.child("Complaints").child(codeTxt).child("Nature_of_Complaint").setValue(offnatureTxt);
                                Reference.child("Complaints").child(codeTxt).child("Reference_Number").setValue(codeTxt);
                                Reference.child("Complaints").child(codeTxt).child("Place_of_Incident").setValue(munitxt);
                                Reference.child("Complaints").child(codeTxt).child("Date_Filed").setValue(dateTxt);*/

                                Reference.child(username).child(codeTxt).child("Reference_Number").setValue(codeTxt);
                                Reference.child(username).child(codeTxt).child("Date_Filed").setValue(dateTxt);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    databaseReference.child("Psychological_Abuse").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if phone is not registered before
                            if (snapshot.hasChild(codeTxt)){
                                Toast.makeText(comp_form_4_PSYA.this, "Complaint is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //sending data to firebase Realtime Database
                                //using phone number as unique identifier
                                // all other details of users come under phone number
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Victim_Fullname").setValue(victimnameTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Victim_Sex").setValue(victimsexTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Victim_Address").setValue(victimaddressTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Victim_Age").setValue(victimageTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Victim_ContactNumber").setValue(victimcontactTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Offender_Fullname").setValue(offnameTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Offender_Sex").setValue(offsexTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Offender_Age").setValue(offageTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Offender_Address").setValue(offaddressTxt);
                          //      databaseReference.child("Psychological_Abuse").child(codeTxt).child("Offender_Work").setValue(offworkTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Offender_State").setValue(offstateTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Nature_of_Complaint").setValue(offnatureTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Reference_Number").setValue(codeTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Place_of_Incident").setValue(munitxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("Date_Filed").setValue(dateTxt);
                                databaseReference.child("Psychological_Abuse").child(codeTxt).child("username").setValue(username);


                                // show a success message then finish the activity
                                Toast.makeText(comp_form_4_PSYA.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                                //finish();

                                //String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);

                                //  Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                //Intent intent = new Intent(getApplicationContext(), dashboardPage.class);

                                Intent intent = new Intent(getApplicationContext(), complaint_page_four_psya.class);
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

                // Intent intent = new Intent(comp_form_4_PSYA.this, complaint_page_four_pa.class);
               //startActivity(intent);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        choicepsy = adapterView.getItemAtPosition(i).toString();

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


