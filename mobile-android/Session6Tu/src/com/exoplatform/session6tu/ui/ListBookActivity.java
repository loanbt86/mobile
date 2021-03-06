/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.exoplatform.session6tu.ui;

import com.exoplatform.session6tu.BookAdapter;
import com.exoplatform.session6tu.R;
import com.exoplatform.session6tu.datasource.BookDAO;
import com.exoplatform.session6tu.domain.Book;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * screen to list all books 
 * 
 * @author Created by The eXo Platform SAS
 * <br/>Anh-Tu Nguyen
 * <br/><a href="mailto:tuna@exoplatform.com">tuna@exoplatform.com<a/>
 * <br/>Nov 20, 2012  
 */
public class ListBookActivity extends ListActivity
{
  private static final String TAG = "ListBookActivity";
  
  public static final String BOOK_NAME = "bookName";
  
  public static final String BOOK_ID   = "bookId";
  
  private BookDAO mBookDAO;
  
  @Override
  public void onCreate(Bundle savedInstance)
  {
    super.onCreate(savedInstance);
    mBookDAO = new BookDAO(this);
    setListAdapter(new BookAdapter(this, mBookDAO.getAllBooks()));
    
    /* if no book found, display a message */
    if (mBookDAO.getAllBooks().size() == 0) {
      Toast toast = Toast.makeText(this, "add a book first", Toast.LENGTH_SHORT);
      toast.show();
    }
  }
  
  /* override this method to display option menu */
  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.menu, menu); /* inflate menu from xml file into menu of this activity */
    menu.findItem(R.id.editBook).setEnabled(false); /* do not allow to edit book */
    return true;
  }
  
  /* handle user click on action menu */
  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem)
  {
    switch(menuItem.getItemId()) 
    {
      case android.R.id.home: /* user clicks on Home icon of action bar */ 
        /* do not do anything since this is home already */ 
        break;
      case R.id.searchBook:
        break;
      case R.id.addBook:
        Intent addBook = new Intent();
        addBook.setAction("com.exoplatform.intent.addBook");
        startActivity(addBook);
        break;
      default: 
        break;
    }
    return true;
  }
  
  @Override
  public void onListItemClick(ListView listView, View view, int position, long id)
  {
    super.onListItemClick(listView, view, position, id);
    Object o = getListAdapter().getItem(position);
    Intent showBookDetail = new Intent();
    showBookDetail.setAction("com.exoplatform.intent.showBookDetail");
    showBookDetail.putExtra(BOOK_NAME, ((Book)o).getName() );
    showBookDetail.putExtra(BOOK_ID, ((Book)o ).getId());
    startActivity(showBookDetail);
  }
  
  @Override
  public void onNewIntent(Intent intent)
  {
    Log.i(TAG, "receives intent: " + intent.getAction());
    /* update ui with new list of books */
    setListAdapter(new BookAdapter(this, mBookDAO.getAllBooks()));
  }
}
