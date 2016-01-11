package com.example.jorgi.animacionejemplo1;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Animacion extends AppCompatActivity {

    AnimationDrawable animacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_animacion);
        final Button btnTr = (Button)findViewById(R.id.btnTransicion);

        animacion = (AnimationDrawable)getResources().getDrawable(R.drawable.animacion);
        ImageView imagen = new ImageView(this);
        imagen.setImageDrawable(animacion);
        setContentView(imagen);

        btnTr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = getIntent();
                setResult(RESULT_OK, i);
                finish();
            }
        });


        /*btnTr = (Button)findViewById(R.id.btnAnimacion);
        btnTr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent miIntent= new Intent(Animacion.this, Transicion.class);
                Bundle miBundle=new Bundle();
                miIntent.putExtras(miBundle);
                startActivity(miIntent);
            }
        });*/

    }

    public boolean onTouchEvent(MotionEvent evento) {
        //Al realizar una pulsación en pantalla
        if (evento.getAction() == MotionEvent.ACTION_DOWN) {
            //Ponemos en marcha la animación
            animacion.start();
            return true;
        }
        return super.onTouchEvent(evento);
    }
}
