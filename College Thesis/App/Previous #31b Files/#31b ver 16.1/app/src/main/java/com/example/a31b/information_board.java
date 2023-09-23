package com.example.a31b;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ArrayAdapter;
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

import HelperClasses.HomeAdapter.FeaturedHelperClass;
import HelperClasses.HomeAdapter.FeauturedAdapter;

public class information_board extends AppCompatActivity {

    RecyclerView info_recycler;
    RecyclerView.Adapter adapter;

    ListView listview;
   // ArrayList<String> myArraylist = new ArrayList<>();
    ArrayList <String> list ;
    ArrayAdapter<String> adapterr;
    Announcements ann;

    FirebaseDatabase database;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_board);

        ann = new Announcements();
        listview = (ListView) findViewById(R.id.listview);
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Announcements");
        list = new ArrayList<>();
        adapterr = new ArrayAdapter<String>(this,R.layout.announce, R.id.announce, list);


        //Hooks

        info_recycler = findViewById(R.id.info_board_feature);

        info_recycler();
        setupHyperlink();
        setupHyperlink2();
        setupHyperlink3();
        setupHyperlink4();
        setupHyperlink5();
        setupHyperlink6();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot ds : datasnapshot.getChildren() ){

                    ann = ds.getValue(Announcements.class);
                    list.add( "\n" +ann.getAnnounce().toString() +"\n"+ "Date Posted: " +ann.getPosteddate().toString() + "\n" + "Posted by: " + ann.getPostedby().toString() +"\n");
                }

                listview.setAdapter(adapterr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void setupHyperlink() {
        TextView pcw = findViewById(R.id.pcw);
        pcw.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void setupHyperlink2() {
        TextView pcw2 = findViewById(R.id.pcw2);
        pcw2.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void setupHyperlink3() {
        TextView pcw3 = findViewById(R.id.pcw3);
        pcw3.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void setupHyperlink4() {
        TextView pcw4 = findViewById(R.id.pcw4);
        pcw4.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void setupHyperlink5() {
        TextView pcw5 = findViewById(R.id.pcw5);
        pcw5.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void setupHyperlink6() {
        TextView pcwmain = findViewById(R.id.pcwmain);
        pcwmain.setMovementMethod(LinkMovementMethod.getInstance());
    }


    private void info_recycler() {

        info_recycler.setHasFixedSize(true);
        info_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.pub_physical, "1. PHYSICAL ABUSE", "R.A. 9262 defines PHYSICAL ABUSE (Physical Violence) as acts that include bodily or physical harm (battery)."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pub_sexual, "2. SEXUAL ABUSE", "R.A. 9262 defines SEXUAL ABUSE (Sexual Violence) the acts which are sexual in nature committed against a woman or her child."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pub_eco, "3. ECONOMIC ABUSE", "R.A. 9262 defines ECONOMIC ABUSE (Economic Violence) acts that make or attempt to make a woman financially dependent upon her abuser"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pub_psyc, "4. PSYCHOLOGICAL \t\t ABUSE", "R.A. 9262 defines PSYCHOLOGICAL ABUSE (Psychological Violence) is an acts or omissions causing or likely to cause mental or emotional suffering of the victim."));


        adapter = new FeauturedAdapter(featuredLocations);

        info_recycler.setAdapter(adapter);

    }
}