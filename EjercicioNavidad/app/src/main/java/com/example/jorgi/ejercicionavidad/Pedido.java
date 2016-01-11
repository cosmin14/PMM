package com.example.jorgi.ejercicionavidad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
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

public class Pedido extends Activity {

    Spinner spinnerZona;
    private SeekBar seekBar;
    private TextView textViewPeso, textViewPrecioFinal;
    private double tarifa, peso, envio = 1;
    private double precioFinal, zona = 10;
    RadioGroup radioGroup;
    RadioButton radioNormal, radioExpress;
    Button btnEnviar;

    private Continente[] continentes =
            new Continente[] {
                    new Continente("Europa", 1, 10),
                    new Continente("America", 2, 20),
                    new Continente("Africa", 2, 20),
                    new Continente("Asia", 3, 30),
                    new Continente("Oceania", 3, 30),
            };







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnEnviar = (Button)findViewById(R.id.buttonEnviar);
        textViewPrecioFinal = (TextView) findViewById(R.id.textViewPrecio);
        textViewPrecioFinal.setText("Precio: ");

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
         *   •	<6Kg se cobra 1 € /Kg
         *
         *   •	>=6 <=10 Kg se cobra a 1.5 € /kg
         *
         *   •	> 10Kg se tarifa 2 €  Kg
         */

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textViewPeso = (TextView) findViewById(R.id.textViewPeso);
        textViewPeso.setText("0");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //la Seekbar siempre empieza en cero, si queremos que el valor mínimo sea otro podemos modificarlo
                //Toast toast1 = Toast.makeText(Pedido.this, ""+progress, Toast.LENGTH_SHORT);
                //toast1.show();
                //peso = ((float)progress / 10.0);
                peso = (progress / 10.0);
                textViewPeso.setText("" + peso + "Kg");

                if (peso < 6) {
                    tarifa = peso;
                } else if (peso >= 6 || peso <= 10) {
                    tarifa = peso * 1.5;
                } else if (peso > 10) {
                    tarifa = peso * 2;
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

                if(checkedId == R.id.radioExpres) {
                    envio = 1.3;
                }else{
                    envio = 1;
                }

                calcularPrecio();
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
                cambiarImagenMundo(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Datos.class);
                Bundle miBundle = new Bundle();
                miBundle.putString("precio", precioFinal + "");
                miBundle.putString("peso", peso + "");
                miBundle.putString("tarifa", tarifa + "");
                miBundle.putString("zona",zona + "");
                i.putExtras(miBundle);
                startActivity(i);
            }
        });

    }// /onCreate
    //                      /SPINNER








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intentMain = new Intent(Pedido.this ,
                        about.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Funciona para cambiar la imagen del continente donde se envia el paquete
     * @param id
     */


    private void cambiarImagenMundo(long id){
        ImageView img = (ImageView)findViewById(R.id.imageView);

        int id2 = ((int) id);

        switch (id2) {
            case 0: img.setImageResource(R.drawable.europa); zona = 10;
                break;
            case 1: img.setImageResource(R.drawable.america); zona = 20;
                break;
            case 2: img.setImageResource(R.drawable.africa); zona = 20;
                break;
            case 3: img.setImageResource(R.drawable.asia); zona = 30;
                break;
            case 4: img.setImageResource(R.drawable.oceania); zona = 30;
                break;
            default: img.setImageResource(R.drawable.mundo);
                break;
        }
    }









    public void showToast(String text){
        Toast.makeText(Pedido.this, text, Toast.LENGTH_SHORT).show();
    }
    public void showPrecio(double total){
        total = Math.round(total * 100);
        total = total/100;
        textViewPrecioFinal.setText("Precio: " + total + "€");
    }
    public void calcularPrecio(){
        this.precioFinal = zona + tarifa * envio;
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



}
