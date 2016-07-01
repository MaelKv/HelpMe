package com.example.maelchiaverini.helpme.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.maelchiaverini.helpme.Classes.Contact;
import com.example.maelchiaverini.helpme.Classes.Historique;
import com.example.maelchiaverini.helpme.Classes.Message;
import com.example.maelchiaverini.helpme.R;
import com.google.android.gms.maps.model.LatLng;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccueilActivity extends AppCompatActivity {

    private Activity activity;
    private final int PERMISSIONS=1;
    LocationManager mLoMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        activity = this;

        ImageButton AlerteBtn = (ImageButton) findViewById(R.id.btn_alerte);
        assert AlerteBtn != null;
        AlerteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.findById(Message.class, 1);
                if (message != null) {
                    if (ActivityCompat.checkSelfPermission(AccueilActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(AccueilActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{
                                android.Manifest.permission.READ_CONTACTS,
                                android.Manifest.permission.ACCESS_FINE_LOCATION,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.CALL_PHONE,
                                android.Manifest.permission.VIBRATE,
                                android.Manifest.permission.INTERNET,
                                android.Manifest.permission.SEND_SMS
                        }, PERMISSIONS);
                        return;
                    }
                    Location location = getLocation();
                    double latitude = 0,longitude = 0;
                    if(location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                    List<Contact> contacts = Contact.listAll(Contact.class);
                    String test = "bla bla " + !contacts.isEmpty();
                    if (!contacts.isEmpty()) {
                        String listContact = "";
                        String msg;
                        if(latitude != 0.0 && longitude != 0.0) {
                            msg = String.format("%1$s\n%2$s\nhttps://www.google.fr/maps/@%3$s,%4$s,14z !", message.getTitre(), message.getContenu(), latitude, longitude);
                        }
                        else{
                            msg = String.format("%1$s\n%2$s", message.getTitre(), message.getContenu());
                        }
                        for (Contact contact : contacts) {
                            if(contact.getValid()) {
                                SmsManager.getDefault().sendTextMessage(contact.getNumero(), null,msg, null, null);
                                listContact=listContact+(contact.getNom() + " - " + contact.getNumero())+",";
                            }
                        }
                        Historique histo = new Historique(listContact, message.getTitre(),msg, latitude,longitude);
                        histo.save();
                        Toast.makeText(getApplicationContext(), "Message envoyé !",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Vous devez configurer au moins un contact",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Vous devez configurer un message",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton ConfigBtn = (ImageButton) findViewById(R.id.btn_conf);
        assert ConfigBtn != null;
        ConfigBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(AccueilActivity.this, ConfigActivity.class);
                startActivity(secondeActivite);
            }
        });

        ImageButton HelpBtn = (ImageButton) findViewById(R.id.btn_help);
        HelpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Aide");
                builder.setMessage("Cliquer sur le bouton de configuration en bas à droite, pour configurer votre message ainsi que la liste des contacts \nVous pourrez également consulter l'historique.");
                builder.setNegativeButton("Ok", null);
                builder.show();
            }
        });
    }

    private Location getLocation() {
        mLoMan = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLoMan.getProviders(true);
        Location theLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            Location loc = mLoMan.getLastKnownLocation(provider);
            if (loc == null) {
                continue;
            }
            if (theLocation == null || loc.getAccuracy() < theLocation.getAccuracy()) {
                theLocation = loc;
            }
        }
        if (theLocation == null) {
            theLocation = new Location("nolocation");
            theLocation.setLongitude(0);
            theLocation.setLatitude(0);
        }
        return theLocation;
    }
}
