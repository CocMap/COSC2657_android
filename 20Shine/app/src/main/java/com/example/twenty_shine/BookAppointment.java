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

public class BookAppointment extends AppCompatActivity {
    private static final String TAG = "BookAppointment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);
        Log.d(TAG, "onCreate: Started.");

        Button btnNext = (Button) findViewById(R.id.next_button);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked btnToHairstyle.");

                Intent intent = new Intent(BookAppointment.this, BookAppointment2.class);
                startActivity(intent);
            }
        });
    }
}


