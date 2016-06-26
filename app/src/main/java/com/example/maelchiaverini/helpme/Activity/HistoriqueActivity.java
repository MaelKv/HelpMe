package com.example.maelchiaverini.helpme.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.maelchiaverini.helpme.Classes.Historique;
import com.example.maelchiaverini.helpme.Adapter.HistoriqueAdaptater;
import com.example.maelchiaverini.helpme.R;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueActivity extends AppCompatActivity {
    private ListView histoListView;
    private HistoriqueAdaptater adaptater;
    private List<Historique> histoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        histoListView = (ListView) findViewById(R.id.histo_list);
        try{
            histoList = new ArrayList<>();
            histoList = Historique.listAll(Historique.class);

            adaptater = new HistoriqueAdaptater(getApplicationContext(), histoList);
            histoListView.setAdapter(adaptater);
        }
        catch (SQLException e) {}

        ImageButton imgBtn = (ImageButton) findViewById(R.id.imgbtnReturnHisto);
        imgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(HistoriqueActivity.this, ConfigActivity.class);
                startActivity(secondeActivite);
            }
        });

        ImageButton delBtn = (ImageButton) findViewById(R.id.imgbtn_deleteHisto);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoriqueActivity.this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Suppression");
                builder.setMessage("Etes vous sur de vouloir supprimer l'historique ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Historique.deleteAll(Historique.class);
                                Intent secondeActivite = new Intent(HistoriqueActivity.this, HistoriqueActivity.class);
                                startActivity(secondeActivite);
                            }
                        });
                builder.setNegativeButton("Non", null);
                builder.show();
            }
        });
    }
}