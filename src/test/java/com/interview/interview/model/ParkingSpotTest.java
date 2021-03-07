package com.interview.interview.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotTest {

  @Test
  public void testCreation() {
    ParkingSpot parkingSpot = new ParkingSpot(1);

    assertAll("parking spot", () -> assertEquals(parkingSpot.getSpotNumber(), 1));
  }
}
