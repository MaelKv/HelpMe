package com.example.maelchiaverini.helpme.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.maelchiaverini.helpme.Classes.Contact;
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
        //TextView
        TextView nom_tv = (TextView) v.findViewById(R.id.tv_nom);
        TextView num_tv = (TextView) v.findViewById(R.id.tv_numero);
        CheckBox valid_cb = (CheckBox) v.findViewById(R.id.cb_valid);
        //SET
        valid_cb.setChecked(contactList.get(position).getValid());
        nom_tv.setText(contactList.get(position).getNom());
        num_tv.setText(contactList.get(position).getNumero());
        //Click checkbox
        valid_cb.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                contactList.get(position).setValid(checked);
                contactList.get(position).save();
            }
        });

        v.setTag(contactList.get(position).getId());

        return v;
    }
}
