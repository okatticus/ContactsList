package com.example.android.contactslist;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)(findViewById(R.id.contactView));
        Cursor contactCursor = getContacts();
        while(contactCursor.moveToNext()){
            String displayName = contactCursor.getString(contactCursor.getColumnIndex
                    (ContactsContract.Data.DISPLAY_NAME));
            textView.append("Name: ");
            textView.append(displayName);
            textView.append("\n");
        }
    }
    public Cursor getContacts(){
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME};
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP;
        String order =ContactsContract.Contacts.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC";
        return managedQuery(uri,projection,selection,null,order);
    }
}
