package com.example.maelchiaverini.helpme.Classes;

import android.provider.ContactsContract;

import com.orm.SugarRecord;

/**
 * Created by Mael Chiaverini on 13/05/2016.
 */
public class Contact extends SugarRecord {
    String nom;
    String numero;
    Boolean valid;

    public Contact() {}

    public Contact(String nom, String num){
        this.nom = nom;
        this.numero = num;
        this.valid = true;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
