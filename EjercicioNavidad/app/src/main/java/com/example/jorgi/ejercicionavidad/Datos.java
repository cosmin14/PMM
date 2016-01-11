package com.example.jorgi.ejercicionavidad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Datos extends AppCompatActivity {

    Spinner spinnerCiudad;
    ArrayAdapter<CharSequence> aa_com_val;
    ArrayAdapter<CharSequence> aa_catalunya;
    ArrayAdapter<CharSequence> aa_andalucia;
    ArrayAdapter<CharSequence> aa_default;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        Spinner spinnerPaises = (Spinner) findViewById(R.id.spinnerPais);
        spinnerPaises.setOnItemSelectedListener(new SpinnerListener());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String zona = extras.getString("precio");
            Toast.makeText(Datos.this, zona+"", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

        spinnerCiudad = (Spinner) findViewById(R.id.spinnerCiudad);
        aa_com_val = ArrayAdapter.createFromResource(this, R.array.arrayComValenciana,android.R.layout.simple_spinner_item);
        aa_catalunya = ArrayAdapter.createFromResource(this, R.array.arrayCatalunya,android.R.layout.simple_spinner_item);
        aa_andalucia = ArrayAdapter.createFromResource(this, R.array.arrayAndalucia, android.R.layout.simple_spinner_item);
        aa_default = ArrayAdapter.createFromResource(this, R.array.arrayDefecto, android.R.layout.simple_spinner_item);

        switch (pais) {
            case 1: spinnerCiudad.setAdapter(aa_com_val);
                break;
            case 2: spinnerCiudad.setAdapter(aa_catalunya);
                break;
            case 3: spinnerCiudad.setAdapter(aa_andalucia);
                break;
            default: spinnerCiudad.setAdapter(aa_default);
                break;
        }
    }
}
