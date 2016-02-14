package com.example.jorgi.figurasaleatorias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgi.figurasaleatorias.clases.Circulo;
import com.example.jorgi.figurasaleatorias.clases.Cuadrado;
import com.example.jorgi.figurasaleatorias.clases.Figura;
import com.example.jorgi.figurasaleatorias.clases.Rectangulo;
import com.example.jorgi.figurasaleatorias.clases.Triangulo;
import com.fourmob.colorpicker.ColorPickerDialog;
import com.fourmob.colorpicker.ColorPickerSwatch;

public class MisDibujos extends AppCompatActivity {

    private Figura figurasArray[] = {
            new Circulo("Circulo",R.drawable.circulo),
            new Triangulo("Triangulo",R.drawable.triangle),
            new Rectangulo("Rectangulo",R.drawable.rectangulo),
            new Cuadrado("Cuadrado",R.drawable.quadrado)};

    Button botonColor, botonCalcular;
    EditText editBase, editAltura, editRadio;
    String base, altura, radio;
    float baseF = 0, alturaF = 0, radioF = 0;
    int colorFigura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Spinner miSpinner;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_dibujos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editBase = (EditText)findViewById(R.id.editTextBase);
        editAltura = (EditText)findViewById(R.id.editTextAltura);
        editRadio = (EditText)findViewById(R.id.editTextRadio);

        botonCalcular = (Button)findViewById(R.id.buttonCalcular);


        /**
         *
         *      SELECCION DE COLOR PARA LA FIGURA A PINTAR
         *
         */

        botonColor = (Button)findViewById(R.id.buttonColor);
        botonColor.setBackgroundColor(Color.LTGRAY);
        colorFigura = Color.LTGRAY;
        //botonColor.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));

        final ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
        colorPickerDialog.initialize(R.string.dialog_title, new int[]{
                Color.CYAN,
                Color.LTGRAY,
                Color.BLACK,
                Color.BLUE,
                Color.GREEN,
                Color.MAGENTA,
                Color.RED,
                Color.GRAY,
                Color.YELLOW
        }, Color.LTGRAY, 3, 2);
        colorPickerDialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {

            @Override
            public void onColorSelected(int color) {
                botonColor.setBackgroundColor(color);
                colorFigura = color;
            }
        });


        botonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickerDialog.show(getSupportFragmentManager(), "colorpicker");
            }
        });

        /**
         *   /COLOR FIGURA
         */


        AdaptadorFiguras adaptador = new AdaptadorFiguras(this);

        miSpinner = (Spinner) findViewById(R.id.spinnerFiguras);
        miSpinner.setAdapter(adaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                if (miSpinner.getSelectedItem()==figurasArray[0]){
                    editRadio.setVisibility(View.VISIBLE);
                    editBase.setVisibility(View.GONE);
                    editAltura.setVisibility(View.GONE);
                }
                if (miSpinner.getSelectedItem()==figurasArray[1]){
                    editRadio.setVisibility(View.GONE);
                    editBase.setVisibility(View.VISIBLE);
                    editAltura.setVisibility(View.VISIBLE);
                }
                if (miSpinner.getSelectedItem()==figurasArray[2]){
                    editRadio.setVisibility(View.GONE);
                    editBase.setVisibility(View.VISIBLE);
                    editAltura.setVisibility(View.VISIBLE);
                }
                if (miSpinner.getSelectedItem()==figurasArray[3]){
                    editRadio.setVisibility(View.GONE);
                    editBase.setVisibility(View.VISIBLE);
                    editAltura.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //Fin miSpinner


        botonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;

                // Orientacion 1 = Vertical, 2 = Horizontal
                int orientacion = getResources().getConfiguration().orientation;

                Bundle miBundle = new Bundle();
                Intent i = new Intent(MisDibujos.this,MisDibujosPintar.class);

                if (miSpinner.getSelectedItem()==figurasArray[0]){
                    editRadio.setVisibility(View.VISIBLE);
                    editBase.setVisibility(View.GONE);
                    editAltura.setVisibility(View.GONE);
                    radio = editRadio.getText().toString();

                    try {
                        radioF = Float.parseFloat(radio);
                        if (radioF <= 0){
                            Toast.makeText(MisDibujos.this, "1. Valores no validos", Toast.LENGTH_SHORT).show();
                        }else if (radioF*2 > width && orientacion == 1){
                            Toast.makeText(MisDibujos.this, "1. Demasiado grande", Toast.LENGTH_SHORT).show();
                        }else if(radioF*2 > height && orientacion == 2){
                            Toast.makeText(MisDibujos.this, "2. Demasiado grande", Toast.LENGTH_SHORT).show();
                        }else{
                            Circulo c = new Circulo(colorFigura,radioF);
                            miBundle.putSerializable("figura", c);
                            i.putExtras(miBundle);
                            startActivity(i);
                        }
                    }catch (Exception e){
                        Toast.makeText(MisDibujos.this, "Campos obligatorios ", Toast.LENGTH_SHORT).show();
                    }
                }
                if (miSpinner.getSelectedItem()==figurasArray[1]){
                    editRadio.setVisibility(View.GONE);
                    editBase.setVisibility(View.VISIBLE);
                    editAltura.setVisibility(View.VISIBLE);
                    base = editBase.getText().toString();
                    altura = editAltura.getText().toString();
                    try {
                        baseF = Float.parseFloat(base);
                        alturaF = Float.parseFloat(altura);
                        if (baseF <= 0 && alturaF <= 0){
                            Toast.makeText(MisDibujos.this, "Valores no pueden ser menores que 1", Toast.LENGTH_SHORT).show();
                        }else if (altura.isEmpty() && base.isEmpty()){
                            Toast.makeText(MisDibujos.this, "Valores no validos", Toast.LENGTH_SHORT).show();
                        }else if ((baseF > width && orientacion == 1) || (alturaF > height && orientacion == 1)){
                            Toast.makeText(MisDibujos.this, "Demasiado grande", Toast.LENGTH_SHORT).show();
                        }else{
                            Triangulo t = new Triangulo(colorFigura,baseF,alturaF);
                            miBundle.putSerializable("figura",t);
                            i.putExtras(miBundle);
                            startActivity(i);
                        }
                    }catch (Exception e){
                        Toast.makeText(MisDibujos.this, "Campos obligatorios ", Toast.LENGTH_SHORT).show();
                    }
                }
                if (miSpinner.getSelectedItem()==figurasArray[2]){
                    editRadio.setVisibility(View.GONE);
                    editBase.setVisibility(View.VISIBLE);
                    editAltura.setVisibility(View.VISIBLE);
                    base = editBase.getText().toString();
                    altura = editAltura.getText().toString();

                    try {
                        baseF = Float.parseFloat(base);
                        alturaF = Float.parseFloat(altura);

                        if (baseF <= 0 && alturaF <= 0){
                            Toast.makeText(MisDibujos.this, "1. Valores no validos", Toast.LENGTH_SHORT).show();
                        }else if (baseF > width && orientacion == 1){
                            Toast.makeText(MisDibujos.this, "1. Demasiado grande", Toast.LENGTH_SHORT).show();
                        }else if(alturaF > height && orientacion == 2){
                            Toast.makeText(MisDibujos.this, "2. Demasiado grande", Toast.LENGTH_SHORT).show();
                        }else{
                            Rectangulo r = new Rectangulo(colorFigura,baseF,alturaF);
                            miBundle.putSerializable("figura",r);
                            i.putExtras(miBundle);
                            startActivity(i);
                        }
                    }catch (Exception e){
                        Toast.makeText(MisDibujos.this, "Campos obligatorios ", Toast.LENGTH_SHORT).show();
                    }
                }
                if (miSpinner.getSelectedItem()==figurasArray[3]){
                    editRadio.setVisibility(View.GONE);
                    editBase.setVisibility(View.VISIBLE);
                    editAltura.setVisibility(View.GONE);
                    base = editBase.getText().toString();

                    try {
                        baseF = Float.parseFloat(base);

                        if (baseF <= 0){
                            Toast.makeText(MisDibujos.this, "1. Valores no validos", Toast.LENGTH_SHORT).show();
                        }else if (baseF > width && orientacion == 1){
                            Toast.makeText(MisDibujos.this, "1. Demasiado grande", Toast.LENGTH_SHORT).show();
                        }else if(baseF > height && orientacion == 2){
                            Toast.makeText(MisDibujos.this, "2. Demasiado grande", Toast.LENGTH_SHORT).show();
                        }else{
                            Cuadrado c = new Cuadrado(colorFigura,baseF);
                            miBundle.putSerializable("figura",c);
                            i.putExtras(miBundle);
                            startActivity(i);
                        }
                    }catch (Exception e){
                        Toast.makeText(MisDibujos.this, "Campos obligatorios ", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }//on create

    class AdaptadorFiguras extends ArrayAdapter<Figura> {

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
            lblNombre.setText("" + figurasArray[position].getTipo()+ " ");

            ImageView imgFigura = (ImageView) item.findViewById(R.id.imagenFigura);
            imgFigura.setImageResource(figurasArray[position].getImagen());

            return item;
        }
    }//Fin AdaptadorFiguras

}
