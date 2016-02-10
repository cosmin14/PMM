package com.example.jorgi.ejercicionavidadBBDD;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class pedidoActivity extends ActionBarActivity {

    Spinner spinnerZona;
    private SeekBar seekBar;
    private TextView textViewPeso, textViewPrecioFinal;
    /**
     * tarifaPeso: 0.5, 0.75 o 1 (En funcion del peso se paga mas)
     * peso: peso del paquete a enviar
     * envio: tipo de envio, Normal o express
     * precioFinal: precio total del envio
     * precio: Precio fijo en funcion del continente de destino
     */
    private double tarifaPeso = 1, peso = 1, envio = 1;
    private double precioFinal, precio = 10;
    private int idContinente, zona = 1;
    RadioGroup radioGroup;
    Button btnEnviar;
    ImageView img;
    SharedPreferences prefs;
    String userAdmin;
    int estadoSesion = 0; //0 no logueado - 1 logeado

    private Destino[] destinos = {
                    new Destino(0, "Europa", "1", 10, R.drawable.europa),
                    new Destino(1, "America", "2", 20, R.drawable.america),
                    new Destino(2, "Africa", "2", 20, R.drawable.africa),
                    new Destino(3, "Asia", "3", 30, R.drawable.asia),
                    new Destino(4, "Oceania", "3", 30, R.drawable.oceania),
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        userAdmin = prefs.getString("email", "otro");

        final Bundle extras = getIntent().getExtras(); // Recogo el bundle de la actividad anterior

        /*EnviosSQLiteHelper enviosHelper = new EnviosSQLiteHelper(this, "DBEnvios", null, 1);
        //SQLiteDatabase dbEnvios = enviosHelper.getWritableDatabase();

        int numDestinos = enviosHelper.getCountDestinos();

        for (int i = 0; i < destinos.length; i++){
            Destino d = new Destino( destinos[i].getNombre(), destinos[i].getZona(), destinos[i].getPrecio(), destinos[i].getImagen() );
            d.getNombre();
            enviosHelper.crearDestino(d);
        }*/


        /*if (numDestinos == 0){
            for (int i = 0; i < destinos.length; i++){
                Destino d = new Destino( destinos[i].getNombre(), destinos[i].getZona(), destinos[i].getPrecio(), destinos[i].getImagen() );
                d.getNombre();
                enviosHelper.crearDestino(d);
            }
        }*/

        img = (ImageView)findViewById(R.id.imageView);
        btnEnviar = (Button)findViewById(R.id.buttonEnviar);
        textViewPrecioFinal = (TextView) findViewById(R.id.textViewPrecio);
        textViewPrecioFinal.setText("Precio: ");

        registerForContextMenu(img);

        calcularPrecio();

        /**
         * TODO
         *
         * Crear una etiqueta de info:
         *
         * - Explicar el seekbar.
         * - Mostrar los precios de los paquetes.
         *
         */

        /**
         * SEEKBAR
         *
         *   •	<6Kg se cobra 0.5 € /Kg
         *
         *   •	>=6 <=10 Kg se cobra a 0.75 € /kg
         *
         *   •	> 10Kg se tarifaPeso 1 €  Kg
         */

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textViewPeso = (TextView) findViewById(R.id.textViewPeso);
        textViewPeso.setText("1Kg");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //la Seekbar siempre empieza en cero, si queremos que el valor mínimo sea otro podemos modificarlo
                //Toast toast1 = Toast.makeText(Pedido.this, ""+progress, Toast.LENGTH_SHORT);
                peso = (progress / 10.0);
                textViewPeso.setText("" + peso + "Kg");

                if (peso < 6) {                         tarifaPeso = 0.5;
                } else if (peso >= 6 || peso <= 10) {   tarifaPeso = 0.75;
                } else if (peso > 10) {                 tarifaPeso = 1;
                }
                calcularPrecio();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0){
            }
            @Override
            public void onStopTrackingTouch(SeekBar arg0){
            }
        });

        //                  /SEEKBAR



        /**
         *                  RADIOGROUP tipo de envio
         */
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calcularPrecio();
                if(checkedId == R.id.radioExpres) { envio = 1.3; calcularPrecio();
                }else{ envio = 1; calcularPrecio();}
            }

        });
        //                  /RADIOGROUP



        /**
         *                  SPINNER
         */

        spinnerZona = (Spinner) findViewById(R.id.spinnerZona);
        AdaptadorZona adaptador = new AdaptadorZona(this);
        spinnerZona.setAdapter(adaptador);

        spinnerZona.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarImagenMundo(position);
                idContinente = position; // ID del continente seleccionado en el spinner
                calcularPrecio();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //                      /SPINNER

        Usuario userLoged = (Usuario)extras.getSerializable("usuario");
        //Toast.makeText(pedidoActivity.this, "Usuario logeado: "+userLoged.getNombre(), Toast.LENGTH_LONG).show();


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), resumenActivity.class);
                Bundle miBundle = new Bundle();
                miBundle.putSerializable("objetoContinente", destinos[idContinente]);
                miBundle.putString("precio", precio + "");
                miBundle.putString("peso", peso + "");
                miBundle.putString("envio",envio+"");
                miBundle.putString("tarifaPeso", tarifaPeso+"");
                miBundle.putString("precioFinal", precioFinal+"");
                i.putExtras(miBundle);
                i.putExtras(extras);
                startActivity(i);
            }
        });

    }// /onCreate




    private static final int MENU_LOGOUT = Menu.FIRST;
    private static final int MENU_LOGIN = Menu.FIRST + 1;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_pedido, menu);

        MenuItem login = menu.findItem(R.id.action_login);
        MenuItem logout = menu.findItem(R.id.action_logout);
        MenuItem admin = menu.findItem(R.id.action_admin);

        login.setVisible(false);
        logout.setVisible(true);

        if (userAdmin.equals("admin@admin.com")){
            admin.setVisible(true);
        }else{
            admin.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intentMain = new Intent(pedidoActivity.this , aboutActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_login ){
            Intent intentMain = new Intent(pedidoActivity.this , loginActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_logout){
            prefs.edit().clear().commit();
            Intent intentMain = new Intent(pedidoActivity.this , loginActivity.class);
            startActivity(intentMain);
            return true;
        }else if (id == R.id.action_admin){
            Intent intentMain = new Intent(pedidoActivity.this , adminActivity.class);
            startActivity(intentMain);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Funciona para cambiar la imagen del continente donde se envia el paquete
     * @param id
     */

    private void cambiarImagenMundo(int id){
        img.setImageResource(destinos[id].getImagen());
    }


    public void showPrecio(double total){
        total = Math.round(total * 100);
        total = total/100;
        textViewPrecioFinal.setText("Precio: " + total + "€");
    }
    public void calcularPrecio(){
        Destino cont1 = destinos[idContinente];
        precio = cont1.getPrecio();
        precioFinal = precio + (tarifaPeso * peso) * envio;
        showPrecio(precioFinal);
    }


    class AdaptadorZona extends ArrayAdapter<Destino> {

        public Activity context;

        public AdaptadorZona(Activity context) {
            super(context, R.layout.continente_view, destinos);
            this.context = context;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vistaDesplegada = getView(position,convertView,parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.continente_view, null);

            TextView lblZona = (TextView) item.findViewById(R.id.lblZona);
            TextView lblContinente = (TextView) item.findViewById(R.id.lblContinente);
            TextView lblPrecio = (TextView) item.findViewById(R.id.lblPrecio);

            lblZona.setText("Zona "+ destinos[position].getZona());
            lblContinente.setText(""+ destinos[position].getNombre());
            lblPrecio.setText(""+ destinos[position].getPrecio()+"€");

            return (item);
        }
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
                Intent intentMain = new Intent(pedidoActivity.this ,informacionActivity.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
