package com.example.maelchiaverini.helpme;

import com.orm.SugarRecord;

/**
 * Created by Mael Chiaverini on 13/05/2016.
 */
public class Message extends SugarRecord {
    String titre;
    String msg_contenu;
    Long id;

    public Message(){}

    public Message(String titre, String contenu, long id){
        this.titre = titre;
        this.msg_contenu = contenu;
        this.id = id;
    }
    public String getContenu() { return this.msg_contenu; }

    public void setContenu(String contenu) {
        this.msg_contenu = contenu;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Long geId() { return this.id; }
}