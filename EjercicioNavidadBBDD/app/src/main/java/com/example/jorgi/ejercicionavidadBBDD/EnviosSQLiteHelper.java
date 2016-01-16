package com.example.jorgi.ejercicionavidadBBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorgi on 16/01/2016.
 */
public class EnviosSQLiteHelper extends SQLiteOpenHelper {

    // C:    cd\Users\Jorgi\AppData\Local\Android\sdk1\platform-tools
    //       cd /data/data/com.example.jorgi.ejercicionavidadBBDD/databases/


    // Table Names
    private static final String TABLA_USUARIOS = "usuarios";
    private static final String TABLA_ENVIOS = "envios";
    private static final String TABLA_PEDIDOS = "pedidos";


    private static final String KEY_CODIGO = "codigo";

    // NOTES usuarios - column names

    private static final String USUARIO_CODIGO = KEY_CODIGO;
    private static final String USUARIO_DNI = "dni";
    private static final String USUARIO_NOMBRE = "nombre";
    private static final String USUARIO_APELLIDO1 = "apellido1";
    private static final String USUARIO_APELLIDO2 = "apellido2";
    private static final String USUARIO_COMAUT = "comAut";
    private static final String USUARIO_PROVINCIA = "provincia";
    private static final String USUARIO_LOCALIDAD = "localidad";
    private static final String USUARIO_DIRECCION = "direccion";
    private static final String USUARIO_EMAIL = "email";


    //Sentencia SQL para crear la tabla de Destinos
    String sqlCreateDestinos = "CREATE TABLE IF NOT EXISTS `destinos` (" +
            "  `"+KEY_CODIGO+"` INTEGER NOT NULL PRIMARY KEY," +
            "  `zona` INTEGER NOT NULL," +
            "  `nombre` TEXT NOT NULL," +
            "  `tarifaDestino` TEXT NOT NULL" +
            "  );";

    //Sentencia SQL para crear la tabla de Pedidos
    String sqlCreatePedidos = "CREATE TABLE IF NOT EXISTS `pedidos` (" +
            "  `"+KEY_CODIGO+"` INTEGER NOT NULL PRIMARY KEY," +
            "  `usuarioDNI` TEXT NOT NULL," +
            "  `zonaId` TEXT NOT NULL," +
            "  `peso` TEXT NOT NULL," +
            "  `tarifaPeso` TEXT NOT NULL" +
            "  );";

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateUsuarios = "CREATE TABLE IF NOT EXISTS `usuarios` (" +
            "  `"+USUARIO_CODIGO+"` INTEGER NOT NULL PRIMARY KEY," +
            "  `"+USUARIO_DNI+"` TEXT NOT NULL," +
            "  `"+USUARIO_NOMBRE+"` TEXT NOT NULL," +
            "  `"+USUARIO_APELLIDO1+"` TEXT NOT NULL," +
            "  `"+USUARIO_APELLIDO2+"` TEXT NOT NULL," +
            "  `"+USUARIO_COMAUT+"` TEXT NOT NULL," +
            "  `"+USUARIO_PROVINCIA+"` TEXT NOT NULL," +
            "  `"+USUARIO_LOCALIDAD+"` TEXT NOT NULL," +
            "  `"+USUARIO_DIRECCION+"` TEXT NOT NULL," +
            "  `"+USUARIO_EMAIL+"` TEXT NOT NULL" +
            "  );";

    public EnviosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateUsuarios);
        db.execSQL(sqlCreateDestinos);
        db.execSQL(sqlCreatePedidos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    /*
    * ==========================================================================
    * ==========================================================================
    *
    *                           CRUD USUARIO
    *
    * ==========================================================================
    * ==========================================================================
    * */

    /**
     *
     * @param usuario
     * @return
     *
     * Crear un usuario en la tabla Usuarios
     *
     */

    public long crearUsuario(Usuario usuario) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USUARIO_CODIGO, usuario.getCodigo());
        values.put(USUARIO_DNI, usuario.getDni());
        values.put(USUARIO_NOMBRE, usuario.getNombre());
        values.put(USUARIO_APELLIDO1, usuario.getApellido1());
        values.put(USUARIO_APELLIDO2, usuario.getApellido2());
        values.put(USUARIO_COMAUT, usuario.getComAut());
        values.put(USUARIO_PROVINCIA, usuario.getProvincia());
        values.put(USUARIO_LOCALIDAD, usuario.getLocalidad());
        values.put(USUARIO_DIRECCION, usuario.getDireccion());
        values.put(USUARIO_EMAIL, usuario.getEmail());

        // insert row
        long usuario_id = db.insert(TABLA_USUARIOS, null, values);

        return usuario_id;
    }

    /**
     *
     * @param usuario_dni
     * @return
     *
     * Obtener los datos de un usuario
     *
     */
    public Usuario getUsuario(long usuario_dni) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLA_USUARIOS + " WHERE "
                + KEY_CODIGO + " = " + usuario_dni;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Usuario td = new Usuario();

        td.setCodigo(c.getInt(c.getColumnIndex(KEY_CODIGO)));
        td.setNombre((c.getString(c.getColumnIndex(USUARIO_NOMBRE))));
        td.setDni(c.getString(c.getColumnIndex(USUARIO_DNI)));
        td.setApellido1(c.getString(c.getColumnIndex(USUARIO_APELLIDO1)));
        td.setApellido2(c.getString(c.getColumnIndex(USUARIO_APELLIDO2)));
        td.setComAut(c.getString(c.getColumnIndex(USUARIO_COMAUT)));
        td.setProvincia(c.getString(c.getColumnIndex(USUARIO_PROVINCIA)));
        td.setLocalidad(c.getString(c.getColumnIndex(USUARIO_LOCALIDAD)));
        td.setDireccion(c.getString(c.getColumnIndex(USUARIO_DIRECCION)));
        td.setEmail(c.getString(c.getColumnIndex(USUARIO_EMAIL)));

        return td;
    }


    /*
     * getting all usuarios
     * */
    public List<Usuario> getTodoUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        String selectQuery = "SELECT  * FROM " + TABLA_USUARIOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Usuario td = new Usuario();
                td.setCodigo(c.getInt(c.getColumnIndex(KEY_CODIGO)));
                td.setNombre((c.getString(c.getColumnIndex(USUARIO_NOMBRE))));
                td.setDni(c.getString(c.getColumnIndex(USUARIO_DNI)));
                td.setApellido1(c.getString(c.getColumnIndex(USUARIO_APELLIDO1)));
                td.setApellido2(c.getString(c.getColumnIndex(USUARIO_APELLIDO2)));
                td.setComAut(c.getString(c.getColumnIndex(USUARIO_COMAUT)));
                td.setProvincia(c.getString(c.getColumnIndex(USUARIO_PROVINCIA)));
                td.setLocalidad(c.getString(c.getColumnIndex(USUARIO_LOCALIDAD)));
                td.setDireccion(c.getString(c.getColumnIndex(USUARIO_DIRECCION)));
                td.setEmail(c.getString(c.getColumnIndex(USUARIO_EMAIL)));

                // adding to Usuario list
                usuarios.add(td);
            } while (c.moveToNext());
        }

        return usuarios;
    }


    /*
     * Updating a Usuario
     */
    public int updateToDo(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USUARIO_CODIGO, usuario.getCodigo());
        values.put(USUARIO_DNI, usuario.getDni());
        values.put(USUARIO_NOMBRE, usuario.getNombre());
        values.put(USUARIO_APELLIDO1, usuario.getApellido1());
        values.put(USUARIO_APELLIDO2, usuario.getApellido2());
        values.put(USUARIO_COMAUT, usuario.getComAut());
        values.put(USUARIO_PROVINCIA, usuario.getProvincia());
        values.put(USUARIO_LOCALIDAD, usuario.getLocalidad());
        values.put(USUARIO_DIRECCION, usuario.getDireccion());
        values.put(USUARIO_EMAIL,usuario.getEmail());

        // updating row
        return db.update(TABLA_USUARIOS, values, USUARIO_DNI + " = ?",
                new String[] { String.valueOf(usuario.getDni()) });
    }

    /*
     * Deleting a Usuario
     */
    public void deleteToDo(long usuario_dni) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLA_USUARIOS, USUARIO_DNI + " = ?",
                new String[] { String.valueOf(usuario_dni) });
    }



    /*
    * ==========================================================================
    * ==========================================================================
    *
    *                           CRUD PEDIDOS
    *
    * ==========================================================================
    * ==========================================================================
    * */

    /*
     * getting all usuarios
     * */
    public List<Pedido> getTodoPedidos() {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        String selectQuery = "SELECT  * FROM " + TABLA_PEDIDOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Pedido td = new Pedido();
                td.setCodigo(c.getInt(c.getColumnIndex(KEY_CODIGO)));
                td.setUsuarioDNI((c.getString(c.getColumnIndex("usuarioDNI"))));
                td.setZonaId(c.getString(c.getColumnIndex("zonaId")));
                td.setPeso(c.getString(c.getColumnIndex("peso")));
                td.setTarifaPeso(c.getString(c.getColumnIndex("tarifaPeso")));

                // adding to Usuario list
                pedidos.add(td);
            } while (c.moveToNext());
        }

        return pedidos;
    }
}
