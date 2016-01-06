package com.example.jorgi.gpons;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Pizza[] pizzas =
            new Pizza[]{
                    new Pizza("MARGARITA","jamon/tomate",12, R.drawable.pizza1),
                    new Pizza("TRES QUESOS","parmesano/gouda/roquefortt",15, R.drawable.pizza2),
                    new Pizza("PEPPERONI","jamon/pepperoni",13, R.drawable.pizza3)
            };

    Spinner miSpinner;
    Button btnCalcular, btnFactura;
    CheckBox checkGrande, checkIngredientes, checkQueso;
    RadioGroup radioGroupButtons;
    TextView textPrecio, textCantidad;
    ImageView imagenPizza;

    String nombrePizza;
    double precio,precioPizza;
    int cantidad, extras, imgPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miSpinner = (Spinner) findViewById(R.id.spinner);
        btnCalcular = (Button)findViewById(R.id.buttonCalcularPrecio);
        checkGrande = (CheckBox)findViewById(R.id.checkBoxGrande);
        checkIngredientes = (CheckBox)findViewById(R.id.checkBoxIngredientes);
        checkQueso = (CheckBox)findViewById(R.id.checkBoxQueso);
        radioGroupButtons = (RadioGroup)findViewById(R.id.radioGroup);
        textPrecio = (TextView)findViewById(R.id.textViewPrecioTotal);
        textCantidad = (EditText)findViewById(R.id.editTextCantidad);
        btnFactura = (Button)findViewById(R.id.buttonFactura);

        AdaptadorPizzas miAdaptador= new AdaptadorPizzas(this);
        miSpinner.setAdapter(miAdaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                precioPizza = pizzas[position].getPrecio();
                nombrePizza = pizzas[position].getNombre();
                imgPizza = pizzas[position].getImagen();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });//Fin miSpinner


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precio = 0;
                extras = 0;
                precio += precioPizza;
                cantidad = 0;
                cantidad = Integer.parseInt(String.valueOf(textCantidad.getText()));

                if (cantidad < 1) {
                    Toast.makeText(MainActivity.this, "Cantidad Erronea", Toast.LENGTH_SHORT).show();
                }

                if (checkGrande.isChecked()) {
                    extras += 1;
                    precio += 1;
                }
                if (checkIngredientes.isChecked()) {
                    extras += 1;
                    precio += 1;
                }
                if (checkQueso.isChecked()) {
                    extras += 1;
                    precio += 1;
                }
                if (R.id.radioButtonDomicilio == radioGroupButtons.getCheckedRadioButtonId()) {
                    precio *= 1.10;
                }
                precio *= cantidad;

                textPrecio.setText(" " + String.format("%.2f", precio) + "€");
            }
        });//btnCalcular

        btnFactura.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent= new Intent(MainActivity.this, factura.class);
                Bundle miBundle=new Bundle();
                miBundle.putString("PIZZA", nombrePizza);
                miBundle.putString("PRECIO", String.valueOf(precioPizza));
                miBundle.putString("EXTRAS", String.valueOf(extras));
                miBundle.putString("UNIDADES", String.valueOf(cantidad));
                if (R.id.radioButtonDomicilio == radioGroupButtons.getCheckedRadioButtonId()) {
                    miBundle.putString("ENVIO", "Domicilio");
                }
                if (R.id.radioButtonLocal == radioGroupButtons.getCheckedRadioButtonId()) {
                    miBundle.putString("ENVIO", "Local");
                }
                miBundle.putString("IMAGEN", String.valueOf(imagenPizza));
                miBundle.putString("PRECIOFINAL", String.valueOf(precio));
                miIntent.putExtras(miBundle);
                startActivity(miIntent);
            }
        });




    }//Fin onCreate

    class AdaptadorPizzas extends ArrayAdapter<Pizza> {

        public Activity miActividad;

        public AdaptadorPizzas(Activity laActividad){
            super (laActividad, R.layout.lista, pizzas);
            this.miActividad = laActividad;

        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View ListaDesplegada = getView(position, convertView, parent);
            return ListaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = miActividad.getLayoutInflater();
            View item = inflater.inflate(R.layout.lista, null);

            TextView lblNombre = (TextView) item.findViewById(R.id.textViewListaNombre);
            lblNombre.setText(""+pizzas[position].getNombre());

            TextView lblIngredientes = (TextView) item.findViewById(R.id.textViewListaIngrediente);
            lblIngredientes.setText(pizzas[position].getIngredientes());

            TextView lblPrecio = (TextView) item.findViewById(R.id.textViewListaPrecio);
            lblPrecio.setText(String.valueOf(pizzas[position].getPrecio()) + " €");

            ImageView imgPizza = (ImageView) item.findViewById(R.id.imageViewPizza);
            if(imgPizza != null)
            {
                imgPizza.setBackgroundDrawable(getResources().getDrawable(pizzas[position].getImagen()));
            }

            return (item);
        }
    }//Fin AdaptadorPizzas
}
