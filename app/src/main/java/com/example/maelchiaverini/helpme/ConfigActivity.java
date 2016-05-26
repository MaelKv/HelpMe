package com.example.maelchiaverini.helpme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button valider = (Button) findViewById(R.id.btn_valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtTitre = (EditText) findViewById(R.id.txt_titre);
                String titre = txtTitre.getText().toString();

                EditText txtMsg = (EditText) findViewById(R.id.txt_titre);
                String message = txtMsg.getText().toString();

                Message message1 = new Message(titre, message, 1);
                message1.save();

                Toast.makeText(getApplicationContext(), "Message configuré ! test id : " + message1.getTitre(),
                        Toast.LENGTH_SHORT).show();

                Intent secondeActivite = new Intent(ConfigActivity.this, AccueilActivity.class);
                startActivity(secondeActivite);
            }
        });

        Button conta = (Button) findViewById(R.id.btn_contact);
        conta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent secondeActivite = new Intent(ConfigActivity.this, ContactActivity.class);
                startActivity(secondeActivite);
            }
        });

        Button histor = (Button) findViewById(R.id.btn_historique);
        histor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "bouton historique !",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
