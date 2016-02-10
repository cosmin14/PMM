package com.example.jorgi.ejercicionavidadBBDDmaps.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgi.ejercicionavidadBBDDmaps.clases.EnviosSQLiteHelper;
import com.example.jorgi.ejercicionavidadBBDDmaps.R;
import com.example.jorgi.ejercicionavidadBBDDmaps.clases.Usuario;

import java.util.List;

public class adminActivity extends AppCompatActivity {

    EnviosSQLiteHelper enviosHelper;
    private List<Usuario> usuarios;
    Spinner spinnerUsuarios;
    Button btnNuevo, btnEditar, btnBorrar;
    int idUsuario;
    String dniUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        /* SPINNER */

        enviosHelper = new EnviosSQLiteHelper(this, "DBEnvios", null, 1);
        usuarios = enviosHelper.getAllUsuarios();
        spinnerUsuarios = (Spinner)findViewById(R.id.spinnerUsuarios);
        AdaptadorUsuarios au = new AdaptadorUsuarios(this);
        spinnerUsuarios.setAdapter(au);

        spinnerUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idUsuario = position+1;
                Usuario u = (Usuario)spinnerUsuarios.getSelectedItem();
                dniUsuario = u.getDni();
                Toast.makeText(adminActivity.this, "DNI: "+u.getDni(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnNuevo = (Button)findViewById(R.id.buttonNuevoUsuario);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(adminActivity.this, registroActivity.class);
                startActivity(i);
            }
        });

        btnEditar = (Button)findViewById(R.id.buttonEditarUsuario);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u = enviosHelper.getUsuarioByDni(dniUsuario);
                Intent i = new Intent(adminActivity.this,registroActivity.class);
                Bundle myBundle = new Bundle();
                myBundle.putSerializable("usuario",u);
                i.putExtras(myBundle);
                startActivity(i);
            }
        });

        btnBorrar = (Button)findViewById(R.id.buttonBorrarUsuario);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(adminActivity.this);
                alert.setTitle("Atencion!");
                alert.setMessage("Seguro que quiere borrar este usuario?");
                alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        enviosHelper.deleteUsuario(dniUsuario);
                        Intent i = new Intent(adminActivity.this, adminActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                alert.setNegativeButton("No", null);
                alert.show();
            }
        });

    }




    class AdaptadorUsuarios extends ArrayAdapter<Usuario> {

        public Activity context;

        public AdaptadorUsuarios(Activity context) {
            super(context, R.layout.usuarios_view, usuarios);
            this.context = context;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vistaDesplegada = getView(position,convertView,parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.usuarios_view, null);

            TextView lblId = (TextView) item.findViewById(R.id.usuario);

            lblId.setText(usuarios.get(position).getCodigo()+". "+usuarios.get(position).getNombre()+" - " + usuarios.get(position).getDni());

            return (item);
        }
    }
}
