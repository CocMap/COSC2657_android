package com.example.twenty_shine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Coc Map on 07/11/2017.
 */

public class HairstyleActivity extends AppCompatActivity {

    //vars
    private static final String TAG = "HairstyleActivity";
    private ImageButton btnHR1, btnHR2, btnHR3, btnHR4, btnHR5;

    //add Firebase Database vars
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private EditText mEmail, mPassword;
    private Button btnToLogin;

    //client data info
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hairstyle_layout);
        Log.d(TAG, "onCreate: *****HairstyleActivity Started.");

        //declare button
        btnHR1 = (ImageButton) findViewById(R.id.hairstyle1);
        btnHR2 = (ImageButton) findViewById(R.id.hairstyle2);
        btnHR3 = (ImageButton) findViewById(R.id.hairstyle3);
        btnHR4 = (ImageButton) findViewById(R.id.hairstyle4);
        btnHR5 = (ImageButton) findViewById(R.id.hairstyle5);

        //declare database reference objects
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = mFirebaseDatabase.getReference();

        //database function
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                //user is signed in
                Log.d(TAG, "onAuthStateChanged: *****sign_in" + user.getUid());
                Toast.makeText(HairstyleActivity.this, "Successful signed in with "
                        + user.getEmail(), Toast.LENGTH_SHORT).show();


                btnToLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(HairstyleActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }
                });
            } else {
                //user is signed out
                Log.d(TAG, "onAuthStateChanged: *****signed_out");
            }
            }
        };

        //read from database
        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d(TAG, "onDataChange: ");
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
////                Object value = dataSnapshot.getValue();
//                Log.d(TAG, "Value is: " + value);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });


        //function to buttons
        btnHR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

                String newOrder = "dateCreated" + date.toString();

                if(!newOrder.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Order").child("Hairstyle").setValue("hairstyle1");

                    Toast.makeText(HairstyleActivity.this, "Successful hairstyle", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(HairstyleActivity.this, BookAppointment.class);
                startActivity(intent);
            }
        });


        btnHR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

             String newOrder = "dateCreated" + date.toString();
//                String newOrder = "Hairstyle";

                if(!newOrder.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Order").child("Hairstyle").setValue("hairstyle2");

                    Toast.makeText(HairstyleActivity.this, "Successful hairstyle", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(HairstyleActivity.this, BookAppointment.class);
                startActivity(intent);
            }
        });

        btnHR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

//                String newOrder = date.toString();

                String newOrder = "dateCreated" + date.toString();

                if(!newOrder.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Order").child("Hairstyle").setValue("hairstyle3");

                    Toast.makeText(HairstyleActivity.this, "Successful hairstyle", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(HairstyleActivity.this, BookAppointment.class);
                startActivity(intent);
            }
        });

        btnHR4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

//                String newOrder = date.toString();
                String newOrder = "dateCreated " + date.toString();

                if(!newOrder.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Order").child("Hairstyle").setValue("hairstyle4");

                    Toast.makeText(HairstyleActivity.this, "Successful hairstyle", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(HairstyleActivity.this, BookAppointment.class);
                startActivity(intent);
            }
        });

        btnHR5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

//                String dateCreatedOrder = date.toString();


                String newOrder = "dateCreated " + date.toString();


                if(!newOrder.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Order").child("Hairstyle").setValue("hairstyle5");

                    Toast.makeText(HairstyleActivity.this, "Successful hairstyle", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(HairstyleActivity.this, BookAppointment.class);
                startActivity(intent);
            }
        });
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
}

