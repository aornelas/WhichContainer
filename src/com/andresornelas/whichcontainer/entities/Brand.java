package com.andresornelas.whichcontainer.entities;

import android.annotation.SuppressLint;

public enum Brand {
  LE_CREUSET, CUISINART, PYREX;

  @SuppressLint("DefaultLocale")
  @Override
  public String toString() {
    return super.toString().toLowerCase().replace('_', ' ');
  }
}
