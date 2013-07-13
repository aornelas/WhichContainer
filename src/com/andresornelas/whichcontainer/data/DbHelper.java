package com.andresornelas.whichcontainer.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

  private static final int VERSION = 1;
  private static final String DB_NAME = "whichcontainer.db";

  static final String TABLE_PANS = "pans";
  static final String COL_ID = "id";
  static final String COL_BRAND = "brand";
  static final String COL_AMOUNT = "amount";
  static final String COL_CAPACITY = "capacity";
  static final String COL_UNIT = "unit";
  static final String COL_IS_CONTAINER = "is_container";

  public DbHelper(Context context) {
    super(context, DB_NAME, null, VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_PANS + "(" +
            COL_ID + " INTEGER PRIMARY KEY, " +
            COL_BRAND + " TEXT, " +
            COL_CAPACITY + " REAL, " +
            COL_UNIT + " TEXT," +
            COL_AMOUNT + " INTEGER, " +
            COL_IS_CONTAINER + " INTEGER " +
            ")");
  }

  @Override
  public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    // TODO: Handle upgrading
  }

}
