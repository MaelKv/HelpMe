package com.example.maelchiaverini.helpme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maelchiaverini.helpme.Classes.Contact;
import com.example.maelchiaverini.helpme.Classes.Historique;
import com.example.maelchiaverini.helpme.Adapter.HistoriqueAdaptater;
import com.example.maelchiaverini.helpme.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class DetailHistoriqueActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_historique);

        Intent i = new Intent();
        Long id = i.getLongExtra(HistoriqueAdaptater.IdHistorique, 1);

        Historique histo = Historique.findById(Historique.class, id);

        TextView tvTitre = (TextView) findViewById(R.id.tvDetTitre);
        TextView tvMsg = (TextView) findViewById(R.id.tvDetMsg);
        TextView tvDate = (TextView) findViewById(R.id.tvDateHisto);
        ListView listcont = (ListView) findViewById(R.id.listView);

        tvTitre.setText(histo.getMsg().getTitre());
        tvMsg.setText(histo.getMsg().getContenu());
        tvDate.setText(histo.getDate().toString());

        List<String> list = new ArrayList<String>();
        for (Contact cont : histo.getContact())
        {
            list.add(cont.getNom());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_detail_historique,R.id.listView,list);
        listcont.setAdapter(adapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
