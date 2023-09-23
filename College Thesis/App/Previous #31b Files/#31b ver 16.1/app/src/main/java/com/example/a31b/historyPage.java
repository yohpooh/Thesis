package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class historyPage extends AppCompatActivity {

    TextView history;
    Button back;

    private ArrayList<String> refnumber = new ArrayList<>();

    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList <his> list;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_page);

       // DatabaseReference Reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bemergency-2b09a-default-rtdb.firebaseio.com").child("History");
      //  history = findViewById(R.id.Username_history);

        back = findViewById(R.id.btnBack_History);
        Intent intent = getIntent();
        String uname = intent.getStringExtra("username");

       // history.setText(uname);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backusername();
            }
        });

        recyclerView = findViewById(R.id.userlist);
        database = FirebaseDatabase.getInstance().getReference("History").child(uname);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    his hist = dataSnapshot.getValue(his.class);
                    list.add(hist);
                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void backusername() {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // String email = intent.getStringExtra("email");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    //   String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);

                    // PINAPALITAN TO!! ITO UNG DESTINATION PAGE!!
                    Intent intent = new Intent(getApplicationContext(), dashboardPage.class);
                    intent.putExtra("username", usernameFromDB);
                    //   intent.putExtra("email", emailFromDB);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}