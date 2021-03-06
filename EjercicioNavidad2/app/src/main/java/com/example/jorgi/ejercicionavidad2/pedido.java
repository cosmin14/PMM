package com.example.jorgi.ejercicionavidad2;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class pedido extends ActionBarActivity {

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

    private Continente[] continentes =
            new Continente[] {
                    new Continente(0, "Europa", 1, 10, R.drawable.europa),
                    new Continente(1, "America", 2, 20, R.drawable.america),
                    new Continente(2, "Africa", 2, 20, R.drawable.africa),
                    new Continente(3, "Asia", 3, 30, R.drawable.asia),
                    new Continente(4, "Oceania", 3, 30, R.drawable.oceania),
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

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
                //toast1.show();
                //peso = ((float)progress / 10.0);
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



        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), datos.class);
                Bundle miBundle = new Bundle();
                miBundle.putSerializable("objetoContinente", continentes[idContinente]);
                miBundle.putString("precio", precio + "");
                miBundle.putString("peso", peso + "");
                miBundle.putString("envio",envio+"");
                miBundle.putString("tarifaPeso", tarifaPeso+"");
                miBundle.putString("precioFinal", precioFinal+"");

                i.putExtras(miBundle);
                startActivity(i);
            }
        });

    }// /onCreate






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pedido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intentMain = new Intent(pedido.this ,
                    about.class);
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
        img.setImageResource(continentes[id].getImagen());
    }





    public void showToast(String text){
        Toast.makeText(pedido.this, text, Toast.LENGTH_SHORT).show();
    }

    public void showPrecio(double total){
        total = Math.round(total * 100);
        total = total/100;
        textViewPrecioFinal.setText("Precio: " + total + "€");
    }
    public void calcularPrecio(){
        Continente cont1 = continentes[idContinente];
        precio = cont1.getPrecio();
        precioFinal = precio + (tarifaPeso * peso) * envio;
        showPrecio(precioFinal);
    }


    class AdaptadorZona extends ArrayAdapter<Continente> {

        public Activity context;

        public AdaptadorZona(Activity context) {
            super(context, R.layout.continente_view, continentes);
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

            lblZona.setText("Zona "+continentes[position].getZona());
            lblContinente.setText(""+continentes[position].getNombre());
            lblPrecio.setText(""+continentes[position].getPrecio()+"€");

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
                Intent intentMain = new Intent(pedido.this ,informacion.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
