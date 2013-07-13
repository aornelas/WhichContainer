package com.andresornelas.whichcontainer.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.andresornelas.whichcontainer.WCContract;

public class WCProvider extends ContentProvider {

  private static final int PANS_ITEM_TYPE = 1;
  private static final int PANS_DIR_TYPE = 2;
  private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
  static {
    MATCHER.addURI(WCContract.AUTHORITY, WCContract.Pans.TABLE + "/#",
            PANS_ITEM_TYPE);
    MATCHER.addURI(WCContract.AUTHORITY, WCContract.Pans.TABLE,
            PANS_DIR_TYPE);
  }

  private static final ColumnMap COL_MAP_PANS = new ColumnMap.Builder()
          .addColumn(WCContract.Pans.Columns.ID, DbHelper.COL_ID, ColumnMap.Type.LONG)
          .addColumn(WCContract.Pans.Columns.BRAND, DbHelper.COL_BRAND, ColumnMap.Type.STRING)
          .addColumn(WCContract.Pans.Columns.CAPACITY, DbHelper.COL_CAPACITY, ColumnMap.Type.DOUBLE)
          .addColumn(WCContract.Pans.Columns.UNIT, DbHelper.COL_UNIT, ColumnMap.Type.STRING)
          .addColumn(WCContract.Pans.Columns.AMOUNT, DbHelper.COL_AMOUNT, ColumnMap.Type.INTEGER)
          .addColumn(WCContract.Pans.Columns.IS_CONTAINTER, DbHelper.COL_IS_CONTAINER,
                  ColumnMap.Type.INTEGER)
          .build();

  private static final ProjectionMap PROJ_MAP_PANS = new ProjectionMap.Builder()
          .addColumn(WCContract.Pans.Columns.ID, DbHelper.COL_ID)
          .addColumn(WCContract.Pans.Columns.BRAND, DbHelper.COL_BRAND)
          .addColumn(WCContract.Pans.Columns.CAPACITY, DbHelper.COL_CAPACITY)
          .addColumn(WCContract.Pans.Columns.UNIT, DbHelper.COL_UNIT)
          .addColumn(WCContract.Pans.Columns.AMOUNT, DbHelper.COL_AMOUNT)
          .addColumn(WCContract.Pans.Columns.IS_CONTAINTER, DbHelper.COL_IS_CONTAINER)
          .build();

  private DbHelper dbHelper;

  @Override
  public boolean onCreate() {
    dbHelper = new DbHelper(getContext());
    return dbHelper != null;
  }

  @Override
  public String getType(Uri uri) {
    switch (MATCHER.match(uri)) {
      case PANS_ITEM_TYPE:
        return WCContract.ITEM_TYPE;
      case PANS_DIR_TYPE:
        return WCContract.DIR_TYPE;
      default:
        return null;
    }
  }

  @Override
  public Cursor query(Uri uri, String[] proj, String sel, String[] selArgs, String sort) {
    long pk = -1;
    switch (MATCHER.match(uri)) {
      case PANS_ITEM_TYPE:
        pk = ContentUris.parseId(uri);
        break;
      case PANS_DIR_TYPE:
        break;
      default:
        throw new IllegalArgumentException("Unrecognized URI: " + uri);
    }

    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
    qb.setTables(DbHelper.TABLE_PANS);
    qb.setProjectionMap(PROJ_MAP_PANS.getProjectionMap());

    // This is where we map virtual names to actual data names
    if (pk > 0) qb.appendWhere(DbHelper.COL_ID + "=" + pk);

    Cursor c = qb.query(dbHelper.getWritableDatabase(), proj, sel, selArgs, null, null, sort);
    c.setNotificationUri(getContext().getContentResolver(), uri);
    return c;
  }

  @Override
  public Uri insert(Uri uri, ContentValues pan) {
    switch (MATCHER.match(uri)) {
      case PANS_ITEM_TYPE:
        break;
      case PANS_DIR_TYPE:
        break;
      default:
        throw new IllegalArgumentException("Unrecognized URI: " + uri);
    }

    SQLiteDatabase db = dbHelper.getWritableDatabase();
    try {
      db.beginTransaction();
      db.insert(DbHelper.TABLE_PANS, null, COL_MAP_PANS.translateCols(pan));
      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }

    return uri;
  }

  @Override
  public int bulkInsert(Uri uri, ContentValues[] rows) {
    throw new UnsupportedOperationException("insert not supported");
  }

  @Override
  public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
    throw new UnsupportedOperationException("update not supported");
  }

  @Override
  public int delete(Uri arg0, String arg1, String[] arg2) {
    throw new UnsupportedOperationException("delete not supported");
  }

}
