package com.example.jorgi.ejercicio_layouts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class linearLayout_activity extends Activity {

    //RadioGroup radioGroupColores;
    RadioButton btnRojo;
    RadioButton btnVerde;
    RadioButton btnAzul;
    Button btnSetColor;
    Button btnCancel;

    TextView textColor;

    Button btnTable;
    Button btnRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        final RadioGroup radioGroupColores = (RadioGroup)findViewById(R.id.radioGroupColores);
        btnRojo = (RadioButton)findViewById(R.id.btnrojoL);
        btnVerde = (RadioButton)findViewById(R.id.btnVerdeL);
        btnAzul = (RadioButton)findViewById(R.id.btnAzulL);
        btnSetColor = (Button)findViewById(R.id.btnSetColorL);
        btnCancel = (Button)findViewById(R.id.btnCancelL);

        textColor = (TextView)findViewById(R.id.textColorL);

        btnTable = (Button)findViewById(R.id.btnLinearL);
        btnRelative = (Button)findViewById(R.id.btnRelativeL);

        btnTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent miIntent= new Intent(linearLayout_activity.this, tableLayout_activity.class);
                startActivity(miIntent);
            }
        });

        btnRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(linearLayout_activity.this, relativeLayout_activity.class);
                startActivity(miIntent);
            }
        });

        btnSetColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R.id.btnrojoL == radioGroupColores.getCheckedRadioButtonId()){
                    textColor.setBackgroundColor(Color.RED);
                }
                if (R.id.btnAzulL == radioGroupColores.getCheckedRadioButtonId()){
                    textColor.setBackgroundColor(Color.BLUE);
                }
                if (R.id.btnVerdeL == radioGroupColores.getCheckedRadioButtonId()){
                    textColor.setBackgroundColor(getResources().getColor(R.color.colorVerde));
                }
                int opcion = radioGroupColores.getCheckedRadioButtonId();
                textColor.setText("Color: " + opcion);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textColor.setBackgroundColor(getResources().getColor(R.color.colorFondo));
            }
        });

    }

}
