package com.example.jorgi.ejercicionavidadBBDDmaps.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jorgi.ejercicionavidadBBDDmaps.clases.EnviosSQLiteHelper;
import com.example.jorgi.ejercicionavidadBBDDmaps.R;
import com.example.jorgi.ejercicionavidadBBDDmaps.clases.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registroActivity extends ActionBarActivity {

    TextInputLayout TILnombre, TILapellido1, TILapellido2, TILlocalidad, TILdireccion, TILemail, TILdni, TILpass1, TILpass2;
    EditText editNombre, editApellido1, editApellido2, editLocalidad, editDireccion, editEmail, editDNI, editPass1, editPass2;
    Spinner spinnerComAut, spinnerProvincia;
    ArrayAdapter<CharSequence> aa_com_val, aa_catalunya, aa_andalucia, aa_default;
    Button btnEnviar;
    String nombre, apellido1, apellido2, comAut, provincia, localidad, direccion, email, dni, pass1, pass2;
    RelativeLayout rel1, rel2;
    boolean correcto=true;
    // Esta variable la utilizo para almacenar el estilo del imput
    // Asi cuando le cambio el estilo puedo volver al anterior alamacenado en esta.
    Drawable originalDrawable;
    Usuario usuario;
    String idUsuario;
    Bundle myBundle = null;
    boolean editarUsuario = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final EnviosSQLiteHelper enviosHelper = new EnviosSQLiteHelper(this, "DBEnvios", null, 1);
        SQLiteDatabase dbEnvios = enviosHelper.getWritableDatabase();

        btnEnviar = (Button)findViewById(R.id.buttonEnviarDatos);
        TILnombre = (TextInputLayout)findViewById(R.id.TILnombre);
        TILapellido1 = (TextInputLayout)findViewById(R.id.TILapellido1);
        TILapellido2 = (TextInputLayout)findViewById(R.id.TILapellido2);
        TILlocalidad = (TextInputLayout)findViewById(R.id.TILlocalidad);
        TILdireccion = (TextInputLayout)findViewById(R.id.TILdireccion);
        TILemail = (TextInputLayout)findViewById(R.id.TILemail);
        TILdni = (TextInputLayout)findViewById(R.id.TILdni);
        TILpass1 = (TextInputLayout)findViewById(R.id.TILpass1);
        TILpass2 = (TextInputLayout)findViewById(R.id.TILpass2);

        // Cojo los relativeLayout para poder editar el estilo que envuelve a los spinner
        rel1 = (RelativeLayout)findViewById(R.id.relSpinnerComAut);
        rel2 = (RelativeLayout)findViewById(R.id.relSpinnerProv);


        /*============================================================================
        *                      RECOJO LOS EDITTEXT
        * ============================================================================*/
        editNombre = (EditText)findViewById(R.id.editTextNombre);
        editApellido1 = (EditText)findViewById(R.id.editTextApellido1);
        editApellido2 = (EditText)findViewById(R.id.editTextApellido2);
            spinnerComAut = (Spinner) findViewById(R.id.spinnerComAut);
            spinnerComAut.setFocusable(true);
            spinnerComAut.setFocusableInTouchMode(true);
            spinnerComAut.setOnItemSelectedListener(new SpinnerListener());
            spinnerComAut.setFocusable(true);
            spinnerComAut.setFocusableInTouchMode(true);
        editLocalidad = (EditText)findViewById(R.id.editTextLocalidad);
        editDireccion = (EditText)findViewById(R.id.editTextDireccion);
        editEmail = (EditText)findViewById(R.id.editTextEmail);
        editDNI = (EditText)findViewById(R.id.editTextDNI);
        editPass1 = (EditText)findViewById(R.id.editTextPass);
        editPass2 = (EditText)findViewById(R.id.editTextPass2);



        originalDrawable = editNombre.getBackground();

        myBundle = getIntent().getExtras();

        if (myBundle != null){
            btnEnviar.setText("Guardar");
            usuario = (Usuario)myBundle.getSerializable("usuario");
            idUsuario = usuario.getCodigo();
            editNombre.setText(usuario.getNombre());
            editApellido1.setText(usuario.getApellido1());
            editApellido2.setText(usuario.getApellido2());
            editLocalidad.setText(usuario.getLocalidad());
            editDireccion.setText(usuario.getDireccion());
            editEmail.setText(usuario.getEmail());
            editDNI.setText(usuario.getDni());
            editPass1.setText(usuario.getPassword());
            editPass2.setText(usuario.getPassword());

            String myString = usuario.getComAut(); //the value you want the position for
            ArrayAdapter myAdap = (ArrayAdapter) spinnerComAut.getAdapter(); //cast to an ArrayAdapter
            int spinnerPosition = myAdap.getPosition(myString);
            spinnerComAut.setSelection(spinnerPosition);

            rel2.setBackgroundDrawable(getResources().getDrawable(R.drawable.bordercolorerror));

            editarUsuario = true;

        }


        /*==========================================================================================

                                            BOTON SUBMIT

        ==========================================================================================*/

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*============================================================================
                *                      RECOJO LOS VALORES DE LOS EDITTEXT
                * ============================================================================*/
                nombre = editNombre.getText().toString();
                apellido1 = editApellido1.getText().toString();
                apellido2 = editApellido2.getText().toString();
                localidad = editLocalidad.getText().toString();
                direccion = editDireccion.getText().toString();
                email = editEmail.getText().toString();
                dni = editDNI.getText().toString();
                pass1 = editPass1.getText().toString();
                pass2 = editPass2.getText().toString();
                comAut = spinnerComAut.getSelectedItem().toString();

                validarVacio(TILnombre,editNombre,nombre, "Nombre"); validarVacio(TILapellido1,editApellido1, apellido1, "Apellido 1");
                validarVacio(TILapellido2,editApellido2,apellido2, "Apellido 2");validarVacio(TILlocalidad,editLocalidad,localidad, "Localidad");
                validarVacio(TILdireccion,editDireccion,direccion, "Direccion"); validarVacio(TILemail,editEmail,email, "Email");
                validarVacio(TILdni,editDNI,dni, "DNI"); validarVacio(TILpass1,editPass1, pass1, "Contraseña"); validarVacio(TILpass2, editPass2, pass2, "Repeticion contraseña");

                validarSpinner();

                Toast.makeText(registroActivity.this, ""+correcto, Toast.LENGTH_SHORT).show();
                long user = 0;
                if (correcto == true){
                    if (editarUsuario == true){
                        usuario = new Usuario(idUsuario, dni,email,direccion,provincia,localidad,apellido1,apellido2,comAut,nombre, pass1);
                        enviosHelper.updateUsuario(usuario);
                        Intent i = new Intent(getApplicationContext(), pedidoActivity.class);
                        Bundle miBundle = new Bundle();
                        miBundle.putSerializable("usuario", usuario);
                        i.putExtras(miBundle);
                        startActivity(i);
                    }else{
                        usuario = new Usuario(dni,email,direccion,provincia,localidad,apellido1,apellido2,comAut,nombre, pass1);
                        user = enviosHelper.crearUsuario(usuario); // Devuelvo el id del usuario.
                    }

                    // Si devuelve algun id mayor que 0 cambio de actividad.
                    if (user > 0){
                        /*SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("email", "" + usuario.getEmail());
                        editor.putString("pass1", "" + usuario.getPassword());
                        editor.commit();*/
                        Intent i = new Intent(getApplicationContext(), pedidoActivity.class);
                        Bundle miBundle = new Bundle();
                        miBundle.putSerializable("usuario", usuario);
                        i.putExtras(miBundle);
                        startActivity(i);
                    }
                }else{

                }
            }
        });// evento boton


        /*==========================================================================================

                                            EVENTOS ANIMADOS EDITTEXT

        ==========================================================================================*/


        final View.OnFocusChangeListener existing1 = editNombre.getOnFocusChangeListener();
        editNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing1.onFocusChange(v, hasFocus);
                if (!hasFocus) {
                    nombre = editNombre.getText().toString();
                    //String cap = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
                    /*String cap = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
                    editNombre.setText(cap);*/
                    validarVacio(TILnombre, editNombre, nombre, "Nombre");
                }
            }
        });

        final View.OnFocusChangeListener existing2 = editApellido1.getOnFocusChangeListener();
        editApellido1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing2.onFocusChange(v, hasFocus);
                if (!hasFocus) {
                    apellido1 = editApellido1.getText().toString();
                    String cap = apellido1.substring(0, 1).toUpperCase() + apellido1.substring(1);
                    editApellido1.setText(cap);
                    validarVacio(TILapellido1, editApellido1, apellido1, "1r Apellido");
                }
            }
        });

        final View.OnFocusChangeListener existing3 = editApellido2.getOnFocusChangeListener();
        editApellido2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing3.onFocusChange(v, hasFocus);
                if (!hasFocus) {
                    apellido2 = editApellido2.getText().toString();
                    String cap = apellido2.substring(0, 1).toUpperCase() + apellido2.substring(1);
                    editApellido2.setText(cap);
                    validarVacio(TILapellido2, editApellido2, apellido2, "2o Apellido");
                }
            }
        });

        final View.OnFocusChangeListener existing4 = editLocalidad.getOnFocusChangeListener();
        editLocalidad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing4.onFocusChange(v, hasFocus);
                if (!hasFocus) {
                    localidad = editLocalidad.getText().toString();
                    String cap = localidad.substring(0, 1).toUpperCase() + localidad.substring(1);
                    editLocalidad.setText(cap);
                    validarVacio(TILlocalidad, editLocalidad, localidad, "Localidad");
                }
            }
        });

        final View.OnFocusChangeListener existing5 = editDireccion.getOnFocusChangeListener();
        editDireccion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing5.onFocusChange(v, hasFocus);
                if (!hasFocus) {
                    direccion = editDireccion.getText().toString();
                    String cap = direccion.substring(0, 1).toUpperCase() + direccion.substring(1);
                    editDireccion.setText(cap);
                    validarVacio(TILdireccion, editDireccion, direccion, "Direccion");
                }
            }
        });

        final View.OnFocusChangeListener existing6 = editEmail.getOnFocusChangeListener();
        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing6.onFocusChange(v, hasFocus);
                if (!hasFocus) {
                    email = editEmail.getText().toString();
                    validarVacio(TILemail, editEmail, email, "Email");
                }
            }
        });

        final View.OnFocusChangeListener existing10 = editDNI.getOnFocusChangeListener();
        editDNI.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing10.onFocusChange(v, hasFocus);
                if (!hasFocus) {
                    dni = editDNI.getText().toString();
                    String cap = dni.substring(1);
                    editDNI.setText(cap);
                    validarVacio(TILdni, editDNI, dni, "DNI");
                    isNifNie(TILdni, editDNI, dni, "DNI");
                }
            }
        });

        final View.OnFocusChangeListener existing7 = editPass1.getOnFocusChangeListener();
        editPass1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing7.onFocusChange(v, hasFocus);
                if (!hasFocus) {
                    pass1 = editPass1.getText().toString();
                    validarVacio(TILpass1, editPass1, pass1, "Contraseña");
                }
            }
        });

        final View.OnFocusChangeListener existing8 = editPass2.getOnFocusChangeListener();
        editPass2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing8.onFocusChange(v, hasFocus);
                if (!hasFocus) {
                    pass2 = editPass2.getText().toString();
                    validarVacio(TILpass2, editPass2, pass2, "Repetir contraseña");
                }
            }
        });

        final View.OnFocusChangeListener existing9 = spinnerComAut.getOnFocusChangeListener();
        spinnerComAut.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //existing9.onFocusChange(v,hasFocus);
                if (!hasFocus){
                    validarSpinner();
                }

            }
        });

    } // onCreate


    /*==========================================================================================

                                            VALIDACIONES

     ==========================================================================================*/



    public boolean validarSpinner(){
        if(spinnerComAut.getSelectedItem().toString().equals("Sel. Com. Aut.")){
            rel1.setBackgroundDrawable( getResources().getDrawable(R.drawable.bordercolorerror) );
            rel2.setBackgroundDrawable( getResources().getDrawable(R.drawable.bordercolorerror) );
            correcto = false;
            return false;
        }else{
            rel1.setBackgroundDrawable( getResources().getDrawable(R.drawable.bordercolorspinner) );
            rel2.setBackgroundDrawable( getResources().getDrawable(R.drawable.bordercolorspinner) );
            return true;
        }
    }

    public boolean validarVacio(TextInputLayout textInputLayout,EditText editText, String campo, String nombreCampo){
        if (campo.length() == 0){
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(nombreCampo + " no puede estar vacio");
            editText.setBackgroundDrawable( getResources().getDrawable(R.drawable.bordercolorerror) );
            correcto = false;
            return false;
        }else{
            textInputLayout.setErrorEnabled(false);
            editText.setBackgroundDrawable( originalDrawable );
            //textInputLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.bordercolor));
            //editText.setBackgroundDrawable( getResources().getDrawable(R.drawable.bordercolor) );
            return true;
        }
    }

    public boolean isNifNie(TextInputLayout textInputLayout,EditText editText, String nif, String nombreCampo){
        //si es NIE, eliminar la x,y,z inicial para tratarlo como nif
        if (nif.toUpperCase().startsWith("X")||nif.toUpperCase().startsWith("Y")||nif.toUpperCase().startsWith("Z"))
            nif = nif.substring(1);

        Pattern nifPattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher m = nifPattern.matcher(nif);
        if(m.matches()){
            String letra = m.group(2);
            //Extraer letra del NIF
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int dni = Integer.parseInt(m.group(1));
            dni = dni % 23;
            String reference = letras.substring(dni,dni+1);

            if (reference.equalsIgnoreCase(letra)){
                // Es nif
                textInputLayout.setErrorEnabled(false);
                editText.setBackgroundDrawable( originalDrawable );
                correcto = true;
                return true;
            }else{
                //no es nif
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(nombreCampo + " no valido");
                editText.setBackgroundDrawable( getResources().getDrawable(R.drawable.bordercolorerror) );
                correcto = false;
                return false;
            }
        }
        else{
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Formato no valido. (123456789A o X123456789A)");
            editText.setBackgroundDrawable( getResources().getDrawable(R.drawable.bordercolorerror) );
            return false;
        }
    }


    /*==========================================================================================

                                            MENUS

     ==========================================================================================*/

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
            Intent intentMain = new Intent(registroActivity.this ,
                    aboutActivity.class);
            startActivity(intentMain);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /*==========================================================================================

                                            SPINNER ADAPTER

     ==========================================================================================*/


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
