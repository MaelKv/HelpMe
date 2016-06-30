package com.example.maelchiaverini.helpme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailHistoriqueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_historique);

        TextView tvTitre = (TextView) findViewById(R.id.tvDetTitre);
        TextView tvMsg = (TextView) findViewById(R.id.tvDetMsg);
        TextView tvDate = (TextView) findViewById(R.id.tvDetDate);
        ListView listCont = (ListView) findViewById(R.id.ListDetCont);
        ImageButton btnRet = (ImageButton) findViewById(R.id.btnDetReturn);

        Historique histo = Historique.findById(Historique.class, HistoriqueActivity.idH);
        if(histo != null) {
            assert tvTitre != null;
            tvTitre.setText(histo.getTitre());
            assert tvMsg != null;
            tvMsg.setText(histo.getMessage());
            assert tvDate != null;

            Date d = histo.getDate();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - k:mm");
            String date = format.format(d);
            tvDate.setText(date);

            assert listCont != null;
            String[] conts = histo.getContacts().split(",");
            ArrayList<String> list = new ArrayList<>();
            for(int i = 0; i < conts.length; i++)
            {
                list.add(conts[i]);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_item_detail_contact, R.id.tv_cont_num, list);
            listCont.setAdapter(adapter);
        }

        assert btnRet != null;
        btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(DetailHistoriqueActivity.this, HistoriqueActivity.class);
                startActivity(secondeActivite);
            }
        });
    }
}