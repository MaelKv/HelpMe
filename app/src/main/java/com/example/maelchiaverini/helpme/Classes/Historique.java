package com.example.maelchiaverini.helpme.Classes;

import com.google.android.gms.maps.model.LatLng;
import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by Mael Chiaverini on 13/05/2016.
 */
public class Historique extends SugarRecord {
    List<String> contacts;
    String titre;
    String message;
    Date date;
    double latitude;
    double longitude;
    Long id;

    public Historique(){}

    public Historique(List<String> contacts, String titre, String msg, double latitude, double longitude)
    {
        this.contacts = contacts;
        this.titre = titre;
        this.message = msg;
        this.date = new Date();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}