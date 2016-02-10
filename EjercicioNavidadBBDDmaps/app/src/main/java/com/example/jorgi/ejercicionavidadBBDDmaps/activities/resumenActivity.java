package com.example.jorgi.ejercicionavidadBBDDmaps.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgi.ejercicionavidadBBDDmaps.clases.Destino;
import com.example.jorgi.ejercicionavidadBBDDmaps.clases.EnviosSQLiteHelper;
import com.example.jorgi.ejercicionavidadBBDDmaps.clases.Pedido;
import com.example.jorgi.ejercicionavidadBBDDmaps.R;
import com.example.jorgi.ejercicionavidadBBDDmaps.clases.Usuario;

import java.util.List;

public class resumenActivity extends ActionBarActivity {

    /* DATOS */
    TextView  nombre, apellido1, apellido2, comAut, provincia, localidad, direccion, email, dni, pass;

    /* PEDIDO */
    TextView continente, envio, tarifaPeso, precio, peso;
    ImageView imageViewContinente;

    SharedPreferences prefs;
    String user;

    EnviosSQLiteHelper enviosHelper;
    LinearLayout layone;
    TabHost tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        user = prefs.getString("email", "otro");

        Resources res = getResources();

        layone= (LinearLayout) findViewById(R.id.tabPedido);// change id here

        /*layone= (LinearLayout) findViewById(R.id.tabPedido);// change id here
        layone.setVisibility(View.GONE);

        tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tabDatos);
        spec.setIndicator("", res.getDrawable(android.R.drawable.ic_menu_crop));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tabMisPedidos);
        spec.setIndicator("", res.getDrawable(android.R.drawable.ic_menu_agenda));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);*/


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
        //if (extras != null) {


            /* SQLHelper */

            enviosHelper = new EnviosSQLiteHelper(this, "DBEnvios", null, 1);

            /* DATOS */

            //Usuario u = (Usuario)extras.getSerializable("usuario");
            Usuario u = enviosHelper.getUsuarioByEmail(user.toString());


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

            if ((Destino)getIntent().getSerializableExtra("objetoContinente") != null){

                tabs=(TabHost)findViewById(android.R.id.tabhost);
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

                Destino contSelected = (Destino)getIntent().getSerializableExtra("objetoContinente");
                contSelected.getNombre();

                int idCont = contSelected.getId();
                String idContStr = String.valueOf(idCont);
                String continenteStr = contSelected.getNombre();
                String zonaStr = contSelected.getZona();
                int precioStr = contSelected.getPrecio();
                String tarifaPesoStr = extras.getString("tarifaPeso");
                String expressStr = extras.getString("express");
                Float express = Float.parseFloat(expressStr);
                String pesoStr = extras.getString("peso");

                Toast.makeText(resumenActivity.this, "Pedido realizado correctamente", Toast.LENGTH_SHORT).show();

                envio.setText(expressStr+"€");
                tarifaPeso.setText(tarifaPesoStr + " €/Kg");
                precio.setText(precioStr + "€");
                peso.setText(pesoStr + "Kg");

                continente.setText("ID: " + idContStr + " | Zona " + zonaStr + ": " + continenteStr + " " + precioStr + "€");
                imageViewContinente.setImageResource(contSelected.getImagen());

                Pedido p1 = new Pedido(u.getDni(), idContStr, pesoStr, tarifaPesoStr, express);
                enviosHelper.crearPedido(p1);

            }else{
                layone.setVisibility(View.GONE);

                tabs=(TabHost)findViewById(android.R.id.tabhost);
                tabs.setup();

                TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
                spec.setContent(R.id.tabDatos);
                spec.setIndicator("", res.getDrawable(android.R.drawable.ic_menu_crop));
                tabs.addTab(spec);

                spec = tabs.newTabSpec("mitab3");
                spec.setContent(R.id.tabMisPedidos);
                spec.setIndicator("", res.getDrawable(android.R.drawable.ic_menu_agenda));
                tabs.addTab(spec);

                tabs.setCurrentTab(0);

                Toast.makeText(resumenActivity.this, "No tiene ningun pedido en curso", Toast.LENGTH_SHORT).show();
            }

            List<Pedido> todosPedidos = enviosHelper.getAllPedidosUsuario(u.getDni());
            String[] pedidos = new String[todosPedidos.size()];
            int num = 0;
        Destino d;

        d = enviosHelper.getDestinoById(1);

            for (Pedido pedido : todosPedidos) {
                d = enviosHelper.getDestinoById(Integer.parseInt(pedido.getZonaId()));
                float precioTotal = d.getPrecio() + (Float.parseFloat(pedido.getTarifaPeso()) + Float.parseFloat(pedido.getPeso())) * pedido.getExpress();
                String linea = "Zona: "+pedido.getZonaId() + " - Pais: " + d.getNombre() + " - Precio: " + precioTotal +"€";
                pedidos[num] = linea;
                num++;
            }
            /**
             * En crear pedido creo el pedido con la zona, deberia crear el pedido con el codigo del destino, no con la zona.
             * Puesto que el codigo es el identificador i no la zona
            */
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pedidos);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.resumen_pedidos_view, pedidos);
            ListView lista = (ListView) findViewById(R.id.listViewMisPedidos);
            lista.setAdapter(adapter);
                //}
            //Cerramos la base de registroActivity
            enviosHelper.close();
        //}// if extras!=null
    }// onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        MenuItem admin = menu.findItem(R.id.action_admin);
        if (user.equals("admin@admin.com")){
            admin.setVisible(true);
        }else{
            admin.setVisible(false);
        }
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
        }else if (id == R.id.action_inicio){
            intentMain = new Intent(resumenActivity.this ,pedidoActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_logout){
            prefs.edit().clear().commit();
            intentMain = new Intent(resumenActivity.this , loginActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_admin){
            intentMain = new Intent(resumenActivity.this , adminActivity.class);
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
