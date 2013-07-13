package com.andresornelas.whichcontainer;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;

import com.andresornelas.whichcontainer.entities.Pan;

public class SelectionActivity extends ListActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_selection);

    setListAdapter(new ArrayAdapter<Pan>(this, R.layout.pan_list_item, new Pan[0]));

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.selection, menu);
    return true;
  }

}
