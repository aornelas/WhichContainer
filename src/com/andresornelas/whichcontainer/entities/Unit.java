package com.andresornelas.whichcontainer.entities;

import android.annotation.SuppressLint;

public enum Unit {
  QT, OZ, IN, CUP;

  @SuppressLint("DefaultLocale")
  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}
