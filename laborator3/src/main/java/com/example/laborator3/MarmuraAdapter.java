package com.example.laborator3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MarmuraAdapter extends BaseAdapter {
    private Context ctx;
    private List<Marmura> listaMarmura;

    public MarmuraAdapter(Context ctx, List<Marmura> listaMarmura) {
        this.ctx = ctx;
        this.listaMarmura = listaMarmura;
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
        LayoutInflater inflater= LayoutInflater.from(ctx);
        View v=inflater.inflate(R.layout.item_layout, parent, false);
        TextView culoare=v.findViewById(R.id.culoare_TV);
        TextView tip=v.findViewById(R.id.tip_TV);
        TextView pret=v.findViewById(R.id.pret_TV);
        TextView finisaj=v.findViewById(R.id.finisaj_TV);
        TextView inStoc=v.findViewById(R.id.inStoc_TV);

        Marmura m=(Marmura)getItem(position);

        culoare.setText(m.getCuloare());
        tip.setText(m.getTipMarmura());
        pret.setText("" + m.getPretPeMetruPatrat());
        finisaj.setText(m.getFinisaj());
        inStoc.setText("" + m.isEsteInStoc());

        return v;

    }

    public void remove(Marmura marmuraStearsa) {
        listaMarmura.remove(marmuraStearsa);
    }
}
