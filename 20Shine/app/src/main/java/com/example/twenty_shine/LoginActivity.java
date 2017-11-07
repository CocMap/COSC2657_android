package com.example.twenty_shine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Created by Coc Map on 07/11/2017.
 */

public class LoginActivity extends AppCompatActivity{
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Log.d(TAG, "onCreate: Starting.");


        Button btnToLogin = (Button) findViewById(R.id.login_button);

        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked btnToRegister.");

                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
