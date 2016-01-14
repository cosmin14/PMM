package com.example.jorgi.ejercicionavidadBBDD;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class datos extends ActionBarActivity {

    Spinner spinnerComAut, spinnerProvincia;
    ArrayAdapter<CharSequence> aa_com_val;
    ArrayAdapter<CharSequence> aa_catalunya;
    ArrayAdapter<CharSequence> aa_andalucia;
    ArrayAdapter<CharSequence> aa_default;
    Button btnEnviar;
    String nombre, apellido1, apellido2, comAut, provincia, localidad, direccion, email, dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        spinnerComAut = (Spinner) findViewById(R.id.spinnerComAut);
        spinnerComAut.setOnItemSelectedListener(new SpinnerListener());

        final Bundle extras = getIntent().getExtras(); // Recogo el bundle de la actividad anterior

        btnEnviar = (Button)findViewById(R.id.buttonEnviarDatos);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editNombre = (EditText)findViewById(R.id.editTextNombre);
                    nombre = editNombre.getText().toString();
                EditText editApellido1 = (EditText)findViewById(R.id.editTextApellido1);
                    apellido1 = editApellido1.getText().toString();
                EditText editApellido2 = (EditText)findViewById(R.id.editTextApellido2);
                    apellido2 = editApellido2.getText().toString();
                EditText editLocalidad = (EditText)findViewById(R.id.editTextLocalidad);
                    localidad = editLocalidad.getText().toString();
                EditText editDireccion = (EditText)findViewById(R.id.editTextDireccion);
                    direccion = editDireccion.getText().toString();
                EditText editEmail = (EditText)findViewById(R.id.editTextEmail);
                    email = editEmail.getText().toString();
                EditText editDNI = (EditText)findViewById(R.id.editTextDNI);
                    dni = editDNI.getText().toString();

                comAut = spinnerComAut.getSelectedItem().toString();


                Intent i = new Intent(getApplicationContext(), resumen.class);
                Bundle miBundle = new Bundle();
                miBundle.putString("nombre", nombre+"");
                miBundle.putString("apellido1", apellido1+"");
                miBundle.putString("apellido2", apellido2+"");
                miBundle.putString("comAut", comAut+"");
                miBundle.putString("provincia", provincia+"");
                miBundle.putString("localidad", localidad+"");
                miBundle.putString("direccion", direccion+"");
                miBundle.putString("email", email+"");
                miBundle.putString("dni", dni+"");
                i.putExtras(miBundle); // Guardo los datos en el bundle
                i.putExtras(extras);  // Guardo el bundle recogido anteriormente este nuevo bundle creado
                startActivity(i);
            }
        });



    }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intentMain = new Intent(datos.this ,
                    about.class);
            startActivity(intentMain);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public class SpinnerListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            cargaSpinnerCiudad(parent.getSelectedItemPosition());
        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }

    private void cargaSpinnerCiudad(int pais){

        spinnerProvincia = (Spinner) findViewById(R.id.spinnerProvincia);
        aa_com_val = ArrayAdapter.createFromResource(this, R.array.arrayComValenciana,android.R.layout.simple_spinner_dropdown_item);
        aa_catalunya = ArrayAdapter.createFromResource(this, R.array.arrayCatalunya,android.R.layout.simple_spinner_dropdown_item);
        aa_andalucia = ArrayAdapter.createFromResource(this, R.array.arrayAndalucia, android.R.layout.simple_spinner_dropdown_item);
        aa_default = ArrayAdapter.createFromResource(this, R.array.arrayDefecto, android.R.layout.simple_spinner_dropdown_item);

        switch (pais) {
            case 1: spinnerProvincia.setAdapter(aa_com_val);
                break;
            case 2: spinnerProvincia.setAdapter(aa_catalunya);
                break;
            case 3: spinnerProvincia.setAdapter(aa_andalucia);
                break;
            default: spinnerProvincia.setAdapter(aa_default);
                break;
        }

        spinnerProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provincia = spinnerProvincia.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
