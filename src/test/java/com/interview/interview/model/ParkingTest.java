package com.interview.interview.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTest {

  @Test
  public void testCreation() {

    Parking parking = new Parking(1L, "parking 1", "21 Test S.t.", "0Lat, 0Long");

    assertAll(
        "parking",
        () -> assertEquals(parking.getId(), 1L),
        () -> assertEquals(parking.getName(), "parking 1"),
        () -> assertEquals(parking.getAddress(), "21 Test S.t."),
        () -> assertEquals(parking.getCoordinates(), "0Lat, 0Long"),
        () -> assertNull(parking.getSpots()));
  }

}
