package com.example.jorgi.ejercicionavidadBBDDmaps.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jorgi.ejercicionavidadBBDDmaps.R;
import com.example.jorgi.ejercicionavidadBBDDmaps.clases.EnviosSQLiteHelper;
import com.example.jorgi.ejercicionavidadBBDDmaps.clases.Usuario;

public class loginActivity extends ActionBarActivity {

    TextInputLayout TILuser, TILpass;
    Button btnLogin, btnRegistro;
    EditText editUsuario, editPass;
    String email = "usuario", pass="pass1";
    Usuario usuario;
    EnviosSQLiteHelper enviosHelper;
    Drawable originalDrawable;
    ProgressDialog dialog;
    public static int estadoSesion = 0; //0 no logueado - 1 logeado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TILuser = (TextInputLayout)findViewById(R.id.TILloginEmail);
        TILpass = (TextInputLayout)findViewById(R.id.TILloginPass);
        editUsuario = (EditText)findViewById(R.id.userLogin);
        editPass = (EditText)findViewById(R.id.passLogin);
        btnRegistro = (Button)findViewById(R.id.buttonRegistro);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        originalDrawable = editUsuario.getBackground();
        enviosHelper = new EnviosSQLiteHelper(this, "DBEnvios", null, 1);

        //Recuperamos las preferencias
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // Coge los valores i si no existen les pone de valor vacio
        String sharedCorreo = prefs.getString("email", "vacio");
        String sharedPass = prefs.getString("pass1", "vacio");

        usuario = enviosHelper.getUsuarioByEmail(sharedCorreo);

        if (usuario.validateLogin(sharedCorreo,sharedPass,usuario.getEmail(),usuario.getPassword())){
            Intent i = new Intent(getApplicationContext(), pedidoActivity.class);
            Bundle miBundle = new Bundle();
            miBundle.putSerializable("usuario", usuario);
            i.putExtras(miBundle);
            startActivity(i);
            finish();
        }

        final View.OnFocusChangeListener existing1 = editUsuario.getOnFocusChangeListener();
        editUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    email = editUsuario.getText().toString();
                    validarVacio(TILuser,editUsuario,email,"Correo");
                }
            }
        });

        final View.OnFocusChangeListener existing2 = editPass.getOnFocusChangeListener();
        editPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    pass = editPass.getText().toString();
                    validarVacio(TILpass,editPass, pass, "Contraseña");
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = editUsuario.getText().toString();
                pass = editPass.getText().toString();
                usuario = enviosHelper.getUsuarioByEmail(email);

                if (usuario.validateLogin(email, pass, usuario.getEmail(), usuario.getPassword())) {
                    //Guardamos las preferencias
                    SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", "" + usuario.getEmail());
                    editor.putString("pass1", "" + usuario.getPassword());
                    editor.commit();

                    dialog = ProgressDialog.show(loginActivity.this, "","INICIANDO SESIÓN.\n Espere porfavor...", true);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 10 seconds
                            Toast.makeText(loginActivity.this, "Bienvenido " + usuario.getNombre() + " " + usuario.getApellido1(), Toast.LENGTH_LONG).show();

                            Intent i = new Intent(getApplicationContext(), pedidoActivity.class);
                            Bundle miBundle = new Bundle();
                            miBundle.putSerializable("usuario", usuario);
                            i.putExtras(miBundle);
                            startActivity(i);
                            finish();
                        }
                    }, 2000);



                } else {
                    Toast.makeText(loginActivity.this, "USUARIO ERRONEO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), registroActivity.class);
                startActivity(i);
            }
        });

    }// onCreate

    public boolean validarVacio(TextInputLayout textInputLayout, EditText editText, String campo, String nombreCampo){
        if (campo.length() == 0){
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(nombreCampo + " no puede estar vacio");
            editText.setBackgroundDrawable( getResources().getDrawable(R.drawable.bordercolorerror) );
            return false;
        }else{
            textInputLayout.setErrorEnabled(false);
            editText.setBackgroundDrawable( originalDrawable );
            return true;
        }
    }

}
