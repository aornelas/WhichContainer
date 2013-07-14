package com.andresornelas.whichcontainer.entities;

import android.annotation.SuppressLint;

public enum Unit {
  QT(946), OZ(30), IN(100), CUP(250), ML(1);

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
