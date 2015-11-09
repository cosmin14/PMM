package com.example.jorgi.ejerciciocalculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView cant1, cant2, textResultado;
    Button sumar,restar, multi, div;
    RadioGroup radioGroupOperaciones;
    RadioButton radioButtonSumar, radioButtonRestar, radioButtonMulti, radioButtonDiv;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cant1 = (TextView)findViewById(R.id.editText1);
        cant2 = (TextView)findViewById(R.id.editText2);
        textResultado = (TextView)findViewById(R.id.textViewResultado);




        /* Button */

        sumar = (Button)findViewById(R.id.buttonSumar);
        restar = (Button)findViewById(R.id.buttonRestar);
        multi = (Button)findViewById(R.id.buttonMult);
        div = (Button)findViewById(R.id.buttonDiv);

        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = Integer.parseInt(cant1.getText().toString()) + Integer.parseInt(cant2.getText().toString());
                String mensaje = "";
                mensaje = ""+total;
                textResultado.setText(mensaje);
                radioGroupOperaciones.clearCheck();
            }
        });

        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = Integer.parseInt(cant1.getText().toString()) - Integer.parseInt(cant2.getText().toString());
                String mensaje = "";
                mensaje = ""+total;
                textResultado.setText(mensaje);
                radioGroupOperaciones.clearCheck();
            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = Integer.parseInt(cant1.getText().toString()) * Integer.parseInt(cant2.getText().toString());
                String mensaje = "";
                mensaje = ""+total;
                textResultado.setText(mensaje);
                radioGroupOperaciones.clearCheck();
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = Integer.parseInt(cant1.getText().toString()) / Integer.parseInt(cant2.getText().toString());
                String mensaje = "";
                mensaje = ""+total;
                textResultado.setText(mensaje);
                radioGroupOperaciones.clearCheck();
            }
        });



        /* RadioButtons */

        radioGroupOperaciones = (RadioGroup)findViewById(R.id.radioGroupOperaciones);
        radioButtonSumar = (RadioButton)findViewById(R.id.radioButtonSumar);
        radioButtonRestar = (RadioButton)findViewById(R.id.radioButtonRestar);
        radioButtonMulti = (RadioButton)findViewById(R.id.radioButtonMult);
        radioButtonDiv = (RadioButton)findViewById(R.id.radioButtonDiv);

        radioGroupOperaciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (radioGroupOperaciones.getCheckedRadioButtonId() == R.id.radioButtonSumar){
                    total = Integer.parseInt(cant1.getText().toString()) + Integer.parseInt(cant2.getText().toString());
                    String mensaje = "";
                    mensaje = ""+total;
                    textResultado.setText(mensaje);
                }
                if (radioGroupOperaciones.getCheckedRadioButtonId() == R.id.radioButtonRestar){
                    total = Integer.parseInt(cant1.getText().toString()) - Integer.parseInt(cant2.getText().toString());
                    String mensaje = "";
                    mensaje = ""+total;
                    textResultado.setText(mensaje);
                }
                if (radioGroupOperaciones.getCheckedRadioButtonId() == R.id.radioButtonMult){
                    total = Integer.parseInt(cant1.getText().toString()) * Integer.parseInt(cant2.getText().toString());
                    String mensaje = "";
                    mensaje = ""+total;
                    textResultado.setText(mensaje);
                }
                if (radioGroupOperaciones.getCheckedRadioButtonId() == R.id.radioButtonDiv){
                    total = Integer.parseInt(cant1.getText().toString()) / Integer.parseInt(cant2.getText().toString());
                    String mensaje = "";
                    mensaje = ""+total;
                    textResultado.setText(mensaje);
                }
            }
        });



    }
}
