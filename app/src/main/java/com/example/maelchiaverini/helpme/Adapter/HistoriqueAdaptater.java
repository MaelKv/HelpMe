package com.example.maelchiaverini.helpme.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.maelchiaverini.helpme.Activity.DetailHistoriqueActivity;
import com.example.maelchiaverini.helpme.Classes.Historique;
import com.example.maelchiaverini.helpme.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoriqueAdaptater extends BaseAdapter {
    private Context mContext;
    private List<Historique> histolist;

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
        TextView txt_historique_message = (TextView) v.findViewById(R.id.tvTitreMessage);
        TextView txt_historique_date = (TextView) v.findViewById(R.id.tvDateHisto);
        //SET
        if(histolist.get(position) != null) {
            txt_historique_message.setText(histolist.get(position).getTitre());
            Date d = histolist.get(position).getDate();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - k:mm");
            String date = format.format(d);

            txt_historique_date.setText(date);
        }
        v.setTag(histolist.get(position).getId());

        return v;
    }
}