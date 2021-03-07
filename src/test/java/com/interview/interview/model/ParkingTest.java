package com.interview.interview.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTest {

  @Test
  public void testCreation() {

    Parking parking = new Parking("parking 1", "21 Test S.t.", "0Lat, 0Long");

    assertAll(
        "parking",
        () -> assertEquals(parking.getName(), "parking 1"),
        () -> assertEquals(parking.getAddress(), "21 Test S.t."),
        () -> assertEquals(parking.getCoordinates(), "0Lat, 0Long"),
        () -> assertTrue(parking.getSpots().isEmpty()));
  }
}
