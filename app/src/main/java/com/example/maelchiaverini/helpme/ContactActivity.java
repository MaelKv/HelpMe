package com.example.maelchiaverini.helpme;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "test = "+view.getTag(),
                        Toast.LENGTH_SHORT).show();
            }
        });

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
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data)
    {
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

            Contact contact = new Contact(contactName, contactNumber);
            contact.save();

            Intent returnConfig = new Intent(ContactActivity.this, ContactActivity.class);
            startActivity(returnConfig);
    }
}