package com.andresornelas.whichcontainer.entities;

import android.annotation.SuppressLint;

public enum Unit {
  CUP(250),
  IN(100),
  ML(1),
  OZ(30),
  QT(946);

  private int inML;

  private Unit(int inMilliliters) {
    inML = inMilliliters;
  }

  public int toML(double amount) {
    return Double.valueOf(inML * amount).intValue();
  }

  @SuppressLint("DefaultLocale")
  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}
