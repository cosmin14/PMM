package com.example.jorgi.figurasaleatorias;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgi.figurasaleatorias.clases.Figuras;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MisDibujos extends AppCompatActivity {

    private Figuras figurasArray[] = {
            new Figuras("Circulo",R.drawable.circulo),
            new Figuras("Triangulo",R.drawable.triangulo),
            new Figuras("Rectangulo",R.drawable.rectangulo),
            new Figuras("Cuadrado",R.drawable.quadrado)};

    Spinner miSpinner;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Spinner miSpinner;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_dibujos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        AdaptadorFiguras adaptador = new AdaptadorFiguras(this);

        miSpinner = (Spinner) findViewById(R.id.spinnerFiguras);
        miSpinner.setAdapter(adaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                showToast("HOLA");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //Fin miSpinner
    }

    public void showToast(String text){
        Toast.makeText(MisDibujos.this, text, Toast.LENGTH_SHORT).show();
    }


    class AdaptadorFiguras extends ArrayAdapter<Figuras> {

        public Activity miActividad;

        public AdaptadorFiguras(Activity laActividad){
            super (laActividad, R.layout.figuras, figurasArray);
            this.miActividad = laActividad;

        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View ListaDesplegada = getView(position, convertView, parent);
            return ListaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = miActividad.getLayoutInflater();
            View item = inflater.inflate(R.layout.figuras, null);

            TextView lblNombre = (TextView) item.findViewById(R.id.lblTitulo);
            lblNombre.setText("" + figurasArray[position].getNombre()+ " ");

            ImageView imgFigura = (ImageView) item.findViewById(R.id.imagenFigura);
            imgFigura.setImageResource(figurasArray[position].getImagen());

            /*ImageView imgFigura = (ImageView) item.findViewById(R.id.imagenFigura);
            imgFigura.setImageResource(figurasArray[position].getImagen());
            if(imgFigura != null)
            {
                imgFigura.setBackgroundDrawable(getResources().getDrawable(figurasArray[position].getImagen()));
            }*/

            return item;
        }
    }//Fin AdaptadorFiguras

}
