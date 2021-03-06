package com.example.com.exoplatform.session5tuan.contentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Organization;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private LinearLayout layout_ ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout_ = new LinearLayout(this);
        
        setContentView(layout_);
        
        TextView contactView = new TextView(this) ;
        //layout_ = (LinearLayout)findViewById(R.id.layour_id) ;
        
        		
        Cursor cursor = getContacts();
         
         
        while (cursor.moveToNext()) {

          String displayName = cursor.getString(cursor
              .getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
          
          String contactId = cursor.getString(cursor
                  .getColumnIndex(ContactsContract.Contacts._ID));
          
          Cursor orgCur = getCompanyOf(contactId) ;
          String orgName = "" ;
          String title = "";
          while(orgCur.moveToNext()) {
          orgName = orgCur.getString(orgCur.getColumnIndex(Organization.COMPANY)); 
  		  title = orgCur.getString(orgCur.getColumnIndex(Organization.TITLE));
          }
          orgCur.close() ;
          
          
          contactView.append("Name: ");
          contactView.append(displayName);
          contactView.append("\n");
          contactView.append(orgName) ;
          contactView.append("\n");
          contactView.append(title) ;
          contactView.append("\n");
          
          
        }
        
        layout_.addView(contactView) ;
    }

    
    private Cursor getContacts() {
        // Run query
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] { ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME};
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
            + ("1") + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
            + " COLLATE LOCALIZED ASC";

        
        return managedQuery(uri, projection, selection, selectionArgs,
            sortOrder);
      }
    
    private Cursor getCompanyOf(String contactId) {
    	
    	return getContentResolver().query(
    			Data.CONTENT_URI,                         // URI
    	        new String[] {Organization.COMPANY, Organization.TITLE},
    	        Data.CONTACT_ID + "=?",                   // Selection
    	        new String[] {String.valueOf(contactId)}, // Selection arguments
    	        null);
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
