package com.example.jorgi.listatitular;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyListaTitular extends AppCompatActivity {

    static Titular titulares[] = new Titular[] {
            new Titular("Titular 1","Subtitulo 1"),
            new Titular("Titular 2","Subtitulo2"),
            new Titular("Titular 3","Subtitulo3"),
    };

    ListView miLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lista_titular);

        String mensaje;
        miLista = (ListView) findViewById(R.id.ListTitular);

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        miLista.setAdapter(adaptador);


        miLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "";
                mensaje = "Item clicked => " + titulares[position];
                Toast.makeText(MyListaTitular.this, mensaje, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    class AdaptadorTitulares extends ArrayAdapter {

        Activity context;

        AdaptadorTitulares(Activity context) {
            super(context, R.layout.listitem_titular, titulares);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_titular, null);
            TextView lblTitulo = (TextView) item.findViewById(R.id.LblTitulo);
            lblTitulo.setText(titulares[position].getTitulo());
            TextView lblSubtitulo = (TextView) item.findViewById(R.id.LblSubTitulo);
            lblSubtitulo.setText(titulares[position].getSubtitulo());
            return (item);
        }
    }


}
