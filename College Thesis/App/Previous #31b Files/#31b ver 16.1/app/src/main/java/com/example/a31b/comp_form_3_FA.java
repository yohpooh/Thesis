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

import java.sql.Ref;
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

public class comp_form_3_FA extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerfa;
    String choicefa;

    String senderEmail, senderPassword, senderTo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_form3_fa);

        //verification variables declaration
        senderEmail = "31b.emergency@gmail.com"; //sender email
        senderPassword = "C02emergencyapp"; // sender password
        //senderTo = "31b.emergency@gmail.com"; //recepient email

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        senderTo = email;


        final Button submitfa = findViewById(R.id.btnNext_offender_4);
        final TextView refnumfa = findViewById(R.id.refnum_fa);
        final EditText fa_vname = findViewById(R.id.edtVicName_comp_form3_fa);
        final EditText fa_vsex = findViewById(R.id.edtVicGender_comp_form3_fa);
        final EditText fa_vage = findViewById(R.id.edtVicAge_comp_form3_fa);
        final EditText fa_vaddress = findViewById(R.id.edtVicAdd_comp_form3_fa);
        final EditText fa_vcontact = findViewById(R.id.edtVicCon_comp_form3_fa);
        final EditText fa_oname = findViewById(R.id.edtOffName_comp_form3_fa);
        final EditText fa_osex = findViewById(R.id.edtOffGender_comp_form3_fa);
        final EditText fa_oage = findViewById(R.id.edtOffAge_comp_form3_fa);
        final EditText fa_oaddress = findViewById(R.id.edtOffAdd_comp_form3_fa);
      //  final EditText fa_owork = findViewById(R.id.edtOffOccup_comp_form3_fa);
        final EditText fa_ostate = findViewById(R.id.edtConditionOff_comp_form3_fa);
        final EditText fa_nature = findViewById(R.id.edtDes_comp_form3_fa);

        Calendar calendar = Calendar.getInstance();
        //String currentdate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        String currentdate = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH).format(calendar.getTime());


        Random random = new Random();
        int val = random.nextInt(89999)+10000;
        //(bound: 89999)+10000
        refnumfa.setText("EA_"+Integer.toString(val));

        spinnerfa = findViewById(R.id.spinner_fa);//lagi pinapalitan
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfa.setAdapter(adapter);

        spinnerfa.setOnItemSelectedListener(this);


        submitfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                //get data from edittext into string variables
                final String victimnameTxt = fa_vname.getText().toString();
                final String victimsexTxt = fa_vsex.getText().toString();
                final String victimageTxt = fa_vage.getText().toString();
                final String victimaddressTxt = fa_vaddress.getText().toString();
                final String victimcontactTxt = fa_vcontact.getText().toString();
                final String offnameTxt = fa_oname.getText().toString();
                final String offageTxt = fa_oage.getText().toString();
                final String offsexTxt = fa_osex.getText().toString();
                final String offaddressTxt = fa_oaddress.getText().toString();
                final String offstateTxt = fa_ostate.getText().toString();
                final String offnatureTxt = fa_nature.getText().toString();
                final String codeTxt = refnumfa.getText().toString();
                final String munitxt = choicefa;
                final String dateTxt = currentdate;
                DatabaseReference Reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com").child("History");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com").child(munitxt);
                //DatabaseReference Reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com");


                //check if user fill all the fields before sending data to firebase
                if(victimnameTxt.isEmpty() || victimageTxt.isEmpty() || victimaddressTxt.isEmpty()|| victimcontactTxt.isEmpty())
                {
                    Toast.makeText(comp_form_3_FA.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                //check if passwords are matching with each other
                //if not matching with each other then show a toast message
                else
                {
                    Reference.child("Complaints").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(codeTxt)){
                                Toast.makeText(comp_form_3_FA.this, "Complaint is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                Reference.child(username).child(codeTxt).child("Reference_Number").setValue(codeTxt);
                                Reference.child(username).child(codeTxt).child("Date_Filed").setValue(dateTxt);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    databaseReference.child("Economical_Abuse").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if phone is not registered before
                            if (snapshot.hasChild(codeTxt)){
                                Toast.makeText(comp_form_3_FA.this, "Complaint is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //sending data to firebase Realtime Database
                                //using phone number as unique identifier
                                // all other details of users come under phone number
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Victim_Fullname").setValue(victimnameTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Victim_Sex").setValue(victimsexTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Victim_Address").setValue(victimaddressTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Victim_Age").setValue(victimageTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Victim_ContactNumber").setValue(victimcontactTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Offender_Fullname").setValue(offnameTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Offender_Sex").setValue(offsexTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Offender_Age").setValue(offageTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Offender_Address").setValue(offaddressTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Offender_State").setValue(offstateTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Nature_of_Complaint").setValue(offnatureTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Reference_Number").setValue(codeTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Place_of_Incident").setValue(munitxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("Date_Filed").setValue(dateTxt);
                                databaseReference.child("Economical_Abuse").child(codeTxt).child("username").setValue(username);


                                // show a success message then finish the activity
                                Toast.makeText(comp_form_3_FA.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), complaint_page_four_fa.class);
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


                // Intent intent = new Intent(comp_form_3_FA.this, complaint_page_four_fa.class);
                //startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        choicefa = adapterView.getItemAtPosition(i).toString();

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
