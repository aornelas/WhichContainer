package com.andresornelas.whichcontainer;

import static com.andresornelas.whichcontainer.entities.Brand.CUISINART;
import static com.andresornelas.whichcontainer.entities.Brand.LE_CREUSET;
import static com.andresornelas.whichcontainer.entities.Unit.IN;
import static com.andresornelas.whichcontainer.entities.Unit.QT;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andresornelas.whichcontainer.entities.Pan;
import com.andresornelas.whichcontainer.entities.Volume;

public class SelectionActivity extends Activity {

  private static final Pan[] availablePans = new Pan[] {
                                           new Pan(new Volume(8, QT), CUISINART),
                                           new Pan(new Volume(12, QT), LE_CREUSET),
                                           new Pan(new Volume(10, IN), CUISINART)
                                           };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_selection);

    ArrayAdapter<Pan> adapter = new ArrayAdapter<Pan>(this, R.layout.pan_list_item,
            availablePans);

    ListView panList = (ListView) findViewById(R.id.pan_list);
    panList.setAdapter(adapter);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.selection, menu);
    return true;
  }

}
