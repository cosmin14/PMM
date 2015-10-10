package com.example.jorgi.holamundo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText miTexto= (EditText)findViewById(R.id.nombre);
        final Button miBoton= (Button)findViewById(R.id.enviar);
        final TextView elSaludo= (TextView)findViewById(R.id.mensaje);

        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mensajePaso = "Te saludo " + miTexto.getText();
                elSaludo.setText(mensajePaso);
            }
        });
    }
}
