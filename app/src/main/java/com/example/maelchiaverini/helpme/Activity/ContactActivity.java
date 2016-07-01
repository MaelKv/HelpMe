package com.example.maelchiaverini.helpme.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maelchiaverini.helpme.Classes.Contact;
import com.example.maelchiaverini.helpme.Adapter.ContactAdaptater;
import com.example.maelchiaverini.helpme.R;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private ListView contactListView;
    private ContactAdaptater adaptater;
    private List<Contact> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        contactListView = (ListView) findViewById(R.id.contact_list);
        contactList = new ArrayList<>();

        contactList = Contact.listAll(Contact.class);

        adaptater = new ContactAdaptater(getApplicationContext(), contactList);
        contactListView.setAdapter(adaptater);

        ImageButton btn_cont = (ImageButton) findViewById(R.id.imageButton);
        btn_cont.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(pickContact, 1);
            }
        });

        ImageButton btn_ret = (ImageButton) findViewById(R.id.imgbtnReturn);
        btn_ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnConfig = new Intent(ContactActivity.this, ConfigActivity.class);
                startActivity(returnConfig);
            }
        });

        ImageButton imgBtn = (ImageButton) findViewById(R.id.deletCont);
        assert imgBtn != null;
        imgBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Suppression");
                builder.setMessage("Etes vous sur de vouloir supprimer les contacts ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Contact.deleteAll(Contact.class);
                        Intent secondeActivite = new Intent(ContactActivity.this, ContactActivity.class);
                        startActivity(secondeActivite);
                    }
                });
                builder.setNegativeButton("Non", null);
                builder.show();
            }
        });
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data)
    {
        if (data == null) {
            Toast.makeText(ContactActivity.this, "Aucun contact selectionné", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            String contactName = null;
            String contactNumber = null;
            String contactId = null;
            Uri contactData = data.getData();
            Cursor c = getContentResolver().query(contactData, null, null, null, null);

            c.moveToFirst();
            int column1 = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int column2 = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            contactName = c.getString(column2);
            contactNumber = c.getString(column1);

            if (contactName != null) {
                Contact contact = new Contact(contactName, contactNumber);
                if(Contact.listAll(Contact.class).contains(contact) == false) {
                    contact.save();
                }
                else{Toast.makeText(ContactActivity.this, "Le contact est deja enregistré", Toast.LENGTH_SHORT).show();}
            }
            Intent returnConfig = new Intent(ContactActivity.this, ContactActivity.class);
            startActivity(returnConfig);
        }
    }
}