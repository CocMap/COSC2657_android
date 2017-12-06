package com.example.twenty_shine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Coc Map on 07/11/2017.
 */

public class LoginActivity extends AppCompatActivity{
    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mEmail, mPassword;
    private Button btnToLogin, btnToLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Log.d(TAG, "onCreate: Starting.");

        //vars
        mEmail = (EditText)findViewById(R.id.username_enter);
        mPassword = (EditText)findViewById(R.id.password_enter);
        btnToLogin = (Button) findViewById(R.id.login_button);
        btnToLogout = (Button)findViewById(R.id.logout_button);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    Log.d(TAG, "onAuthStateChanged: *****sign_in" + user.getUid());
                    Toast.makeText(LoginActivity.this, "Successful signed in with "
                            + user.getEmail(), Toast.LENGTH_SHORT).show();


                    btnToLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    //user is signed out
                    Log.d(TAG, "onAuthStateChanged: *****signed_out");
                }
            }
        };

        //clicked on button login
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Log.d(TAG, "onClick: *****Clicked btnToLogin.");

            //authentication
            String email = mEmail.getText().toString();
            String pass = mPassword.getText().toString();
            if(!email.equals("") && !pass.equals("")){
                mAuth.signInWithEmailAndPassword(email,pass);
            } else {
                Toast.makeText(LoginActivity.this, "Please fill in UserName and Password",
                        Toast.LENGTH_SHORT).show();
            }
            }
        });

        //clickec on button logout
        btnToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: *****loging out.");

                mAuth.signOut();
                Toast.makeText(LoginActivity.this, "Logging out...",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
