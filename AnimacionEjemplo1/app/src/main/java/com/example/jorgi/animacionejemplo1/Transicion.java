package com.example.jorgi.animacionejemplo1;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Transicion extends AppCompatActivity {

    public static int COD_RESPUESTA=0;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transicion);

        ImageView imagen1 = (ImageView)findViewById(R.id.img1);

        TransitionDrawable mi_transicion = (TransitionDrawable)
                getResources().getDrawable(R.drawable.transicion);

        imagen1.setImageDrawable(mi_transicion);
        mi_transicion.startTransition(1000);

        btn = (Button)findViewById(R.id.btnAnimacion);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent miIntent= new Intent(Transicion.this, Animacion.class);
                Bundle miBundle=new Bundle();
                miIntent.putExtras(miBundle);
                startActivity(miIntent);*/
                Intent miIntent= new Intent(Transicion.this, Animacion.class);
                Bundle miBundle=new Bundle();
                miIntent.putExtras(miBundle);
                startActivityForResult(miIntent, COD_RESPUESTA);
            }
        });
    }

}
