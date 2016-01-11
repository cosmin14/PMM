package com.example.jorgi.ejercicionavidad2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class about extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiDibujo(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pedido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_inicio) {
            Intent intentMain = new Intent(about.this ,
                    pedido.class);
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

            miPincel.setStrokeWidth(5);
            miPincel.setTextSize(60);
            lienzo.drawText("Empresa de envio", ancho, altura*18, miPincel);
            lienzo.drawText("®cosmin", ancho, altura* 19, miPincel);


        }
    }

}
