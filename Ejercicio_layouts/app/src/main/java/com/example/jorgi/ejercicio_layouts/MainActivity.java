package com.example.jorgi.ejercicio_layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends Activity {

    RadioButton btnRojo;
    RadioButton btnVerde;
    RadioButton btnAzul;
    Button btnSetColor;
    Button btnCancel;

    Button btnTable;
    Button btnRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRojo = (RadioButton)findViewById(R.id.btnRojo);
        btnVerde = (RadioButton)findViewById(R.id.btnVerde);
        btnAzul = (RadioButton)findViewById(R.id.btnAzul);
        btnSetColor = (Button)findViewById(R.id.btnSetColor);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnTable = (Button)findViewById(R.id.btnTable);
        btnRelative = (Button)findViewById(R.id.btnRelative);

        btnTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent miIntent= new Intent(MainActivity.this, tableLayout_activity.class);
                startActivity(miIntent);
            }
        });

        btnRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent= new Intent(MainActivity.this, tableLayout_activity.class);
                startActivity(miIntent);
            }
        });

    }

}
