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
package com.exoplatform.session3tu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Represent the Home screen which display a message to user
 * 
 * Created by The eXo Platform SAS
 * <br/>Author : Anh-Tu Nguyen
 * <br/><a href="mailto:tuna@exoplatform.com">tuna@exoplatform.com</a>
 * <br/>Nov 12, 2012  
 */
public class HomeActivity extends Activity
{
  @Override
  public void onCreate(Bundle bundle)
  {
    super.onCreate(bundle);
    setContentView(R.layout.homeactivity);
    TextView helloText = (TextView) findViewById(R.id.helloText);
    helloText.setText(getResources().getString(R.string.home_message) + " " + getIntent().getCharSequenceExtra("username").toString());
  }
}
