package com.example.smartirrigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    Button logbtn, createbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);



        createbtn = findViewById(R.id.createBtn);
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Register = new Intent(view.getContext(),Register.class);
                startActivity(Register);

            }
        });

        logbtn = findViewById(R.id.loginBtn);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Userlogin = new Intent(view.getContext(),Userlogin.class);
                startActivity(Userlogin);
            }
        });


    }
}