package com.example.jorgi.ej_layout_2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textViewColor = (TextView)findViewById(R.id.textView);
        ImageButton btnRojo = (ImageButton)findViewById(R.id.imageButtonRojo);
        ImageButton btnAzul = (ImageButton)findViewById(R.id.imageButtonAzul);

        btnAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewColor.setBackgroundColor(Color.BLUE);
            }
        });

        btnRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewColor.setBackgroundColor(Color.RED);
            }
        });



    }
}
