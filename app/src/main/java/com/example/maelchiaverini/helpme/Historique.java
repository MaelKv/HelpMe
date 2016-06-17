package com.example.maelchiaverini.helpme;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Mael Chiaverini on 13/05/2016.
 */
public class Historique extends SugarRecord {
    Contact contact;
    Message msg;
    Date date;

    public Historique(){}

    public Historique(Contact contact, Message msg)
    {
        this.contact = contact;
        this.msg = msg;
        this.date = new Date();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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
}
