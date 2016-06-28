package com.example.maelchiaverini.helpme.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
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
import java.util.List;

public class AccueilActivity extends AppCompatActivity {

    private Activity activity;
    private final int MY_PERMISSIONS=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        activity = this;

        ImageButton AlerteBtn = (ImageButton) findViewById(R.id.btn_alerte);
        AlerteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.findById(Message.class, 1);
                if (message != null) {
                    if (ActivityCompat.checkSelfPermission(AccueilActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(AccueilActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        /*Toast.makeText(getApplicationContext(), "Permission",
                                Toast.LENGTH_SHORT).show();*/
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
                        }, MY_PERMISSIONS);
                        return;
                    }
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    LatLng newLL = new LatLng(0,0);
                    if(location != null) {
                        newLL = new LatLng(location.getLatitude(), location.getLongitude());
                    }
                    List<Contact> contacts = Select.from(Contact.class).where(Condition.prop("valid").eq(true)).list(); //Contact.find(Contact.class, "valid = ?","true");// listAll(Contact.class);
                    if (contacts.isEmpty()) {
                        for (Contact contact : contacts) {
                            //SmsManager.getDefault().sendTextMessage(contact.getNumero(), null, message.getTitre() + " " + message.getContenu(), null, null);
                            Toast.makeText(getApplicationContext(), contact.getNom(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        Historique histo = new Historique(contacts, message.getTitre(),message.getContenu(), newLL);
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
        //Pour vider la liste des contacts
        /*List<Contact> conts = Contact.listAll(Contact.class);
        Contact.deleteAll(Contact.class);
        List<Message> msg = Message.listAll(Message.class);
        Message.deleteAll(Message.class);*/

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
