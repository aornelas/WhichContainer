package com.andresornelas.whichcontainer.entities;

public class Pan {

  private Volume volume;
  private Brand brand;
  private boolean isContainer;

  public Pan(Volume volume, Brand brand, boolean isContainer) {
    this.volume = volume;
    this.brand = brand;
    this.isContainer = isContainer;
  }

  @Override
  public String toString() {
    return volume.toString() + " " + brand;
  }

  public double getCapacity() {
    return volume.getAmount();
  }

  public Unit getUnit() {
    return volume.getUnit();
  }

  public Brand getBrand() {
    return brand;
  }

  public boolean isContainer() {
    return this.isContainer;
  }

  public int percentFull(int milliliters) {
    int percent = milliliters * 100 / volume.getCapacity();
    return percent > 100 ? percent * -1 : percent;
  }
}
