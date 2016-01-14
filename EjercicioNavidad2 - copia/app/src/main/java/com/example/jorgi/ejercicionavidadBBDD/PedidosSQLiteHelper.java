package com.example.jorgi.ejercicionavidadBBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jorgi on 14/01/2016.
 */
public class PedidosSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE IF NOT EXISTS `pedidos` (" +
            "  `codigo` INTEGER NOT NULL PRIMARY KEY," +
            "  `usuarioDNI` TEXT NOT NULL," +
            "  `zonaId` TEXT NOT NULL," +
            "  `peso` TEXT NOT NULL," +
            "  `tarifaPeso` TEXT NOT NULL" +
            "  );";

    /*
    continente
    envio
    tarifaPeso
    precio
    peso
    */

    public PedidosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS pedidos");

        //Se crea la nueva versión de la tabla vacía de datos
        db.execSQL(sqlCreate);
    }
}
