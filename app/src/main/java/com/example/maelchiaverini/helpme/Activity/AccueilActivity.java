package com.example.maelchiaverini.helpme.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.maelchiaverini.helpme.Classes.Contact;
import com.example.maelchiaverini.helpme.Classes.Historique;
import com.example.maelchiaverini.helpme.Classes.Message;
import com.example.maelchiaverini.helpme.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        ImageButton AlerteBtn = (ImageButton) findViewById(R.id.btn_alerte);
        AlerteBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {



                /* ((checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }*/

                if (ActivityCompat.checkSelfPermission(AccueilActivity.this ,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(AccueilActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng newLL = new LatLng(location.getLatitude(), location.getLongitude());

                Message message = Message.findById(Message.class,1);
                if (message!=null) {
                    List<Contact> contacts = Contact.listAll(Contact.class);
                    if(contacts.size() > 0) {
                        for(Contact contact : contacts)
                        {
                            //SmsManager.getDefault().sendTextMessage(contact.getNumero(), null, message.getTitre() + " " + message.getContenu(), null, null);
                            Toast.makeText(getApplicationContext(), contact.getNumero() + " " +message.getTitre() +" "+ message.getContenu()+" "+ newLL,
                                    Toast.LENGTH_SHORT).show();
                        }
                        Historique histo = new Historique(contacts,message, newLL);
                        histo.save();
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
