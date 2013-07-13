package com.andresornelas.whichcontainer.entities;

public class Volume {

  private double amount;
  private Unit unit;

  public Volume(double amount, Unit unit) {
    this.amount = amount;
    this.unit = unit;
  }

  @Override
  public String toString() {
    return amount + " " + unit;
  }

  public double getAmount() {
    return amount;
  }

  public Unit getUnit() {
    return unit;
  }

  public static String cleanAmount(String amount) {
    if (amount.endsWith(".0")) amount = amount.substring(0, amount.length() - 2);
    return amount;
  }

}
