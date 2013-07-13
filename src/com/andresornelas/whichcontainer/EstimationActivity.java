package com.andresornelas.whichcontainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.andresornelas.whichcontainer.entities.Volume;

public class EstimationActivity extends Activity {

  public static final String ESTIMATE = "EstimationActivity.Estimate";

  private TextView estimateLabel;
  private SeekBar estimateBar;

  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.activity_estimation);

    if (state == null) state = getIntent().getExtras();

    if (state != null) {
      TextView title = (TextView) findViewById(R.id.selected_pan_label);
      title.setText(Volume.cleanAmount(
              state.getDouble(WCContract.Pans.Columns.CAPACITY) + "") + " " +
              state.getString(WCContract.Pans.Columns.UNIT) + " " +
              state.getString(WCContract.Pans.Columns.BRAND));
    }

    estimateLabel = (TextView) findViewById(R.id.estimate_label);
    estimateBar = (SeekBar) findViewById(R.id.estimate_seekbar);
    estimateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {}

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {}

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        updateEstimateLabel(progress);
      }
    });

    Button okButton = (Button) findViewById(R.id.ok_button);
    okButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showResults();
      }
    });
  }

  protected void showResults() {
    Bundle state = getIntent().getExtras();
    Intent i = new Intent(this, ResultsActivity.class);
    i.putExtra(WCContract.Pans.Columns.CAPACITY, state.getDouble(WCContract.Pans.Columns.CAPACITY));
    i.putExtra(WCContract.Pans.Columns.UNIT, state.getDouble(WCContract.Pans.Columns.UNIT));
    i.putExtra(ESTIMATE, estimateBar.getProgress());
    startActivity(i);
  }

  protected void updateEstimateLabel(int value) {
    estimateLabel.setText(value + "%");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.estimation, menu);
    return true;
  }

}
