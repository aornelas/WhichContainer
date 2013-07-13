package com.andresornelas.whichcontainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.andresornelas.whichcontainer.entities.Volume;

public class EstimationActivity extends Activity {

  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.activity_estimation);

    if (state == null) state = getIntent().getExtras();

    if (state != null) {
      TextView title = (TextView) findViewById(R.id.selected_pan);
      title.setText(Volume.cleanAmount(
              state.getDouble(WCContract.Pans.Columns.CAPACITY) + "") + " " +
              state.getString(WCContract.Pans.Columns.UNIT) + " " +
              state.getString(WCContract.Pans.Columns.BRAND));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.estimation, menu);
    return true;
  }

}
