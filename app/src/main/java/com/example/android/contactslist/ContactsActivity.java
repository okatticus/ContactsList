package com.example.android.contactslist;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

/**
 * Created by Apoorva on 6/27/2017.
 */

public class ContactsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView contactView = (TextView) findViewById(R.id.contactView);
        Cursor cursor = getContacts();

        while(cursor.moveToNext()){
            String displayName = cursor.
                    getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            contactView.append("Contact: ");
            contactView.append(displayName);
            contactView.append("\n");
        }
    }
    private Cursor getContacts(){
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] { ContactsContract.Contacts._ID,
        ContactsContract.Contacts.DISPLAY_NAME};
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP +
                "'" + ("1") + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;
        return managedQuery(uri, projection, selection, selectionArgs,
                sortOrder);
    }
}
