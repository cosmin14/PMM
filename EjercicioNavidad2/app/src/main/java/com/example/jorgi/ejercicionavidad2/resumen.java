package com.example.jorgi.ejercicionavidad2;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class resumen extends ActionBarActivity {

    /* DATOS */
    TextView  nombre, apellido1, apellido2, comAut, provincia, localidad, direccion, email;

    /* PEDIDO */
    TextView continente, envio, tarifaPeso, precio, peso;
    ImageView imageViewContinente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        Resources res = getResources();

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tabDatos);
        spec.setIndicator("", res.getDrawable(android.R.drawable.ic_menu_crop));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tabPedido);
        spec.setIndicator("", res.getDrawable(android.R.drawable.ic_menu_view));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        /* PESTAÑA DE DATOS */

        nombre = (TextView)findViewById(R.id.textNombre);
        apellido1 = (TextView)findViewById(R.id.textApellido1);
        apellido2 = (TextView)findViewById(R.id.textApellido2);
        comAut = (TextView)findViewById(R.id.textViewComAut);
        provincia = (TextView)findViewById(R.id.textViewProvincia);
        localidad = (TextView)findViewById(R.id.textViewLocalidad);
        direccion = (TextView)findViewById(R.id.textViewDireccion);
        email = (TextView)findViewById(R.id.textViewEmail);


        /* PESTAÑA PEDIDO */

        continente = (TextView)findViewById(R.id.textContinente);
        imageViewContinente = (ImageView)findViewById(R.id.imageViewContinente);
        envio = (TextView)findViewById(R.id.textViewEnvio);
        tarifaPeso = (TextView)findViewById(R.id.textViewTarifaPeso);
        precio = (TextView)findViewById(R.id.textViewPrecio);
        peso = (TextView)findViewById(R.id.textViewPeso);

        registerForContextMenu(imageViewContinente);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {

            /* DATOS */
            String nombreStr = extras.getString("nombre");
            String apellido1Str = extras.getString("apellido1");
            String apellido2Str = extras.getString("apellido2");
            String comAutStr = extras.getString("comAut");
            String provinciaStr = extras.getString("provincia");
            String localidadStr = extras.getString("localidad");
            String direccionStr = extras.getString("direccion");
            String emailStr = extras.getString("email");

            nombre.setText(nombreStr);
            apellido1.setText(apellido1Str);
            apellido2.setText(apellido2Str);
            comAut.setText(comAutStr);
            provincia.setText(provinciaStr);
            localidad.setText(localidadStr);
            direccion.setText(direccionStr);
            email.setText(emailStr);


            /* PEDIDO */
            Continente contSelected = (Continente)getIntent().getSerializableExtra("objetoContinente");
            contSelected.getNombre();

            String continenteStr = contSelected.getNombre();
            int zonaStr = contSelected.getZona();
            double precioStr = contSelected.getPrecio();
            String tarifaPesoStr = extras.getString("tarifaPeso");
            String envioStr = extras.getString("envio");
            String pesoStr = extras.getString("peso");

            envio.setText(envioStr+"€");
            tarifaPeso.setText(tarifaPesoStr+" €/Kg");
            precio.setText(precioStr+"€");
            peso.setText(pesoStr+"Kg");

            //envio, tarifaPeso, precio, peso

            continente.setText("Zona " + zonaStr + ": " + continenteStr + " /€");
            imageViewContinente.setImageResource(contSelected.getImagen());
        }

    }// onCreate

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
        if (id == R.id.action_about) {
            Intent intentMain = new Intent(resumen.this ,
                    about.class);
            startActivity(intentMain);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_context1:
                Intent intentMain = new Intent(resumen.this ,informacion.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
