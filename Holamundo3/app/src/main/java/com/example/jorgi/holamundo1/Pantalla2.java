package com.example.jorgi.holamundo1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Pantalla2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla2);

        final TextView otroSaludo = (TextView)findViewById(R.id.textoSaludo);
        final Button btnVolver = (Button)findViewById(R.id.volver);

        final Bundle miBundleRecoger = getIntent().getExtras();
        otroSaludo.setText("TE SALUDO " + miBundleRecoger.getString("SALUDO"));

        btnVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String saludoDevuelto = miBundleRecoger.getString("SALUDO");
                Intent i = getIntent();
                i.putExtra("DEVUELTO", saludoDevuelto);
                setResult(RESULT_OK, i);
                finish();
            } });
    }
}
