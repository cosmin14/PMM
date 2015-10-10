package com.example.jorgi.holamundo1;

import android.app.Activity;
import android.content.Intent;
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
        setContentView(R.layout.activity_pantalla2);

        final TextView otroSaludo= (TextView)findViewById(R.id.textoSaludo);
        Bundle miBundleRecoger = getIntent().getExtras();
        otroSaludo.setText(miBundleRecoger.getString("TEXTO") + miBundleRecoger.getString("EDAD"));

    }
}
