package com.andresornelas.whichcontainer.entities;

import android.annotation.SuppressLint;

public enum Brand {
  CUISINART,
  LE_CREUSET,
  MULLINIX,
  PYREX,
  REYNOLDS,
  RUBBERMAID;

  @SuppressLint("DefaultLocale")
  @Override
  public String toString() {
    return super.toString().toLowerCase().replace('_', ' ');
  }
}
