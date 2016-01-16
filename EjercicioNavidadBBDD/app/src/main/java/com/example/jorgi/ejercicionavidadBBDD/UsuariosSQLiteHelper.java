package com.example.jorgi.ejercicionavidadBBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jorgi on 13/01/2016.
 */
public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    // C: - cd\Users\Jorgi\AppData\Local\Android\sdk1\platform-tools

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE IF NOT EXISTS `usuarios` (" +
            "  `codigo` INTEGER NOT NULL PRIMARY KEY," +
            "  `dni` TEXT NOT NULL," +
            "  `nombre` TEXT NOT NULL," +
            "  `apellido1` TEXT NOT NULL," +
            "  `apellido2` TEXT NOT NULL," +
            "  `comAut` TEXT NOT NULL," +
            "  `provincia` TEXT NOT NULL," +
            "  `localidad` TEXT NOT NULL," +
            "  `direccion` TEXT NOT NULL," +
            "  `email` TEXT NOT NULL" +
            "  );";

    public UsuariosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS usuarios");

        //Se crea la nueva versión de la tabla vacía de datosActivity
        db.execSQL(sqlCreate);
    }
}
