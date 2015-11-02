package com.example.jorgi.implementacioneseventos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Context ctx = null; // Creamos un contexto para almacenar posteriormente el contenido de la Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this; // Almacenamos el contenido de la Activity
        setContentView(R.layout.activity_main);

        Button btn=(Button)this.findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Pulsado boton Normal",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void cmdDos_click(View v) {
        Toast.makeText(this, "Pulsado boton Funcion", Toast.LENGTH_SHORT).show();

    }
}
