package com.example.maelchiaverini.helpme;

import com.orm.SugarRecord;

/**
 * Created by Mael Chiaverini on 13/05/2016.
 */
public class Historique extends SugarRecord {
    Contact contact;
    Message msg;

    public Historique(){}

    public Historique(Contact contact, Message msg)
    {
        this.contact = contact;
        this.msg = msg;
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
}
