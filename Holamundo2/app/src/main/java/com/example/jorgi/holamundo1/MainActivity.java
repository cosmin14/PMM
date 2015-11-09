package com.example.jorgi.holamundo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // final TextView mensajeIni;
        final EditText miTexto = (EditText) findViewById(R.id.nombre);
        final EditText miEdad = (EditText) findViewById(R.id.edad);
        final Button miBoton = (Button) findViewById(R.id.enviar);
        final TextView elSaludo = (TextView) findViewById(R.id.mensaje);

        //Borrar el texto inicial del EditText
        miTexto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                miTexto.setText("");
            }
        });

        miBoton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent= new Intent(MainActivity.this, Pantalla2.class);
                    Bundle miBundle=new Bundle();
                    String mensajePaso= "Te saludo " + miTexto.getText();
                    String mensajeEdad= "Tu edad es de " + miEdad.getText();
                    miBundle.putString("TEXTO", mensajePaso);
                    miBundle.putString("EDAD", mensajeEdad);
                miIntent.putExtras(miBundle);
                startActivity(miIntent);
            }
        });


    }

}
