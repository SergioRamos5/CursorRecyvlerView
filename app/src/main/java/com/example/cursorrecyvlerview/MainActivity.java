package com.example.cursorrecyvlerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    OHCategoria ohCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] from = new String[]{"nombre", "cate", "imagen"};
        int[] to = new int[]{R.id.ciclo, R.id.cate, R.id.image};
        setContentView(R.layout.activity_main);
        ohCategoria = new OHCategoria(this, "BBDCategoria", null, 1);

        insertarDatosCodigo();

        sqLiteDatabase = ohCategoria.getReadableDatabase();
        if (sqLiteDatabase != null)
        {
            RecyclerView desplegable = findViewById(R.id.reycler);
            Cursor cur = sqLiteDatabase.rawQuery("select idcategoria as _id, nombre, cate, imagen from categoria",null);
            MyRecyclerAdapter mAdapter = new MyRecyclerAdapter(R.layout.layout_recycler, cur, from, to);
            desplegable.setAdapter(mAdapter);
            desplegable.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }
    }

    static public Bitmap convertirStringBitmap(String imagen)
    {
        byte[] decodedString = Base64.decode(imagen, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    static  public String ConvertirImagenString(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        byte[] byte_arr = stream.toByteArray();
        String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
        return image_str;
    }

    public void insertarDatosCodigo()
    {
        sqLiteDatabase = ohCategoria.getWritableDatabase();
        String img;
        if (sqLiteDatabase != null)
        {
            ContentValues valores = new ContentValues();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            valores.put("nombre", "ASIR");
            valores.put("cate", "Superior");
            valores.put("idcategoria", 1);
            img = ConvertirImagenString(BitmapFactory.decodeResource(getResources(), R.drawable.descarga));
            valores.put("imagen", img);
            sqLiteDatabase.insert("categoria", null, valores);

            valores.put("nombre", "DAM");
            valores.put("cate", "Superior");
            valores.put("idcategoria", 2);
            img = ConvertirImagenString(BitmapFactory.decodeResource(getResources(), R.drawable.descarga));
            valores.put("imagen", img);
            sqLiteDatabase.insert("categoria", null, valores);

            valores.put("nombre", "SMR");
            valores.put("cate", "Medio");
            valores.put("idcategoria", 3);
            img = ConvertirImagenString(BitmapFactory.decodeResource(getResources(), R.drawable.descarga));
            valores.put("imagen", img);
            sqLiteDatabase.insert("categoria", null, valores);
            sqLiteDatabase.close();
        }
    }
}
