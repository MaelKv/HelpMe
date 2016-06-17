package com.example.maelchiaverini.helpme.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.maelchiaverini.helpme.Activity.DetailHistoriqueActivity;
import com.example.maelchiaverini.helpme.Classes.Historique;
import com.example.maelchiaverini.helpme.R;

import java.util.List;

public class HistoriqueAdaptater extends BaseAdapter {
    private Context mContext;
    private List<Historique> histolist;

    public final static String IdHistorique = "IDhisto";

    //Constructeur
    public HistoriqueAdaptater(Context mContext, List<Historique> histolist) {
        this.mContext = mContext;
        this.histolist = histolist;
    }

    @Override
    public int getCount() { return histolist.size(); }

    @Override
    public Object getItem(int position) { return histolist.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.activity_item_historique, null);
        //TextView
        TextView nom_tv = (TextView) v.findViewById(R.id.tv_nom);
        TextView num_tv = (TextView) v.findViewById(R.id.tv_numero);
        CheckBox valid_cb = (CheckBox) v.findViewById(R.id.cb_valid);
        //SET
        nom_tv.setText(histolist.get(position).getMsg().getTitre());
        num_tv.setText(histolist.get(position).getDate().toString());
        //Click checkbox
        valid_cb.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent detailHisto = new Intent(mContext.getApplicationContext(), DetailHistoriqueActivity.class);
                detailHisto.putExtra(HistoriqueAdaptater.IdHistorique, histolist.get(position).getId());
                mContext.getApplicationContext().startActivity(detailHisto);
            }
        });

        v.setTag(histolist.get(position).getId());

        return v;
    }
}
