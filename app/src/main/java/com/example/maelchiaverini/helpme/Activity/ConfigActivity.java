package com.example.maelchiaverini.helpme.Activity;

import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.maelchiaverini.helpme.Classes.Message;
import com.example.maelchiaverini.helpme.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Objects;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        EditText txtTitre = (EditText) findViewById(R.id.txt_titre);
        EditText txtMsg = (EditText) findViewById(R.id.txt_msg);

        Message message = Message.findById(Message.class, 1);
        if (message != null) {
            txtTitre.setText(message.getTitre());
            txtMsg.setText(message.getContenu());
        }
        ImageButton valider = (ImageButton) findViewById(R.id.btn_valider);
        assert valider != null;
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtTitre = (EditText) findViewById(R.id.txt_titre);
                EditText txtMsg = (EditText) findViewById(R.id.txt_msg);

                String titre = txtTitre.getText().toString();
                String msg = txtMsg.getText().toString();

                if (!Objects.equals(titre, "") && !Objects.equals(msg, "")) {
                    Message msg_connu = Message.findById(Message.class, 1);
                    if (msg_connu != null) {
                        msg_connu.setContenu(msg);
                        msg_connu.setTitre(titre);
                        msg_connu.save();
                    } else {
                        msg_connu = new Message(titre, msg, 1);
                        msg_connu.save();
                    }

                    Intent secondeActivite = new Intent(ConfigActivity.this, AccueilActivity.class);
                    startActivity(secondeActivite);
                } else {
                    Toast.makeText(getApplicationContext(), "Saisie incorrecte",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton conta = (ImageButton) findViewById(R.id.btn_contact);
        assert conta != null;
        conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(ConfigActivity.this, ContactActivity.class);
                startActivity(secondeActivite);
            }
        });

        ImageButton histor = (ImageButton) findViewById(R.id.btn_historique);
        assert histor != null;
        histor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(ConfigActivity.this, HistoriqueActivity.class);
                startActivity(secondeActivite);
            }
        });
    }
}