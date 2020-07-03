package com.example.smartirrigation;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UserData extends AppCompatActivity {

    TextView Moisture;
    DatabaseReference dref;
    String status;
    Button Low;
    Button Medium;
    Button High;
    TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        Low = findViewById(R.id.Low);
        Medium = findViewById(R.id.Medium);
        High = findViewById(R.id.High);
        Moisture = findViewById(R.id.textView2);
        dref= FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status= Objects.requireNonNull(dataSnapshot.child("moisture").getValue()).toString();
                Moisture.setText(status);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        //// Button Action goes here
        // consumption of low amount of water
        Low.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserData");
                myRef.setValue(950);

                openDialog();

            }
            public void openDialog(){
                TheDialog theDialog = new TheDialog();
                theDialog.show(getSupportFragmentManager(), "first button ko dialog");

            }
        });

        // consumption of medium amount of water level
        Medium.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserData");
                myRef.setValue(850);
                openDialog();


            }
            public void openDialog(){
                TheSecondDialog theDialog = new TheSecondDialog();
                theDialog.show(getSupportFragmentManager(), "second button ko dialog");

            }

        });

        // consumption of high water level
        High.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserData");
                myRef.setValue(750);
                openDialog();

            }
            public void openDialog(){
                TheThirdDialog theDialog = new TheThirdDialog();
                theDialog.show(getSupportFragmentManager(), "third button ko dialog");

            }
        });



        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent We = new Intent(v.getContext(), We.class);
                startActivity(We);
            }
        });


    }

    public void logout (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Userlogin.class));
    }

//    public void logout(View view) {
//
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(getApplicationContext(),Userlogin.class));
//        finish();
//    }
}