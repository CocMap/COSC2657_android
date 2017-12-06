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

/**
 * Created by Coc Map on 07/11/2017.
 */

public class ProductActivity extends AppCompatActivity{
    private static final String TAG = "HairstyleActivity";


    //declare vars
    private ImageButton product1, product2, product3, product4;

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
        setContentView(R.layout.product_layout);
        Log.d(TAG, "onCreate: *****product Picker Started.");

        //declare button

        product1 = (ImageButton) findViewById(R.id.product1);
        product2 = (ImageButton) findViewById(R.id.product2);
        product3 = (ImageButton) findViewById(R.id.product3);
        product4 = (ImageButton) findViewById(R.id.product4);


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
                    Toast.makeText(ProductActivity.this, "Successful signed in with "
                            + user.getEmail(), Toast.LENGTH_SHORT).show();


                    btnToLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(ProductActivity.this, MenuActivity.class);
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
        //function to buttons
        product1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

                String newOrder = "product 1";

                if(!newOrder.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Shopping").child("Product").setValue(newOrder);
                }

                Intent intent = new Intent(ProductActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        product2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

                String newOrder = "product 2";

                if(!newOrder.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Shopping").child("Product").setValue(newOrder);
                }

                Intent intent = new Intent(ProductActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        product3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

                String newOrder = "product 3";

                if(!newOrder.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Shopping").child("Product").setValue(newOrder);
                }

                Intent intent = new Intent(ProductActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        product4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to dd object to database");

                String newOrder = "product 4";

                if(!newOrder.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child(userID).child("Shopping").child("Product").setValue(newOrder);

                    Toast.makeText(ProductActivity.this, "1 item added", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(ProductActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
