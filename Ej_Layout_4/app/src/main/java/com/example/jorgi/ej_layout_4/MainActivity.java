package com.example.jorgi.ej_layout_4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView textViewColor;
    ToggleButton toogleButtonRojo;
    ToggleButton toogleButtonAzul;
    ToggleButton toogleButtonVerde;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewColor = (TextView)findViewById(R.id.textViewColor);
        toogleButtonRojo = (ToggleButton)findViewById(R.id.toggleButtonRojo);
        toogleButtonAzul = (ToggleButton)findViewById(R.id.toggleButtonAzul);
        toogleButtonVerde = (ToggleButton)findViewById(R.id.toggleButtonVerde);

        toogleButtonRojo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    textViewColor.setBackgroundColor(Color.RED);
                else if (toogleButtonAzul.isChecked())
                    textViewColor.setBackgroundColor(Color.BLUE);
                else if (toogleButtonVerde.isChecked())
                    textViewColor.setBackgroundColor(Color.GREEN);
                else
                    textViewColor.setBackgroundColor(Color.BLACK);
            }
        });
        toogleButtonAzul.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    textViewColor.setBackgroundColor(Color.BLUE);
                else if (toogleButtonRojo.isChecked())
                    textViewColor.setBackgroundColor(Color.RED);
                else if (toogleButtonVerde.isChecked())
                    textViewColor.setBackgroundColor(Color.GREEN);
                else
                    textViewColor.setBackgroundColor(Color.BLACK);
            }
        });
        toogleButtonVerde.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    textViewColor.setBackgroundColor(Color.GREEN);
                else if (toogleButtonRojo.isChecked())
                    textViewColor.setBackgroundColor(Color.RED);
                else if (toogleButtonAzul.isChecked())
                    textViewColor.setBackgroundColor(Color.BLUE);
                else
                    textViewColor.setBackgroundColor(Color.BLACK);
            }
        });

    }
}
