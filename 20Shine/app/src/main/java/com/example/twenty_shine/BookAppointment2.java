package com.example.twenty_shine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Coc Map on 07/11/2017.
 */

public class BookAppointment2 extends AppCompatActivity {
    private static final String TAG = "BookAppointment2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book2_layout);
        Log.d(TAG, "onCreate: Started.");

        Button btnConfirm = (Button) findViewById(R.id.next_button);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked btnToHairstyle.");

                Intent intent = new Intent(BookAppointment2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
