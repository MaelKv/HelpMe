package com.example.maelchiaverini.helpme;

import com.orm.SugarRecord;

/**
 * Created by Mael Chiaverini on 13/05/2016.
 */
public class Message extends SugarRecord {
    String titre;
    String contenu;
    Long id;

    public Message(){}

    public Message(String titre, String contenu, long id){
        this.titre = titre;
        this.contenu = contenu;
        this.id = id;
    }
    public String getContenu() { return contenu; }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Long geId() { return id; }
}