package com.example.cursorrecyvlerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OHCategoria extends SQLiteOpenHelper {

    String cadena = "create table if not exists categoria (idcategoria INTEGER PRIMARY KEY NOT NULL, nombre Text, cate TEXT, imagen TEXT )";

    public OHCategoria(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(cadena);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}