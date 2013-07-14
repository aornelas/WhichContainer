package com.andresornelas.whichcontainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andresornelas.whichcontainer.entities.Volume;
import com.andresornelas.whichcontainer.svc.WCService;

public class ResultsActivity extends Activity {

  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.activity_results);

    if (state == null) state = getIntent().getExtras();

    if (state != null) {
      TextView bestContainer = (TextView) findViewById(R.id.best_container_label);
      bestContainer.setText(Volume.cleanAmount(
              state.getDouble(WCContract.Pans.Columns.CAPACITY) + "") + " " +
              state.getString(WCContract.Pans.Columns.UNIT) + " " +
              state.getString(WCContract.Pans.Columns.BRAND));
      TextView percentFull = (TextView) findViewById(R.id.percentage_full_label);
      percentFull.setText(state.getInt(WCService.PARAM_PERCENT_FULL) + "% full");
    }

    Button resetButton = (Button) findViewById(R.id.reset_button);
    resetButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        reset();
      }
    });
  }

  protected void reset() {
    Intent i = new Intent(this, SelectionActivity.class);
    startActivity(i);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.results, menu);
    return true;
  }

}
