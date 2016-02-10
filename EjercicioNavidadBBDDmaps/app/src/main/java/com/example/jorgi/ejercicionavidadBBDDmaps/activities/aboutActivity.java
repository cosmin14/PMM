package com.example.jorgi.ejercicionavidadBBDDmaps.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jorgi.ejercicionavidadBBDDmaps.R;

public class aboutActivity extends ActionBarActivity {

    SharedPreferences prefs;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiDibujo(this));

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        user = prefs.getString("email", "otro");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intentMain;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_inicio) {
            intentMain = new Intent(aboutActivity.this ,pedidoActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_logout){
            prefs.edit().clear().commit();
            intentMain = new Intent(aboutActivity.this , loginActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_admin){
            intentMain = new Intent(aboutActivity.this , adminActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_localizacion){
            intentMain = new Intent(aboutActivity.this ,MapsActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_resumen){
            intentMain = new Intent(aboutActivity.this ,resumenActivity.class);
            startActivity(intentMain);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MiDibujo extends View {
        public MiDibujo(Context c){

            super(c);
        }

        protected void onDraw(Canvas lienzo){
            Paint miPincel= new Paint();
            miPincel.setColor(Color.BLACK);
            miPincel.setStyle(Paint.Style.STROKE);
            miPincel.setStrokeWidth(5);
            miPincel.setTextSize(70);

            int width = this.getWidth();
            int ancho = width/5;
            int height = this.getHeight();
            int altura = height / 20;

            lienzo.drawText(width + " - " + ancho + " / " + height + " - " + altura,100,100,miPincel);

            lienzo.drawRect(ancho, altura * 5, ancho * 4, altura * 11, miPincel);
            lienzo.drawLine(ancho, altura * 5, width / 2, altura * 8, miPincel);
            lienzo.drawLine(width/2, altura * 8, ancho * 4, altura * 5, miPincel);

            miPincel.setStrokeWidth(3);
            miPincel.setTextSize(60);
            lienzo.drawText("Empresa de envio", ancho, altura*18, miPincel);
            lienzo.drawText("®cosmin", ancho, altura* 19, miPincel);


        }
    }

}
