package com.example.a31b;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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

public class comp_form1_pa extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergencydatabase-default-rtdb.firebaseio.com/");
   private Spinner spinner;
   String choice;

    String senderEmail, senderPassword, senderTo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_form1_pa);

        //verification variables declaration
        senderEmail = "31b.emergency@gmail.com"; //sender email
        senderPassword = "C02emergencyapp"; // sender password
        //senderTo = "31b.emergency@gmail.com"; //recepient email

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        senderTo = email;

        final Button submitpa = findViewById(R.id.btnNext_offender_1);
        final TextView refnumpa = findViewById(R.id.refnum_pa);
        final EditText pa_vname = findViewById(R.id.edtVicName_comp_form1_pa);
        final EditText pa_vsex = findViewById(R.id.edtVicGender_comp_form1_pa);
        final EditText pa_vage = findViewById(R.id.edtVicAge_comp_form1_pa);
        final EditText pa_vaddress = findViewById(R.id.edtVicAdd_comp_form1_pa);
        final EditText pa_vcontact = findViewById(R.id.edtVicCon_comp_form1_pa);
        final EditText pa_oname = findViewById(R.id.edtOffName_comp_form1_pa);
        final EditText pa_osex = findViewById(R.id.edtOffGender_comp_form1_pa);
        final EditText pa_oage = findViewById(R.id.edtOffAge_comp_form1_pa);
        final EditText pa_oaddress = findViewById(R.id.edtOffAdd_comp_form1_pa);
      //  final EditText pa_owork = findViewById(R.id.edtOffOccup_comp_form1_pa);
        final EditText pa_ostate = findViewById(R.id.edtConditionOff_comp_form1_pa);
        final EditText pa_nature = findViewById(R.id.edtDes_comp_form1_pa);

        Calendar calendar = Calendar.getInstance();
        //String currentdate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        String currentdate = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH).format(calendar.getTime());

        Random random = new Random();
        int val = random.nextInt(89999)+10000;
        //(bound: 89999)+10000
        refnumpa.setText("PHY_"+Integer.toString(val));

        spinner = findViewById(R.id.spinner_pa);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


         submitpa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            //get data from edittext into string variables
                            final String victimnameTxt = pa_vname.getText().toString();
                            final String victimsexTxt = pa_vsex.getText().toString();
                            final String victimageTxt = pa_vage.getText().toString();
                            final String victimaddressTxt = pa_vaddress.getText().toString();
                            final String victimcontactTxt = pa_vcontact.getText().toString();
                            final String offnameTxt = pa_oname.getText().toString();
                            final String offageTxt = pa_oage.getText().toString();
                            final String offsexTxt = pa_osex.getText().toString();
                            final String offaddressTxt = pa_oaddress.getText().toString();
                            final String offstateTxt = pa_ostate.getText().toString();
                            final String offnatureTxt = pa_nature.getText().toString();
                            final String codeTxt = refnumpa.getText().toString();
                            final String munitxt = choice;
                            final String dateTxt = currentdate;
                            DatabaseReference Reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com").child("History");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com").child(munitxt);


                            //check if user fill all the fields before sending data to firebase
                            if(victimnameTxt.isEmpty() || victimageTxt.isEmpty() || victimaddressTxt.isEmpty() || victimcontactTxt.isEmpty())
                            {
                                Toast.makeText(comp_form1_pa.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(comp_form1_pa.this, "Complaint is already registered", Toast.LENGTH_SHORT).show();
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


                                databaseReference.child("Physical_Abuse").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        //check if phone is not registered before
                                        if (snapshot.hasChild(codeTxt)){
                                            Toast.makeText(comp_form1_pa.this, "Complaint is already registered", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            //sending data to firebase Realtime Database
                                            //using phone number as unique identifier
                                            // all other details of users come under phone number
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Victim_Fullname").setValue(victimnameTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Victim_Sex").setValue(victimsexTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Victim_Address").setValue(victimaddressTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Victim_Age").setValue(victimageTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Victim_ContactNumber").setValue(victimcontactTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Offender_Fullname").setValue(offnameTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Offender_Sex").setValue(offsexTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Offender_Age").setValue(offageTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Offender_Address").setValue(offaddressTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Offender_State").setValue(offstateTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Nature_of_Complaint").setValue(offnatureTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Reference_Number").setValue(codeTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Place_of_Incident").setValue(munitxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("Date_Filed").setValue(dateTxt);
                                            databaseReference.child("Physical_Abuse").child(codeTxt).child("username").setValue(username);


                                            // show a success message then finish the activity
                                            Toast.makeText(comp_form1_pa.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                                                                                                // pinapalitan to, kase ito yung destination page
                                            Intent intent = new Intent(getApplicationContext(), complaint_page_four_pa.class);
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



                        }
                    });//end ng submit button

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        choice = adapterView.getItemAtPosition(i).toString();
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
