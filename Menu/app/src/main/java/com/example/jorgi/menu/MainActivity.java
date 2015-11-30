package com.example.jorgi.menu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        TextView lblTxt = (TextView)findViewById(R.id.texto);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Menu opciones seleccionado!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.MenuOpc1){
            Toast.makeText(MainActivity.this, "Menu opcion 1 seleccionado!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.MenuOpc2){
            Toast.makeText(MainActivity.this, "Menu opcion 2 seleccionado!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.MenuOpc3){
            Toast.makeText(MainActivity.this, "Menu opcion 3 seleccionado!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.MenuOpc31){
            Toast.makeText(MainActivity.this, "Menu opcion 3.1 seleccionado!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.MenuOpc32){
            Toast.makeText(MainActivity.this, "Menu opcion 3.2 seleccionado!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
