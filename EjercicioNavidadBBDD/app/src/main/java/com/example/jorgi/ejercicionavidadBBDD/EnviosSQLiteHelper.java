package com.example.jorgi.ejercicionavidadBBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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
    private static final String TABLA_DESTINOS = "destinos";
    private static final String TABLA_PEDIDOS = "pedidos";


    private static final String KEY_CODIGO = "codigo";

    // usuarios - columnas

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
    private static final String USUARIO_PASS = "pass";

    private static final String[] COLUMNAS_USUARIO = {USUARIO_CODIGO,USUARIO_DNI,USUARIO_NOMBRE,USUARIO_APELLIDO1,USUARIO_APELLIDO2,USUARIO_COMAUT,USUARIO_PROVINCIA,USUARIO_LOCALIDAD,USUARIO_DIRECCION,USUARIO_EMAIL};


    // pedidos - columnas

    private static final String PEDIDO_CODIGO = KEY_CODIGO;
    private static final String PEDIDO_USUARIODNI = "usuarioDNI";
    private static final String PEDIDO_ZONAID = "zonaId";
    private static final String PEDIDO_PESO = "peso";
    private static final String PEDIDO_TARIFAPESO = "tarifaPeso";


    // destinos - columnas
    // int id, String nombre, String zona, int precio, int imagen

    private static final String DESTINO_CODIGO = KEY_CODIGO;
    private static final String DESTINO_NOMBRE = "nombre";
    private static final String DESTINO_ZONA = "zona";
    private static final String DESTINO_PRECIO = "precio";
    private static final String DESTINO_IMAGEN = "imagen";



    //Sentencia SQL para crear la tabla de Destinos
    String sqlCreateDestinos = "CREATE TABLE IF NOT EXISTS `destinos` (" +
            "  `"+KEY_CODIGO+"` INTEGER NOT NULL PRIMARY KEY," +
            "  `zona` INTEGER NOT NULL," +
            "  `nombre` TEXT NOT NULL," +
            "  `precio` INTEGER NOT NULL," +
            "  `imagen` INTEGER NOT NULL" +
            "  );";

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateUsuarios = "CREATE TABLE `usuarios` (" +
            "  `"+KEY_CODIGO+"` INTEGER NOT NULL PRIMARY KEY," +
            "  `"+USUARIO_DNI+"` TEXT NOT NULL," +
            "  `"+USUARIO_NOMBRE+"` TEXT NOT NULL," +
            "  `"+USUARIO_APELLIDO1+"` TEXT NOT NULL," +
            "  `"+USUARIO_APELLIDO2+"` TEXT NOT NULL," +
            "  `"+USUARIO_COMAUT+"` TEXT NOT NULL," +
            "  `"+USUARIO_PROVINCIA+"` TEXT NOT NULL," +
            "  `"+USUARIO_LOCALIDAD+"` TEXT NOT NULL," +
            "  `"+USUARIO_DIRECCION+"` TEXT NOT NULL," +
            "  `"+USUARIO_EMAIL+"` TEXT NOT NULL," +
            "  `"+USUARIO_PASS+"` TEXT NOT NULL" +
            "  );";

    //Sentencia SQL para crear la tabla de Pedidos
    String sqlCreatePedidos = "CREATE TABLE IF NOT EXISTS `pedidos` (" +
            "  `"+KEY_CODIGO+"` INTEGER NOT NULL PRIMARY KEY," +
            "  `usuarioDNI` TEXT NOT NULL," +
            "  `zonaId` TEXT NOT NULL," +
            "  `peso` TEXT NOT NULL," +
            "  `tarifaPeso` TEXT NOT NULL," +
            "   FOREIGN KEY(`usuarioDNI`) REFERENCES usuarios("+ USUARIO_DNI +")," +
            "   FOREIGN KEY(`zonaId`) REFERENCES destinos("+ KEY_CODIGO +")" +
            "  );";



    public EnviosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreateUsuarios);
        db.execSQL(sqlCreateDestinos);
        db.execSQL(sqlCreatePedidos);

        ContentValues values = new ContentValues();
        values.put(USUARIO_CODIGO, 1);
        values.put(USUARIO_DNI, "123456789A");
        values.put(USUARIO_NOMBRE, "admin");
        values.put(USUARIO_APELLIDO1, "apellido1");
        values.put(USUARIO_APELLIDO2, "apellido2");
        values.put(USUARIO_COMAUT, "comaut");
        values.put(USUARIO_PROVINCIA, "provinvia");
        values.put(USUARIO_LOCALIDAD, "localidad");
        values.put(USUARIO_DIRECCION, "direccion");
        values.put(USUARIO_EMAIL, "admin@admin.com");
        values.put(USUARIO_PASS, "admin");
        // insert row
        db.insert(TABLA_USUARIOS, null, values);
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
        //values.put(USUARIO_CODIGO, usuario.getCodigo());
        values.put(USUARIO_DNI, usuario.getDni());
        values.put(USUARIO_NOMBRE, usuario.getNombre());
        values.put(USUARIO_APELLIDO1, usuario.getApellido1());
        values.put(USUARIO_APELLIDO2, usuario.getApellido2());
        values.put(USUARIO_COMAUT, usuario.getComAut());
        values.put(USUARIO_PROVINCIA, usuario.getProvincia());
        values.put(USUARIO_LOCALIDAD, usuario.getLocalidad());
        values.put(USUARIO_DIRECCION, usuario.getDireccion());
        values.put(USUARIO_EMAIL, usuario.getEmail());
        values.put(USUARIO_PASS,usuario.getPassword());

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
    //public Usuario getUsuario(String usuario_dni) {
    public List<Usuario> getUsuario(String usuario_dni) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Usuario> usuarios = new ArrayList<Usuario>();

        String selectQuery = "SELECT  * FROM " + TABLA_USUARIOS + " WHERE " + USUARIO_DNI + " = '" + usuario_dni + "'";
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Usuario td = new Usuario();
                td.setCodigo(c.getString(c.getColumnIndex(KEY_CODIGO)));
                td.setNombre((c.getString(c.getColumnIndex(USUARIO_NOMBRE))));
                td.setDni(c.getString(c.getColumnIndex(USUARIO_DNI)));
                td.setApellido1(c.getString(c.getColumnIndex(USUARIO_APELLIDO1)));
                td.setApellido2(c.getString(c.getColumnIndex(USUARIO_APELLIDO2)));
                td.setComAut(c.getString(c.getColumnIndex(USUARIO_COMAUT)));
                td.setProvincia(c.getString(c.getColumnIndex(USUARIO_PROVINCIA)));
                td.setLocalidad(c.getString(c.getColumnIndex(USUARIO_LOCALIDAD)));
                td.setDireccion(c.getString(c.getColumnIndex(USUARIO_DIRECCION)));
                td.setEmail(c.getString(c.getColumnIndex(USUARIO_EMAIL)));
                td.setPassword(c.getString(c.getColumnIndex(USUARIO_PASS)));
                // adding to Usuario list
                usuarios.add(td);
            } while (c.moveToNext());
        }
        return usuarios;
    }

    public Usuario getUsuarioLogin(String usuario_email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLA_USUARIOS + " WHERE " + USUARIO_EMAIL + " = '" + usuario_email + "'";
        Cursor c = db.rawQuery(selectQuery, null);
        Usuario td = new Usuario();
        if (c.moveToFirst()){
            do {
                td.setCodigo(c.getString(c.getColumnIndex(KEY_CODIGO)));
                td.setNombre((c.getString(c.getColumnIndex(USUARIO_NOMBRE))));
                td.setEmail((c.getString(c.getColumnIndex(USUARIO_EMAIL))));
                td.setDni(c.getString(c.getColumnIndex(USUARIO_DNI)));
                td.setApellido1(c.getString(c.getColumnIndex(USUARIO_APELLIDO1)));
                td.setApellido2(c.getString(c.getColumnIndex(USUARIO_APELLIDO2)));
                td.setComAut(c.getString(c.getColumnIndex(USUARIO_COMAUT)));
                td.setProvincia(c.getString(c.getColumnIndex(USUARIO_PROVINCIA)));
                td.setLocalidad(c.getString(c.getColumnIndex(USUARIO_LOCALIDAD)));
                td.setDireccion(c.getString(c.getColumnIndex(USUARIO_DIRECCION)));
                td.setPassword(c.getString(c.getColumnIndex(USUARIO_PASS)));
                c.close();
            }while (c.moveToNext());
        }
        db.close();
        return td;
    }

    public Usuario getUsuarioByDni(String dni) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLA_USUARIOS + " WHERE " + USUARIO_DNI + " = '"+dni+"'";
        Cursor c = db.rawQuery(selectQuery, null);
        Usuario td = new Usuario();
        if (c.moveToFirst()){
            do {
                td.setCodigo(c.getString(c.getColumnIndex(KEY_CODIGO)));
                td.setNombre((c.getString(c.getColumnIndex(USUARIO_NOMBRE))));
                td.setEmail((c.getString(c.getColumnIndex(USUARIO_EMAIL))));
                td.setDni(c.getString(c.getColumnIndex(USUARIO_DNI)));
                td.setApellido1(c.getString(c.getColumnIndex(USUARIO_APELLIDO1)));
                td.setApellido2(c.getString(c.getColumnIndex(USUARIO_APELLIDO2)));
                td.setComAut(c.getString(c.getColumnIndex(USUARIO_COMAUT)));
                td.setProvincia(c.getString(c.getColumnIndex(USUARIO_PROVINCIA)));
                td.setLocalidad(c.getString(c.getColumnIndex(USUARIO_LOCALIDAD)));
                td.setDireccion(c.getString(c.getColumnIndex(USUARIO_DIRECCION)));
                td.setPassword(c.getString(c.getColumnIndex(USUARIO_PASS)));
                c.close();
            }while (c.moveToNext());
        }
        db.close();
        return td;
    }

    /*
     * getting all usuarios
     * */
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        String selectQuery = "SELECT  * FROM " + TABLA_USUARIOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Usuario td = new Usuario();
                td.setCodigo(c.getString(c.getColumnIndex(KEY_CODIGO)));
                td.setNombre(c.getString(c.getColumnIndex(USUARIO_NOMBRE)));
                td.setDni(c.getString(c.getColumnIndex(USUARIO_DNI)));
                td.setApellido1(c.getString(c.getColumnIndex(USUARIO_APELLIDO1)));
                td.setApellido2(c.getString(c.getColumnIndex(USUARIO_APELLIDO2)));
                td.setComAut(c.getString(c.getColumnIndex(USUARIO_COMAUT)));
                td.setProvincia(c.getString(c.getColumnIndex(USUARIO_PROVINCIA)));
                td.setLocalidad(c.getString(c.getColumnIndex(USUARIO_LOCALIDAD)));
                td.setDireccion(c.getString(c.getColumnIndex(USUARIO_DIRECCION)));
                td.setEmail(c.getString(c.getColumnIndex(USUARIO_EMAIL)));
                td.setPassword(c.getString(c.getColumnIndex(USUARIO_PASS)));
                // adding to Usuario list
                usuarios.add(td);
            } while (c.moveToNext());
        }

        return usuarios;
    }


    /*
     * Updating a Usuario
     */
    public int updateUsuario(Usuario usuario) {
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
        values.put(USUARIO_PASS,usuario.getPassword());

        // updating row
        return db.update(TABLA_USUARIOS, values, USUARIO_DNI + " = ?",
                new String[] { String.valueOf(usuario.getDni()) });
    }

    /*
     * Deleting a Usuario
     */
    public void deleteUsuario(String usuario_dni) {
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

    public long crearPedido(Pedido pedido) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(PEDIDO_CODIGO, pedido.getCodigo());
        values.put(PEDIDO_USUARIODNI, pedido.getUsuarioDNI());
        values.put(PEDIDO_ZONAID, pedido.getZonaId());
        values.put(PEDIDO_PESO, pedido.getPeso());
        values.put(PEDIDO_TARIFAPESO, pedido.getTarifaPeso());

        // insert row
        long usuario_id = db.insert(TABLA_PEDIDOS, null, values);

        return usuario_id;
    }


    /*
     * getting all pedidos
     * */
    public List<Pedido> getAllPedidos() {
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


    /*
     * getting all pedidos usuario
     * */
    public List<Pedido> getAllPedidosUsuario(String dniUsuario) {
        List<Pedido> pedidos = new ArrayList<>();
        //String selectQuery = "SELECT  * FROM " + TABLA_PEDIDOS + " WHERE usuarioDNI LIKE '" + dniUsuario + "'";
        String selectQuery = "SELECT * " +
                "FROM "+TABLA_PEDIDOS+" p, "+TABLA_USUARIOS+" u, "+TABLA_DESTINOS+" d" +
                " WHERE u."+USUARIO_DNI+" = p."+PEDIDO_USUARIODNI+" AND p."+  PEDIDO_ZONAID +" = d.codigo AND u."+USUARIO_DNI+" = '" + dniUsuario + "'" +
                " GROUP BY p."+PEDIDO_CODIGO;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Pedido td = new Pedido();
                td.setCodigo(c.getInt(c.getColumnIndex(KEY_CODIGO)));
                td.setUsuarioDNI(c.getString(c.getColumnIndex(PEDIDO_USUARIODNI)));
                td.setZonaId(c.getString(c.getColumnIndex(PEDIDO_ZONAID)));
                td.setPeso(c.getString(c.getColumnIndex(PEDIDO_PESO)));
                td.setTarifaPeso(c.getString(c.getColumnIndex(PEDIDO_TARIFAPESO)));
                Log.d("RESULTADO",c.getString(c.getColumnIndex(PEDIDO_USUARIODNI)));
                pedidos.add(td);
            } while (c.moveToNext());
        }

        return pedidos;
    }




    /*
    * ==========================================================================
    * ==========================================================================
    *
    *                           CRUD DESTINOS
    *
    * ==========================================================================
    * ==========================================================================
    * */

    public long crearDestino(Destino destino) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(PEDIDO_CODIGO, pedido.getCodigo());
        values.put(DESTINO_NOMBRE, destino.getNombre());
        values.put(DESTINO_ZONA, destino.getZona());
        values.put(DESTINO_PRECIO, destino.getPrecio());
        values.put(DESTINO_IMAGEN, destino.getImagen());

        // insert row
        long destino_id = db.insert(TABLA_DESTINOS, null, values);

        return destino_id;
    }


    /*
     * getting all destinos
     * */
    public List<Destino> getAllDestinos() {
        List<Destino> destinos = new ArrayList<Destino>();
        String selectQuery = "SELECT  * FROM " + TABLA_DESTINOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Destino td = new Destino();
                td.setId(c.getInt(c.getColumnIndex(KEY_CODIGO)));
                td.setNombre((c.getString(c.getColumnIndex(DESTINO_NOMBRE))));
                td.setZona(c.getString(c.getColumnIndex(DESTINO_ZONA)));
                td.setPrecio(c.getColumnIndex(DESTINO_PRECIO));
                td.setImagen(c.getColumnIndex(DESTINO_IMAGEN));

                // adding to Usuario list
                destinos.add(td);
            } while (c.moveToNext());
        }

        return destinos;
    }

    public int getCountDestinos(){

        String selectQuery = "SELECT COUNT(*) FROM " + TABLA_DESTINOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int destinos = c.getCount();

        return destinos;

    }

}
