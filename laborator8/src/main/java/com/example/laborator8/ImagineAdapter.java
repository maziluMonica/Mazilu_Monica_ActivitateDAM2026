package com.example.laborator8;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private Context ctx;
    private List<ImaginiMarmura> listaMarmura;
    private int imgLayout;

    public ImagineAdapter() {
    }



    public ImagineAdapter(Context applicationContext, int imgLayout, List<ImaginiMarmura> lista) {
   this.ctx=applicationContext;
   this.imgLayout=imgLayout;
   this.listaMarmura=lista;
    }

    @Override
    public int getCount() {
        return this.listaMarmura.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaMarmura.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(R.layout.img_layout,parent,false);
        ImageView imagine1_IV=v.findViewById(R.id.imagine1_IV);
        TextView imagine1_TV=v.findViewById(R.id.imagine1_TV);

        imagine1_IV.setImageBitmap(listaMarmura.get(position).getImagine());
        imagine1_TV.setText(listaMarmura.get(position).getTextAfisat());
        return v;
    }
}
