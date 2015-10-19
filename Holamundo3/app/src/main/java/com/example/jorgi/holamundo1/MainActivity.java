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

    public static int COD_RESPUESTA=0;
    TextView elSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // final TextView mensajeIni;
        final EditText miTexto = (EditText) findViewById(R.id.nombre);
        final Button miBoton = (Button) findViewById(R.id.enviar);
        final TextView elSaludo = (TextView) findViewById(R.id.elSaludo);

        //Borrar el texto inicial del EditText
        miTexto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                miTexto.setText("");
            }
        });
        elSaludo.setText("");

        miBoton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent= new Intent(MainActivity.this, Pantalla2.class);
                    Bundle miBundle=new Bundle();
                    String mensajePaso= miTexto.getText().toString();
                    miBundle.putString("SALUDO", mensajePaso);
                miIntent.putExtras(miBundle);
                startActivityForResult(miIntent, COD_RESPUESTA);
            } });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String resultado = intent.getExtras().getString("DEVUELTO");
        elSaludo = (TextView) findViewById(R.id.elSaludo);
        elSaludo.setText("TE DEVUELVO EL SALUDO " + resultado);
    }
}
