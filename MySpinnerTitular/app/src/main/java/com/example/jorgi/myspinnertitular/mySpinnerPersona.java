package com.example.jorgi.myspinnertitular;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class mySpinnerPersona extends AppCompatActivity {

    public Spinner miSpinner;

    private Persona[] personas =
            new Persona[] {
                    new Persona("Angeles","Campos",21),
                    new Persona("Consuelo","Martin",20),
                    new Persona("Fernando","Molina",26),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spinner_persona);

        miSpinner = (Spinner)findViewById(R.id.spPersona);
        AdaptadorPersona miAdaptador = new AdaptadorPersona(this);
        miSpinner.setAdapter(miAdaptador);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mensaje = "";
                mensaje = ""+ personas[position].getNombre() + " " + personas[position].getApellido() + " tiene " + personas[position].getEdad() + " años.";
                showToast(mensaje);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void showToast(String text){
        Toast.makeText(mySpinnerPersona.this, text, Toast.LENGTH_SHORT).show();
    }


    class AdaptadorPersona extends ArrayAdapter<Persona>{

        public Activity miActividad;

        public AdaptadorPersona(Activity laActividad){
            super(laActividad,R.layout.desmilista,personas);
            this.miActividad = laActividad;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vistaDesplegada = getView(position,convertView,parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = miActividad.getLayoutInflater();
            View item = inflater.inflate(R.layout.desmilista, null);

            TextView lblNombre = (TextView)findViewById(R.id.campoNombre);
            TextView lblApellido = (TextView)findViewById(R.id.campoApellido);
            TextView lblEdad = (TextView)findViewById(R.id.campoEdad);

            lblNombre.setText(""+personas[position].getNombre());
            lblApellido.setText(""+personas[position].getApellido());
            lblEdad.setText(""+Integer.toString(personas[position].getEdad()));


            return item;
        }
    }


}
