package com.andresornelas.whichcontainer;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SelectionActivity extends ListActivity implements LoaderCallbacks<Cursor> {
  private static final String LOG_TAG = "WCSelectionActivity";

  private static final String[] PROJECTION = {
      WCContract.Pans.Columns.ID,
      WCContract.Pans.Columns.CAPACITY,
      WCContract.Pans.Columns.UNIT,
      WCContract.Pans.Columns.BRAND
  };

  private static final String[] FROM = new String[PROJECTION.length - 1];
  static {
    System.arraycopy(PROJECTION, 1, FROM, 0, FROM.length);
  }

  private static final int LOADER_ID = 29;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.d(LOG_TAG, "onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_selection);

    SimpleCursorAdapter adapter =
      new SimpleCursorAdapter(this, R.layout.pan_list_item, null, FROM,
              new int[] { R.id.pan_list_item }, 0);
    adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
      @Override
      public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
        String capacity = cursor.getDouble(cursor.getColumnIndex(WCContract.Pans.Columns.CAPACITY))
                + "";
        if (capacity.endsWith(".0")) capacity = capacity.substring(0, capacity.length() - 2);
        String unit = cursor.getString(cursor.getColumnIndex(WCContract.Pans.Columns.UNIT));
        String brand = cursor.getString(cursor.getColumnIndex(WCContract.Pans.Columns.BRAND));
        ((TextView) view).setText(capacity + " " + unit + " " + brand);
        return true;
      }
    });

    setListAdapter(adapter);

    getLoaderManager().initLoader(LOADER_ID, null, this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.selection, menu);
    return true;
  }

  @Override
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    Log.d(LOG_TAG, "onCreateLoader");
    return new CursorLoader(this, WCContract.Pans.URI, PROJECTION, "IS_CONTAINER = 0", null, null);
  }

  @Override
  public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
    Log.d(LOG_TAG, "onLoadFinished");
    ((SimpleCursorAdapter) getListAdapter()).swapCursor(cursor);
  }

  @Override
  public void onLoaderReset(Loader<Cursor> loader) {
    ((SimpleCursorAdapter) getListAdapter()).swapCursor(null);
  }

}
