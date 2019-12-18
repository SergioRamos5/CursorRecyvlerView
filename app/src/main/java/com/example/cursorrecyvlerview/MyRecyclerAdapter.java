package com.example.cursorrecyvlerview;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter extends CursorRecyclerAdapterAbs {

    private  int mLayout;
    private int[] mFrom;
    private int[] mTo;

    public MyRecyclerAdapter(int layout, Cursor cursor, String [] from, int[] to) {
        super(cursor);
        mLayout = layout;
        mTo = to;
        findColumns(cursor, from);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        return new SimpleViewHolder(v, mTo);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {
        ((SimpleViewHolder) holder).bind(0, cursor.getString(mFrom[0]));
        ((SimpleViewHolder) holder).bind(1, cursor.getString(mFrom[1]));
        Bitmap theImage = MainActivity.convertirStringBitmap(cursor.getString(mFrom[2]));
        ((SimpleViewHolder) holder).bind(theImage);
    }



    private void findColumns(Cursor c, String[] from)
    {
        if (c != null)
        {
            int i;
            int count = from.length;
            if (mFrom == null || mFrom.length != count)
                mFrom = new int[count];
            for (i = 0; i < count; i++)
                mFrom[i]= c.getColumnIndexOrThrow(from[i]);
        }else
            mFrom=null;
    }


}
