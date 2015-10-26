package com.example.jorgi.ejercicio_layout_radio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    RadioButton btnRojo = (RadioButton)findViewById(R.id.rojo);
    RadioButton btnVerde = (RadioButton)findViewById(R.id.verde);
    RadioButton btnAzul = (RadioButton)findViewById(R.id.azul);
    Button setColor = (Button)findViewById(R.id.setColor);
    Button btnCancel = (Button)findViewById(R.id.cancel);

    Button btnTable = (Button)findViewById(R.id.btnTable);
    Button btnRelative = (Button)findViewById(R.id.btnRelative);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent= new Intent(MainActivity.this, tableLayout_activity.class);
                startActivity(miIntent);
            }
            }
        );

    }

}
