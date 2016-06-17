package com.example.maelchiaverini.helpme.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maelchiaverini.helpme.Classes.Message;
import com.example.maelchiaverini.helpme.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ConfigActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        try{
            Message message = Message.findById(Message.class, 1);
            EditText txtTitre = (EditText) findViewById(R.id.txt_titre);
            EditText txtMsg = (EditText) findViewById(R.id.txt_msg);
            txtTitre.setText(message.getTitre());
            txtMsg.setText(message.getContenu());
        }
        catch(SQLiteException s){}


        Button valider = (Button) findViewById(R.id.btn_valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtTitre = (EditText) findViewById(R.id.txt_titre);
                EditText txtMsg = (EditText) findViewById(R.id.txt_msg);

                String titre = txtTitre.getText().toString();
                String msg = txtMsg.getText().toString();

                Message msg_connu = Message.findById(Message.class, 1);

                if (msg_connu == null) {
                    msg_connu = new Message(titre, msg, 1);
                    msg_connu.save();
                } else {
                    msg_connu.setContenu(msg);
                    msg_connu.setTitre(titre);
                    msg_connu.save();
                }
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
                Intent secondeActivite = new Intent(ConfigActivity.this, HistoriqueActivity.class);
                startActivity(secondeActivite);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Config Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.maelchiaverini.helpme.Activity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Config Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.maelchiaverini.helpme.Activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}