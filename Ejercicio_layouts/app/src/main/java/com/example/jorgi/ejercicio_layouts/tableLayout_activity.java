package com.example.jorgi.ejercicio_layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tableLayout_activity extends Activity {

    Button btnLinear;
    Button btnRelative;

    Button btnrojo;
    Button btnamarillo;
    Button btnazul;

    Button btnborrar;

    TextView textViewcolorR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        btnLinear = (Button)findViewById(R.id.btnLinearT);
        btnRelative = (Button)findViewById(R.id.btnRelativeT);

        btnrojo = (Button)findViewById(R.id.btnrojoL);
        btnamarillo = (Button)findViewById(R.id.btnamarilloT);
        btnazul = (Button)findViewById(R.id.btnazulT);
        btnborrar = (Button)findViewById(R.id.btnborrarT);

        textViewcolorR = (TextView)findViewById(R.id.textViewcolorT);

        btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(tableLayout_activity.this, linearLayout_activity.class);
                startActivity(miIntent);
            }
        });

        btnRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(tableLayout_activity.this, relativeLayout_activity.class);
                startActivity(miIntent);
            }
        });

        btnrojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewcolorR.setBackgroundColor(getResources().getColor(R.color.colorRed));
            }
        });
        btnamarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewcolorR.setBackgroundColor(getResources().getColor(R.color.colorYellow));
            }
        });
        btnazul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewcolorR.setBackgroundColor(getResources().getColor(R.color.colorBlue));
            }
        });

        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewcolorR.setBackgroundColor(getResources().getColor(R.color.colorFondo));
            }
        });

    }


}
