package com.example.jorgi.holamundo1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

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
        final Button btnMusica = (Button) findViewById(R.id.btnMusica);

        final MediaPlayer miMusica = MediaPlayer.create(getApplicationContext(),R.raw.aspencat);
        miMusica.start();

        //Borrar el texto inicial del EditText
        miTexto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                miTexto.setText("");
            }
        });
        elSaludo.setText("");

        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(MainActivity.this, Pantalla2.class);
                Bundle miBundle = new Bundle();
                String mensajePaso = miTexto.getText().toString();
                miBundle.putString("SALUDO", mensajePaso);
                miIntent.putExtras(miBundle);
                startActivityForResult(miIntent, COD_RESPUESTA);
            }
        });
        btnMusica.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (miMusica.isPlaying()){
                    miMusica.pause();
                }else{
                    //miMusica.prepare();
                    miMusica.start();
                }
            }
        });



        showToast("Estamos en el main activity TOAST");
        showAlert("Estamos en el main activity ALERT");
    }

    protected void showToast(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    protected void showAlert(CharSequence text){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(text);
        alert.setPositiveButton(android.R.string.ok,null);
        alert.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String resultado = intent.getExtras().getString("DEVUELTO");
        elSaludo = (TextView) findViewById(R.id.elSaludo);
        elSaludo.setText("TE DEVUELVO EL SALUDO " + resultado);
    }


    @Override protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart 1", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume 1", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onPause() {
        Toast.makeText(this,"onPause 1", Toast.LENGTH_SHORT).show();
        super.onPause();
    }
    @Override protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop 1", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart 1", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onDestroy() {
        Toast.makeText(this, "onDestroy 1", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

}
