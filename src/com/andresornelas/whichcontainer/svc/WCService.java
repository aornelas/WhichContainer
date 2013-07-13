package com.andresornelas.whichcontainer.svc;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;

import com.andresornelas.whichcontainer.WCContract;

public class WCService extends IntentService {
  private static final String LOG_TAG = WCService.class.getSimpleName();

  public static final String PARAM_OP = "WhichContainter.OP";

  public static final int OP_ADD_PAN = -1;

  public WCService() {
    super(LOG_TAG);
  }

  @Override
  protected void onHandleIntent(Intent i) {
    int op = i.getIntExtra(PARAM_OP, 0);
    switch (op) {
      case OP_ADD_PAN:
        addPan(i);
        break;
      default:
        throw new UnsupportedOperationException("Unrecognized operation: " + op);
    }
  }

  private void addPan(Intent i) {
    Log.d(LOG_TAG, "Adding pan");
    ContentValues pan = new ContentValues();
    pan.put(WCContract.Pans.Columns.BRAND, i.getStringExtra(WCContract.Pans.Columns.BRAND));
    pan.put(WCContract.Pans.Columns.CAPACITY, i.getDoubleExtra(WCContract.Pans.Columns.CAPACITY, 0));
    pan.put(WCContract.Pans.Columns.UNIT, i.getStringExtra(WCContract.Pans.Columns.UNIT));
    pan.put(WCContract.Pans.Columns.AMOUNT, i.getIntExtra(WCContract.Pans.Columns.AMOUNT, 0));
    pan.put(WCContract.Pans.Columns.IS_CONTAINTER,
            i.getIntExtra(WCContract.Pans.Columns.IS_CONTAINTER, 0));
    getContentResolver().insert(WCContract.Pans.URI, pan);
  }
}
