package com.example.jorgi.ejercicionavidadBBDD;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends ActionBarActivity {

    Button btnLogin, btnRegistro;
    TextView textViewUsuario, textViewPass;
    String email = "usuario", pass="pass";
    Usuario usuario;
    EnviosSQLiteHelper enviosHelper;
    public static boolean logged = false;
    public static int estadoSesion = 0; //0 no logueado - 1 logeado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        textViewUsuario = (TextView)findViewById(R.id.userLogin);
        textViewPass = (TextView)findViewById(R.id.passLogin);
        btnRegistro = (Button)findViewById(R.id.buttonRegistro);

        enviosHelper = new EnviosSQLiteHelper(this, "DBEnvios", null, 1);

        //Recuperamos las preferencias
        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String sharedCorreo = prefs.getString("email", "vacio");
        String sharedPass = prefs.getString("pass", "vacio");

        Log.d(">>>>>> RESULTADO", ">>>>>>> " + sharedCorreo + " - " + sharedPass);

        usuario = enviosHelper.getUsuarioLogin(sharedCorreo);

        if (usuario.validateLogin(sharedCorreo,sharedPass,usuario.getEmail(),usuario.getPassword())){

            Toast.makeText(loginActivity.this, "Ya logeado "+usuario.getNombre() + " " + usuario.getApellido1(), Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), pedidoActivity.class);
            Bundle miBundle = new Bundle();
            miBundle.putSerializable("usuario", usuario);
            i.putExtras(miBundle);
            startActivity(i);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = textViewUsuario.getText().toString();
                pass = textViewPass.getText().toString();
                usuario = enviosHelper.getUsuarioLogin(email);

                if (usuario.validateLogin(email, pass, usuario.getEmail(), usuario.getPassword())) {
                    //Guardamos las preferencias
                    SharedPreferences prefs =
                            getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", "" + usuario.getEmail());
                    editor.putString("pass", "" + usuario.getPassword());
                    editor.commit();

                    logged = true;

                    Toast.makeText(loginActivity.this, "Login details are saved..", Toast.LENGTH_SHORT).show();
                    Toast.makeText(loginActivity.this, "Bienvenido " + usuario.getNombre() + " " + usuario.getApellido1(), Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), pedidoActivity.class);
                    Bundle miBundle = new Bundle();
                    miBundle.putSerializable("usuario", usuario);
                    i.putExtras(miBundle);
                    startActivity(i);
                } else {
                    Toast.makeText(loginActivity.this, "USUARIO ERRONEO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), usuarioActivity.class);
                startActivity(i);
            }
        });

    }// onCreate


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Toast.makeText(loginActivity.this, "ATRAS", Toast.LENGTH_SHORT).show();

        /*Intent intent = new Intent(login.this, Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);*/

        loginActivity.this.finish();
        System.exit(0);
    }

}
