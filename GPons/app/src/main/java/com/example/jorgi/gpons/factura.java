package com.example.jorgi.gpons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class factura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        TextView nombrePizza = (TextView)findViewById(R.id.textViewPizza);
        TextView descriPizza = (TextView)findViewById(R.id.textViewPrecio);
        TextView txtExtras = (TextView)findViewById(R.id.textViewExtras);
        TextView txtUnidades = (TextView)findViewById(R.id.textViewUnidades);
        TextView txtEnvio = (TextView)findViewById(R.id.textViewEnvio);
        TextView txtTotal = (TextView)findViewById(R.id.textViewPrecioFinal);
        //ImageView imgViewPizza = (ImageView)findViewById(R.id.imageViewPizza);


        final Bundle miBundleRecoger = getIntent().getExtras();

        String imagenString = miBundleRecoger.getString("IMAGEN");
        //int imagen = Integer.parseInt(imagenString);

        nombrePizza.setText(""+ miBundleRecoger.getString("PIZZA"));
        descriPizza.setText(""+ miBundleRecoger.getString("PRECIO") + "€");
        txtExtras.setText(""+ miBundleRecoger.getString("EXTRAS") + "€");
        txtUnidades.setText(""+ miBundleRecoger.getString("UNIDADES"));
        txtEnvio.setText("" + miBundleRecoger.getString("ENVIO"));
        txtTotal.setText("" + miBundleRecoger.getString("PRECIOFINAL"));

    }
}
