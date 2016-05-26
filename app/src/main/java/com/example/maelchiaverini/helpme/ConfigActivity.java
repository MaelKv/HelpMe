package com.example.maelchiaverini.helpme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    EditText txtTitre = (EditText) findViewById(R.id.txt_titre);
    EditText txtMsg = (EditText) findViewById(R.id.txt_titre);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Message message = Message.findById(Message.class,1);
        if(message!=null){
            txtTitre.setText(message.getTitre());
            txtMsg.setText(message.getContenu());
        }

        Button valider = (Button) findViewById(R.id.btn_valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titre = txtTitre.getText().toString();
                String message = txtMsg.getText().toString();
                Message msg_connu = Message.findById(Message.class, 1);

                if (msg_connu == null) {
                    msg_connu = new Message(titre, message, 1);
                    msg_connu.save();
                } else {
                    msg_connu.setContenu(message);
                    msg_connu.setTitre(titre);
                    msg_connu.save();
                }

                Toast.makeText(getApplicationContext(), "Message configuré !",
                        Toast.LENGTH_SHORT).show();

                Intent secondeActivite = new Intent(ConfigActivity.this, AccueilActivity.class);
                startActivity(secondeActivite);
            }
        });

        Button conta = (Button) findViewById(R.id.btn_contact);
        conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(ConfigActivity.this, ContactActivity.class);
                startActivity(secondeActivite);
            }
        });

        Button histor = (Button) findViewById(R.id.btn_historique);
        histor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "bouton historique !",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
