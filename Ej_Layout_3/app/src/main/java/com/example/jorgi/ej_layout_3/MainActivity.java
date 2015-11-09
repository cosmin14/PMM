package com.example.jorgi.ej_layout_3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioBtnAzul;
    RadioButton radioBtnRojo;
    RadioButton radioBtnVerde;
    TextView textViewColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioBtnAzul = (RadioButton)findViewById(R.id.radioButtonAzul);
        radioBtnRojo = (RadioButton)findViewById(R.id.radioButtonRojo);
        radioBtnVerde = (RadioButton)findViewById(R.id.radioButtonVerde);
        textViewColor = (TextView)findViewById(R.id.textViewColor);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonAzul){
                    textViewColor.setBackgroundColor(Color.BLUE);
                    Toast.makeText(MainActivity.this, "AZUL", Toast.LENGTH_SHORT).show();
                }
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonRojo){
                    textViewColor.setBackgroundColor(Color.RED);
                    Toast.makeText(MainActivity.this, "ROJO", Toast.LENGTH_SHORT).show();
                }
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonVerde){
                    textViewColor.setBackgroundColor(Color.GREEN);
                    Toast.makeText(MainActivity.this, "VERDE", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
