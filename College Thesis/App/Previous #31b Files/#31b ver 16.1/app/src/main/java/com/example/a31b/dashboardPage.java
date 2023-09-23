package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class dashboardPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com");

    private Button backButton;

    private CardView stepOne;
    private CardView stepTwo;
    private CardView stepThree;
    private CardView stepFour;
    private CardView stepFive;
    private CardView stepSix;

    private FloatingActionButton navButton;

    private FloatingActionButton callButton;

    private FirebaseUser firebaseUser;
    //private DatabaseReference databaseReference;

    private FirebaseAuth mAuth;


    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_page);
        mAuth = FirebaseAuth.getInstance();

        //get username profile
        final TextView userFullName = findViewById(R.id.fullnameDisplay_navDrawer);
        Intent intent = getIntent();
        String usersUsername = intent.getStringExtra("username");
        if (!usersUsername.isEmpty()){
            readData(usersUsername);
            /*databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(usersUsername).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()){
                        if (task.getResult().exists()){
                            DataSnapshot dataSnapshot = task.getResult();
                            String firstName = String.valueOf(dataSnapshot.child("firstname").getValue());
                            String lastName = String.valueOf(dataSnapshot.child("firstname").getValue());
                            userFullName.setText(firstName + " " + lastName);
                            //Toast.makeText(getApplicationContext(), firstName + " Successfully read!",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Something is wrong!",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Something is wrong!",Toast.LENGTH_LONG).show();
                    }

                }
            });*/

        }else{
            Toast.makeText(getApplicationContext(), "Something is wrong!",Toast.LENGTH_LONG).show();
        }


        //Menu hooks
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        //Show User Fullname
        //firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        //final TextView userFullName = findViewById(R.id.fullnameDisplay_navDrawer);

        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Users usersProfile = snapshot.getValue(Users.class);
                if(usersProfile != null){
                    String usersFirstName = usersProfile.firstname;
                    String usersMiddleName = usersProfile.middlename;
                    String usersLastName = usersProfile.lastname;

                    userFullName.setText(usersFirstName + " " + usersMiddleName + " " + usersLastName);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        navButton = (FloatingActionButton) findViewById(R.id.fabNavigationBar);
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });


       /* callButton = (FloatingActionButton) findViewById(R.id.btnCall_dashboardPage);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCall();
            }
        });
 */

      /*  backButton = (Button) findViewById(R.id.btnBack_dashboardPage);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        }); */

        stepOne = (CardView) findViewById(R.id.stepOne_dashboardPage);
        stepOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // !!ITO YUNG MAY KASAMA NA USERNAME PAG NAPUNTA SA NEXT PAGE!!!
                showUserFullName();


              //  openComplaint();

            }
        });

        stepTwo = (CardView) findViewById(R.id.stepTwo_dashboardPage);
        stepTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(dashboardPage.this, emergency_hotlines_pg1.class);
                //startActivity(intent);

                Intent intent = getIntent();
                String username = intent.getStringExtra("username");

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("username").equalTo(username);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);
                            Intent intent = new Intent(dashboardPage.this, emergency_hotlines_pg1.class);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("email", emailFromDB);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        stepThree = (CardView) findViewById(R.id.stepThree_dashboardPage);
        stepThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(dashboardPage.this, social_service.class);
                //startActivity(intent);

                Intent intent = getIntent();
                String username = intent.getStringExtra("username");

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("username").equalTo(username);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                             String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);
                            Intent intent = new Intent(dashboardPage.this, social_service.class);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("email", emailFromDB);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        stepFour = (CardView) findViewById(R.id.stepFour_dashboardPage);
        stepFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(dashboardPage.this, health_care.class);
                //startActivity(intent);

                Intent intent = getIntent();
                String username = intent.getStringExtra("username");

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("username").equalTo(username);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);
                            Intent intent = new Intent(dashboardPage.this, health_care.class);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("email", emailFromDB);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        stepFive = (CardView) findViewById(R.id.stepFive_dashboardPage);
        stepFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("username").equalTo(username);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);
                            Intent intent = new Intent(getApplicationContext(), information_board.class);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("email", emailFromDB);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //Intent intent = new Intent(dashboardPage.this, information_board.class);
                //startActivity(intent);
            }
        });

       stepSix = (CardView) findViewById(R.id.stepSix_dashboardPage);
        stepSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Historyusername();
               // Intent intent = new Intent(dashboardPage.this, historyPage.class);
               // startActivity(intent);
            }
        });



    }

    private void readData(String usersUsername) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(usersUsername).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String firstName = String.valueOf(dataSnapshot.child("firstname").getValue());
                        String lastName = String.valueOf(dataSnapshot.child("lastname").getValue());
                        Toast.makeText(getApplicationContext(),  "Welcome" + " " + firstName + " " + lastName,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Something is wrong!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Something is wrong!",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    // !!!!!ITO YUNG PARA SA USERNAME!!!!!
    private void showUserFullName() {
       // MAGLALAGAY NG GANITO PER CARDVIEW!! WAG KALIMUTAN!!!!
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                        String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), complaintPage.class);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void Historyusername() {
        // MAGLALAGAY NG GANITO PER CARDVIEW!! WAG KALIMUTAN!!!!
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);
                    Intent intent = new Intent(getApplicationContext(), historyPage.class);
                    intent.putExtra("username", usernameFromDB);
                    intent.putExtra("email", emailFromDB);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }

    public void openLogin() {
        Intent intent = new Intent(this, loginPage.class);
        startActivity(intent);
    }

    public void openComplaint() {
        Intent intent = new Intent(this, complaintPage.class);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

          /*   case R.id.nav_profile:
                Intent profile = new Intent(getApplicationContext(),userProfile.class);Intent intent = getIntent();
               String usersUsername = intent.getStringExtra("username");
                if (!usersUsername.isEmpty()){
                    readData(usersUsername);
                }else{
                    Toast.makeText(getApplicationContext(), "Something is wrong!",Toast.LENGTH_LONG).show();
                }

                readData(usersUsername);
                startActivity(profile);
                break;*/

            case R.id.nav_dptc:
                Intent dptcIntent = getIntent();
                String dptcUsername = dptcIntent.getStringExtra("username");

                DatabaseReference dptcreference = FirebaseDatabase.getInstance().getReference("Users");
                Query dptcCheckUser = dptcreference.orderByChild("username").equalTo(dptcUsername);

                dptcCheckUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            String usernameFromDB = dataSnapshot.child(dptcUsername).child("username").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(dptcUsername).child("email").getValue(String.class);
                            Intent dptc = new Intent(getApplicationContext(),data_privacy_nav_panel.class);
                            dptc.putExtra("username", usernameFromDB);
                            dptc.putExtra("email", emailFromDB);
                            startActivity(dptc);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case R.id.nav_about:
                Intent aboutIntent = getIntent();
                String aboutUsername = aboutIntent.getStringExtra("username");

                DatabaseReference aboutReference = FirebaseDatabase.getInstance().getReference("Users");
                Query aboutCheckUser = aboutReference.orderByChild("username").equalTo(aboutUsername);

                aboutCheckUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            String usernameFromDB = dataSnapshot.child(aboutUsername).child("username").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(aboutUsername).child("email").getValue(String.class);
                            Intent about = new Intent(getApplicationContext(),about_page.class);
                            about.putExtra("username", usernameFromDB);
                            about.putExtra("email", emailFromDB);
                            startActivity(about);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case R.id.nav_forgotpassword:
                Intent forgotIntent = getIntent();
                String forgotUsername = forgotIntent.getStringExtra("username");

                DatabaseReference forgotReference = FirebaseDatabase.getInstance().getReference("Users");
                Query forgotCheckUser = forgotReference.orderByChild("username").equalTo(forgotUsername);

                forgotCheckUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            String usernameFromDB = dataSnapshot.child(forgotUsername).child("username").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(forgotUsername).child("email").getValue(String.class);
                            Intent forgotpassword = new Intent(getApplicationContext(),nav_settings_change_pword.class);
                            forgotpassword.putExtra("username", usernameFromDB);
                            forgotpassword.putExtra("email", emailFromDB);
                            startActivity(forgotpassword);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case R.id.nav_logout:
                SharedPreferences myPrefs = getSharedPreferences("MY",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();

                mAuth.signOut();
                Intent logout = new Intent(getApplicationContext(), loginPage.class);
                logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                break;
            case R.id.nav_home:
                closeDrawer();

               // Intent home = new Intent(getApplicationContext(),dashboardPage.class);
               // startActivity(home);
                break;

        }

        return true;
    }

    private void closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public  void openCall(){
        Intent intent = new Intent(this, popUpCall.class);
        startActivity(intent);
    }
}