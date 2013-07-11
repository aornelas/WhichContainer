package com.andresornelas.whichcontainer.entities;

public class Volume {

  private double amount;
  private Unit   unit;

  public Volume(double amount, Unit unit) {
    this.amount = amount;
    this.unit = unit;
  }

  @Override
  public String toString() {
    return amount + " " + unit;
  }

}
