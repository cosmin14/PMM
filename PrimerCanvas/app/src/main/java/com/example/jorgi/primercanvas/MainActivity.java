package com.example.jorgi.primercanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiDibujo(this));
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

            int radio = 200;
            // Cabeza
            lienzo.drawCircle(750,500,radio,miPincel);
            lienzo.drawCircle(690,450,40,miPincel);// Ojo izquierdo
            lienzo.drawCircle(680,440,5,miPincel);
            lienzo.drawCircle(680,460,5,miPincel);
            lienzo.drawCircle(700,440,5,miPincel);
            lienzo.drawCircle(700,460,5,miPincel);


            lienzo.drawCircle(810,450,40,miPincel);// Ojo derecho
            lienzo.drawCircle(800,440,5,miPincel);
            lienzo.drawCircle(800,460,5,miPincel);
            lienzo.drawCircle(820,440,5,miPincel);
            lienzo.drawCircle(820,460,5,miPincel);

            // Cuerpo
            lienzo.drawCircle(750,1100,400,miPincel);
            // Botones cuerpo
            lienzo.drawCircle(750,950,40,miPincel);
            lienzo.drawCircle(740,940,5,miPincel);
            lienzo.drawCircle(740,960,5,miPincel);
            lienzo.drawCircle(760,940,5,miPincel);
            lienzo.drawCircle(760,960,5,miPincel);

            lienzo.drawCircle(750,1050,40,miPincel);
            lienzo.drawCircle(740,1040,5,miPincel);
            lienzo.drawCircle(740,1060,5,miPincel);
            lienzo.drawCircle(760,1040,5,miPincel);
            lienzo.drawCircle(760,1060,5,miPincel);

            lienzo.drawCircle(750,1150,40,miPincel);
            lienzo.drawCircle(740,1140,5,miPincel);
            lienzo.drawCircle(740,1160,5,miPincel);
            lienzo.drawCircle(760,1140,5,miPincel);
            lienzo.drawCircle(760,1160,5,miPincel);


            /*for (int i=0; i<5; i++){
                lienzo.drawCircle(750,600,radio,miPincel);
                radio+=50;
            }*/

            // Param1 = punto donde se pinta la linia de la izquierda
            // Param2 = punto donde se pinta la linia superior
            // Param3 = punto donde se pinta la linia de la derecha
            // Param4 = punto donde se pinta la linia inferior
            // Conidiciones imprescindibles:
            //  Param3 > Param1
            //  Param4 > Param2

            RectF rec = new RectF(200, 200, 100, 520);
            lienzo.drawRect(rec, miPincel);

            Path p = new Path();

            // Brazo izquierdo
            p.moveTo(400, 1100);
            p.lineTo(100, 800);// Linia central
            p.moveTo(200, 900);// Punto inicio dedos
            p.lineTo(100, 900);// Linia dedo inferior
            p.moveTo(200, 900);
            p.lineTo(200, 800);// Linia dedo inferior

            // Brazo derecho
            p.moveTo(1100, 1100);
            p.lineTo(1400, 800);
            p.moveTo(1300, 900);// Punto inicio dedos
            p.lineTo(1300, 800);// Linia dedo superior
            p.moveTo(1300, 900);
            p.lineTo(1400, 900);// Linia dedo inferior

            lienzo.drawPath(p, miPincel);

            miPincel.setTextSize(60);
            lienzo.drawText("Mi mucñeco de nieve",500,1700,miPincel);


        }
    }

}
