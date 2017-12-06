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
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Coc Map on 07/11/2017.
 */

public class BookAppointment2 extends AppCompatActivity {
    private static final String TAG = "BookAppointment2";

    //declare vars
    private TimePicker timePicker;
    private Button btnNext;

    //add Firebase Database vars
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private EditText mEmail, mPassword;
    private Button btnToLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book2_layout);
        Log.d(TAG, "onCreate: *****Date Picker Started.");

        //declare button
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        btnNext = (Button) findViewById(R.id.next_button);


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
                    Toast.makeText(BookAppointment2.this, "Successful signed in with "
                            + user.getEmail(), Toast.LENGTH_SHORT).show();


                    btnToLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(BookAppointment2.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    //user is signed out
                    Log.d(TAG, "onAuthStateChanged: *****signed_out");
                }
            }
        };


        //function to buttons
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

                String newOrder = timePicker.getCurrentHour().toString() + ":"
                        + timePicker.getCurrentMinute().toString();

                if (!newOrder.equals("")) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Order").child("timePicker").setValue(newOrder);

                    Toast.makeText(BookAppointment2.this, "Successful time picker", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(BookAppointment2.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
