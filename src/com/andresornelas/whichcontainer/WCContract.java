package com.andresornelas.whichcontainer;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class WCContract {
  private WCContract() {}

  public static final int VERSION = 1;
  public static final String AUTHORITY = "com.andresornelas.whichcontainer";

  public static final Uri BASE_URI = new Uri.Builder()
          .scheme(ContentResolver.SCHEME_CONTENT)
          .authority(AUTHORITY)
          .build();

  private static final String MINOR_TYPE = "/vnd." + AUTHORITY;
  public static final String ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + MINOR_TYPE;
  public static final String DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + MINOR_TYPE;

  public static class Pans {
    private Pans() {}

    public static final String TABLE = "pans";
    public static final Uri URI = BASE_URI.buildUpon().appendPath(TABLE).build();

    public static class Columns {
      public static final String ID = BaseColumns._ID;
      public static final String BRAND = "brand";
      public static final String AMOUNT = "amount";
      public static final String CAPACITY = "capacity";
      public static final String UNIT = "unit";
      public static final String IS_CONTAINTER = "is_container";
    }
  }
}
