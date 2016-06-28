
package com.example.maelchiaverini.helpme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maelchiaverini.helpme.Classes.Contact;
import com.example.maelchiaverini.helpme.Classes.Historique;
import com.example.maelchiaverini.helpme.Adapter.HistoriqueAdaptater;
import com.example.maelchiaverini.helpme.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class DetailHistoriqueActivity extends AppCompatActivity /*implements OnMapReadyCallback*/ {

    Historique histo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_historique);

        Intent i = new Intent();
        Long id = i.getLongExtra(HistoriqueAdaptater.IdHistorique, 1);

        histo = Historique.findById(Historique.class, id);
        if(histo != null) {
            TextView tvTitre = (TextView) findViewById(R.id.tvDetTitre);
            TextView tvMsg = (TextView) findViewById(R.id.tvDetMsg);
            TextView tvDate = (TextView) findViewById(R.id.tvDateHisto);
            ListView listcont = (ListView) findViewById(R.id.listView);
            LinearLayout map = (LinearLayout) findViewById(R.id.map);
            map.setVisibility(View.GONE);

            tvTitre.setText(histo.getTitre());
            tvMsg.setText(histo.getMessage());
            tvDate.setText(histo.getDate().toString());

            List<String> list = new ArrayList<String>();
            for (Contact cont : histo.getContacts()) {
                list.add(cont.getNom());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_detail_historique, R.id.listView, list);
            listcont.setAdapter(adapter);

            MapFragment mapFragment = (MapFragment ) getFragmentManager().findFragmentById(R.id.map);
            //mapFragment.getMapAsync(this);
        }
    }
/*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng lieu = histo.getLatlng();
        googleMap.addMarker(new MarkerOptions().position(lieu));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lieu,16));
    }*/
}