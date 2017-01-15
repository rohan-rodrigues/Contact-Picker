package com.example.rohanrodrigues.democontactpickerextrbasic;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import static android.R.attr.id;
import static android.R.attr.phoneNumber;

/**
 * Created by rohanrodrigues on 1/15/17.
 */

public class MainActivity extends ActionBarActivity {
    private final int PICK_CONTACT = 1;
    private HashMap<String, String> contactList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callContacts(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (reqCode == PICK_CONTACT) {
            if (resultCode == ActionBarActivity.RESULT_OK) {
                Uri contactData = data.getData();
                ContentResolver cr = getContentResolver();
                Cursor c = cr.query(contactData, null, null, null, null);

                if (c.moveToFirst()) {
                    String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

                    String isNumber = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    if (Integer.parseInt(isNumber) == 0) {
                        Toast.makeText(this, "You have chosen a contact without a phone number. Please choose another number", Toast.LENGTH_LONG).show();

                    }
                    else {

                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                     //   Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                     //           ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                      //  String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                 /*       Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Toast.makeText(this, "You've picked: " + name + " : Phone: " + contactId, Toast.LENGTH_LONG).show(); */


                    /*    Cursor cp = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);
                        if (cp != null && cp.moveToFirst()) {
                            String phone = cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            cp.close();
                        } */


                        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                     /*   Toast.makeText(getApplicationContext(), "-1", Toast.LENGTH_SHORT).show();
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                        Toast.makeText(getApplicationContext(), "Aa", Toast.LENGTH_SHORT).show();
                        phones.moveToFirst();
                        Toast.makeText(getApplicationContext(), "BB", Toast.LENGTH_SHORT).show();
                        String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Toast.makeText(getApplicationContext(), cNumber, Toast.LENGTH_SHORT).show();*/

                     //   Log.d(TAG, "Contact Phone Number: " + contactNumber);
                    }
                }
            }
        }
    }
}
