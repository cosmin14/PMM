package com.example.jorgi.ejercicionavidadBBDDmaps;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.List;

public class resumenActivity extends ActionBarActivity {

    /* DATOS */
    TextView  nombre, apellido1, apellido2, comAut, provincia, localidad, direccion, email, dni, pass;

    /* PEDIDO */
    TextView continente, envio, tarifaPeso, precio, peso;
    ImageView imageViewContinente;

    EnviosSQLiteHelper enviosHelper;

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

        spec = tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tabMisPedidos);
        spec.setIndicator("", res.getDrawable(android.R.drawable.ic_menu_agenda));
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
        dni = (TextView)findViewById(R.id.textViewDNI);
        pass = (TextView)findViewById(R.id.textViewPass);



        /* PESTAÑA PEDIDO */

        continente = (TextView)findViewById(R.id.textContinente);
        imageViewContinente = (ImageView)findViewById(R.id.imageViewContinente);
        envio = (TextView)findViewById(R.id.textViewEnvio);
        tarifaPeso = (TextView)findViewById(R.id.textViewTarifaPeso);
        precio = (TextView)findViewById(R.id.textViewPrecio);// Destino
        peso = (TextView)findViewById(R.id.textViewPeso);


        // Menu contextual - Asigno el menu contextual a la imagen
        registerForContextMenu(imageViewContinente);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {

            /* DATOS */

            Usuario u = (Usuario)extras.getSerializable("usuario");

            nombre.setText(u.getNombre());
            apellido1.setText(u.getApellido1());
            apellido2.setText(u.getApellido2());
            comAut.setText(u.getComAut());
            provincia.setText(u.getProvincia());
            localidad.setText(u.getLocalidad());
            direccion.setText(u.getDireccion());
            email.setText(u.getEmail());
            dni.setText(u.getDni());
            pass.setText(u.getPassword());


            /* PEDIDO */
            Destino contSelected = (Destino)getIntent().getSerializableExtra("objetoContinente");
            contSelected.getNombre();

            String continenteStr = contSelected.getNombre();
            String zonaStr = contSelected.getZona();
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

            /* SQLHelper */

            enviosHelper = new EnviosSQLiteHelper(this, "DBEnvios", null, 1);

            Pedido p1 = new Pedido(u.getDni(), zonaStr, pesoStr, tarifaPesoStr);
            enviosHelper.crearPedido(p1);

            List<Pedido> todosPedidos = enviosHelper.getAllPedidosUsuario(u.getDni());
            String[] pedidos = new String[todosPedidos.size()];
            int num = 0;

            for (Pedido pedido : todosPedidos) {
                String linea = pedido.getCodigo() + " - Nombre: " + u.getNombre() + "\n Zona: " + pedido.getZonaId();
                pedidos[num] = linea;
                num++;
            }

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pedidos);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.pedidos_view, pedidos);
            ListView lista = (ListView) findViewById(R.id.listViewMisPedidos);
            lista.setAdapter(adapter);
                //}
            //Cerramos la base de usuarioActivity
            enviosHelper.close();
        }// if extras!=null
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
        Intent intentMain;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            intentMain = new Intent(resumenActivity.this ,aboutActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_localizacion){
            intentMain = new Intent(resumenActivity.this ,MapsActivity.class);
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
        Intent intentMain;
        switch (item.getItemId()) {
            case R.id.menu_context1:
                intentMain = new Intent(resumenActivity.this ,informacionActivity.class);
                startActivity(intentMain);
                return true;
            case R.id.menu_context2:
                intentMain = new Intent(resumenActivity.this ,MapsActivity.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }




}
