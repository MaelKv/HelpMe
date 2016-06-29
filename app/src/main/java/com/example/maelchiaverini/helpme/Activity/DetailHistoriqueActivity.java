
package com.example.maelchiaverini.helpme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ListView listCont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_historique);

        TextView tvTitre = (TextView) findViewById(R.id.tvDetTitre);
        TextView tvMsg = (TextView) findViewById(R.id.tvDetMsg);
        TextView tvDate = (TextView) findViewById(R.id.tvDateTitre);
        listCont = (ListView) findViewById(R.id.listViewCont);

        histo = Historique.findById(Historique.class, 1);
        if(histo != null) {
            //LinearLayout map = (LinearLayout) findViewById(R.id.map);
            //map.setVisibility(View.GONE);

            assert tvTitre != null;
            tvTitre.setText(histo.getTitre());
            assert tvMsg != null;
            tvMsg.setText(histo.getMessage());
            assert tvDate != null;
            tvDate.setText(histo.getDate().toString());

            assert listCont != null;
            List<String> list;
            list = histo.getContacts();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_item_historique, R.id.listViewCont, list);
            listCont.setAdapter(adapter);

            //MapFragment mapFragment = (MapFragment ) getFragmentManager().findFragmentById(R.id.map);
            //mapFragment.getMapAsync(this);
        }
    }
/*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng lieu = new LatLng(histo.getLatitude(),histo.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(lieu));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lieu,16));
    }*/
}