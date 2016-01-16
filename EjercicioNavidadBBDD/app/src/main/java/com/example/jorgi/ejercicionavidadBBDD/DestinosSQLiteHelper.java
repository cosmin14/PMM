package com.example.jorgi.ejercicionavidadBBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Jorgi on 14/01/2016.
 */
public class DestinosSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Destinos
    String sqlCreate = "CREATE TABLE IF NOT EXISTS `destinos` (" +
            "  `codigo` INTEGER NOT NULL PRIMARY KEY," +
            "  `zona` INTEGER NOT NULL," +
            "  `nombre` TEXT NOT NULL," +
            "  `tarifaDestino` TEXT NOT NULL" +
            "  );";


    public DestinosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
