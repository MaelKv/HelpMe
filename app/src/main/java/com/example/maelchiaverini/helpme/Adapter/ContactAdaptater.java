package com.example.maelchiaverini.helpme.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.maelchiaverini.helpme.Activity.ContactActivity;
import com.example.maelchiaverini.helpme.Classes.Contact;
import com.example.maelchiaverini.helpme.Classes.Historique;
import com.example.maelchiaverini.helpme.R;

import java.util.List;

/**
 * Created by Mael Chiaverini on 26/05/2016.
 */
public class ContactAdaptater extends BaseAdapter {
    private Context mContext;
    private List<Contact> contactList;

    //Constructeur
    public ContactAdaptater(Context mContext, List<Contact> contactList) {
        this.mContext = mContext;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.activity_item_contact, null);

        // Déclaration des items de la page
        TextView nom_tv = (TextView) v.findViewById(R.id.tv_nom);
        TextView num_tv = (TextView) v.findViewById(R.id.tv_numero);
        CheckBox valid_cb = (CheckBox) v.findViewById(R.id.cb_valid);
        ImageButton imgBtn = (ImageButton) v.findViewById(R.id.deletCont);
        //SET
        valid_cb.setChecked(contactList.get(position).getValid());
        nom_tv.setText(contactList.get(position).getNom());
        num_tv.setText(contactList.get(position).getNumero());

        // Valider un contact pour envoyer le sms
        valid_cb.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                contactList.get(position).setValid(checked);
                contactList.get(position).save();
            }
        });
        // Suppression d'un contact
        imgBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Suppression");
                builder.setMessage("Etes vous sur de vouloir supprimer le contact " + contactList.get(position).getNom() + " ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contactList.get(position).delete();
                        contactList.get(position).save();
                        Intent secondeActivite = new Intent(mContext, ContactActivity.class);
                        mContext.startActivity(secondeActivite);
                    }
                });
                builder.setNegativeButton("Non", null);
                builder.show();
            }
        });

        v.setTag(contactList.get(position).getId());

        return v;
    }
}
