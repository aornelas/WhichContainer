package com.andresornelas.whichcontainer.entities;

public class Pan {

  private Volume volume;
  private Brand  brand;

  public Pan(Volume volume, Brand brand) {
    this.volume = volume;
    this.brand = brand;
  }

  @Override
  public String toString() {
    return volume.toString() + " " + brand;
  }
}
