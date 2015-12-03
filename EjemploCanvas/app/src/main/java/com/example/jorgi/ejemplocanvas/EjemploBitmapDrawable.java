package com.example.jorgi.ejemplocanvas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.io.Console;
import java.util.jar.Attributes;

public class EjemploBitmapDrawable extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_bitmap_drawable);
    }*/



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_bitmap_drawable);
        //setContentView(new EjemploView(this));
    }

    public static class EjemploView extends View {
        BitmapDrawable miImagen;

        public EjemploView(Context context, AttributeSet attrs) {
            super(context, attrs);
            Resources res = context.getResources();
            miImagen = (BitmapDrawable) res.getDrawable(R.drawable.logo_cefire);
            miImagen.setBounds(new Rect(30, 30, 200, 200));
        }

        public EjemploView(Context contexto ) {
            super(contexto);
            Resources res = contexto.getResources();
            miImagen = (BitmapDrawable) res.getDrawable(R.drawable.logo_cefire);
            miImagen.setBounds(new Rect(80, 80, 400, 400));
        }
        @Override
        protected void onDraw(Canvas canvas) {
            //Dentro de este método utilizamos los métodos para dibujar BitmapDrawable
            /*
            Paint pincel = new Paint();
            pincel.setColor(Color.RED);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawRect(500, 500, 500, 500, pincel);
            canvas.drawCircle(750, 1100, 400, pincel);7
            */
            miImagen.draw(canvas);
        }
    }
}

