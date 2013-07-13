package com.andresornelas.whichcontainer;

import static com.andresornelas.whichcontainer.entities.Brand.CUISINART;
import static com.andresornelas.whichcontainer.entities.Brand.LE_CREUSET;
import static com.andresornelas.whichcontainer.entities.Brand.PYREX;
import static com.andresornelas.whichcontainer.entities.Unit.CUP;
import static com.andresornelas.whichcontainer.entities.Unit.IN;
import static com.andresornelas.whichcontainer.entities.Unit.QT;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.andresornelas.whichcontainer.entities.Pan;
import com.andresornelas.whichcontainer.entities.Volume;
import com.andresornelas.whichcontainer.svc.WCService;

public class WhichContainerApp extends Application {
  private static final String LOG_TAG = "WCApp";

  private static final Pan[] availablePans = new Pan[] {
      new Pan(new Volume(8, QT), CUISINART, false),
      new Pan(new Volume(12, QT), LE_CREUSET, false),
      new Pan(new Volume(10, IN), CUISINART, false),
      new Pan(new Volume(4, CUP), PYREX, true)
  };

  @Override
  public void onCreate() {
    Log.d(LOG_TAG, "onCreate");
    super.onCreate();
    addSamplePans();
  }

  private void addSamplePans() {
    for (Pan pan : availablePans) {
      Intent i = new Intent(this, WCService.class);
      i.putExtra(WCService.PARAM_OP, WCService.OP_ADD_PAN);
      i.putExtra(WCContract.Pans.Columns.CAPACITY, pan.getCapacity());
      i.putExtra(WCContract.Pans.Columns.UNIT, pan.getUnit().toString());
      i.putExtra(WCContract.Pans.Columns.BRAND, pan.getBrand().toString());
      i.putExtra(WCContract.Pans.Columns.IS_CONTAINTER, pan.isContainer() ? 1 : 0);
      i.putExtra(WCContract.Pans.Columns.AMOUNT, 1);
      startService(i);
    }
  }

}
