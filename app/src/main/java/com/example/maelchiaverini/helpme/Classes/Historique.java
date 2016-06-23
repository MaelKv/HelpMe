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
    Message msg;
    Date date;
    LatLng latlng;

    public Historique(){}

    public Historique(List<Contact> contacts, Message msg, LatLng latlng)
    {
        this.contacts = contacts;
        this.msg = msg;
        this.date = new Date();
        this.latlng = latlng;
    }

    public List<Contact> getContact() {
        return contacts;
    }

    public void setContact(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
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