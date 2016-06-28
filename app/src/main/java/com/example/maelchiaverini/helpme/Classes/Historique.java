package com.example.maelchiaverini.helpme.Classes;

import com.google.android.gms.maps.model.LatLng;
import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by Mael Chiaverini on 13/05/2016.
 */
public class Historique extends SugarRecord {
    List<Contact> contacts;
    String titre;
    String message;
    Date date;
    LatLng latlng;

    public Historique(){}

    public Historique(List<Contact> contacts, String titre, String msg, LatLng latlng)
    {
        this.contacts = contacts;
        this.titre = titre;
        this.message = msg;
        this.date = new Date();
        this.latlng = latlng;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
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

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }
}