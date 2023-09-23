package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/*import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener; */

public class popUpCall extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com");

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
  //  SinchClient sinchClient;
   // Call call;
    String userId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_call);


        //pop up settings
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //getWindow().setLayout((int) (width*.7),(int) (height*.5));
        //getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);

        /*
        //call
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = firebaseUser.getUid();

        sinchClient = Sinch.getSinchClientBuilder()
                .context(this)
                .userId(userId)
                .applicationKey("1d9d12f4-0679-495e-80f0-686c27305e3d")
                .environmentHost("clientapi.sinch.com")
                .build();

        sinchClient.setSupportManagedPush(true);
        sinchClient.startListeningOnActiveConnection();

        sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());

        sinchClient.start();


        final RelativeLayout poracHotline = findViewById(R.id.buttonPoracHotline_popUpCall);
        poracHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Users user = snapshot.
                        callUser(hotlineName);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });*/
    }
    /*
    private class SinchCallListener implements CallListener{

        @Override
        public void onCallProgressing(com.sinch.android.rtc.calling.Call call) {
            Toast.makeText(getApplicationContext(),"Ringing ...", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCallEstablished(com.sinch.android.rtc.calling.Call call) {
            Toast.makeText(getApplicationContext(),"Call Established", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCallEnded(com.sinch.android.rtc.calling.Call endedCall) {
            Toast.makeText(getApplicationContext(),"Call Ended", Toast.LENGTH_LONG).show();
            call = null;
            endedCall.hangup();
        }
    }

    private class SinchCallClientListener implements CallClientListener{

        @Override
        public void onIncomingCall(CallClient callClient, final com.sinch.android.rtc.calling.Call incomingCall) {
            AlertDialog alertDialog = new AlertDialog.Builder(popUpCall.this).create();
            alertDialog.setTitle("CALLING!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Reject", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    incomingCall.hangup();
                }
            });

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Pick", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    call = incomingCall;
                    call.answer();
                    call.addCallListener(new SinchCallListener());
                    Toast.makeText(getApplicationContext(),"Call is started", Toast.LENGTH_LONG).show();
                }
            });

            alertDialog.show();
        }
    }

    public void callUser(Users hotlineName){
        if (call == null){
            call = sinchClient.getCallClient().callUser(hotlineName);
            call.addCallListener(new SinchCallListener());

            openCallerDialog(call);
        }
    }

    public void openCallerDialog(Call call){
        AlertDialog alertDialogCall = new AlertDialog.Builder(getApplicationContext()).create();
        alertDialogCall.setTitle("ALERT");
        alertDialogCall.setMessage("Calling");
        alertDialogCall.setButton(AlertDialog.BUTTON_NEUTRAL, "Hang up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                call.hangup();
            }
        });

        alertDialogCall.show();
    }
*/


}