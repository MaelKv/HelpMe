package com.example.maelchiaverini.helpme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        ImageButton AlerteBtn = (ImageButton) findViewById(R.id.btn_alerte);
        AlerteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.findById(Message.class,1);
                if (message!=null) {
                    List<Contact> contacts = Contact.listAll(Contact.class);
                    if(contacts.size() > 0) {
                        for(Contact contact : contacts)
                        {
                            SmsManager.getDefault().sendTextMessage(contact.getNumero(), null, message.getTitre() + " " + message.getContenu(), null, null);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Vous devez configurer au moins un contact",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Vous devez configurer un message",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Pour vider la liste des contacts
        //List<Contact> conts = Contact.listAll(Contact.class);
        //Contact.deleteAll(Contact.class);
        //List<Message> msg = Message.listAll(Message.class);
        //Message.deleteAll(Message.class);

        ImageButton ConfigBtn = (ImageButton) findViewById(R.id.btn_conf);
        ConfigBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(AccueilActivity.this, ConfigActivity.class);
                startActivity(secondeActivite);
            }
        });
    }
}
