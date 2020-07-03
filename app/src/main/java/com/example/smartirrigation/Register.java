package com.example.smartirrigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
//    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPhoneNumber, mPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    private FirebaseAuth fAuth;
    ProgressBar progressBar;


//    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.FullName);
        mPhoneNumber = findViewById(R.id.PhoneNumber);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mRegisterBtn = findViewById(R.id.SignUp);
        mLoginBtn = findViewById(R.id.LogIn);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }




        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                //final String fullName = mFullName.getText().toString();
                //final String phone = mPhoneNumber.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                } else if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                } else if (password.length() < 6) {
                    mPassword.setError("Password Must be >= 6 Characters");
                } else {
                    progressBar.setVisibility(View.VISIBLE);// register the user in firebase
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//
//                            // send verification link
//
//                            FirebaseUser user = fAuth.getCurrentUser();
//                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Toast.makeText(Register.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
//                                }
//                            });
//


                                Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                            userID = fAuth.getCurrentUser().getUid();
//
//                            Map<String,Object> user = new HashMap<>();
//                            user.put("fName",fullName);
//                            user.put("email",email);
//                            user.put("phone",phone);


                            } else {
                                Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });//                Intent MainActivity = new Intent(v.getContext(), MainActivity.class);
//                startActivity(MainActivity);
                }


            }


        });


//        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent MainActivity = new Intent(view.getContext(), MainActivity.class);
//                startActivity(MainActivity);
//            }
//        });

//        TextView mLoginBtn  = (TextView) findViewById(R.id.LogIn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Userlogin = new Intent(view.getContext(), Userlogin.class);
                startActivity(Userlogin);
            }
        });
    }
}
