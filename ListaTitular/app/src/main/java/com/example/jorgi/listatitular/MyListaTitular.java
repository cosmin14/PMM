package com.example.jorgi.listatitular;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyListaTitular extends Activity {

    private Titular titulares[] =
            new Titular[] {
                new Titular("Jose","Garcia Garcia", 25),
                new Titular("Pepito","Fernandez Fernandez", 30),
                new Titular("Juan","Navarro Navarro", 35),
    };

    ListView miLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lista_titular);

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        miLista = (ListView) findViewById(R.id.ListTitular);
        miLista.setAdapter(adaptador);

        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "";
                mensaje = "" + titulares[position].getTitulo() + " " + titulares[position].getSubtitulo() + " tiene " + titulares[position].getEdad() + " años.";
                showToast(mensaje);
            }
        });

    }

    public void showToast(String text){
        Toast.makeText(MyListaTitular.this, text, Toast.LENGTH_SHORT).show();
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
            //Toast.makeText(MyListaTitular.this, titulares[position].getTitulo(), Toast.LENGTH_SHORT).show();

            //TextView lblSubtitulo = (TextView) item.findViewById(R.id.LblSubTitulo);
            //lblSubtitulo.setText(titulares[position].getSubtitulo());
            //Toast.makeText(MyListaTitular.this, titulares[position].getSubtitulo(), Toast.LENGTH_SHORT).show();

            return (item);
        }
    }


}
