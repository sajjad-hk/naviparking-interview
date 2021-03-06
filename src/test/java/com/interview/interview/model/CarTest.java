package com.interview.interview.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

  @Test
  void testCreation() {
    Car car = new Car("TST 123 PL", "Mars", "DE");

    assertAll(
        "user car",
        () -> assertEquals(car.getPlateNumber(), "TST 123 PL"),
        () -> assertEquals(car.getModel(), "Mars"),
        () -> assertEquals(car.getMake(), "DE"));
  }
}
