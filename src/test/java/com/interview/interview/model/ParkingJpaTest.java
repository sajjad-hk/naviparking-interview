package com.interview.interview.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ParkingJpaTest {

  @Autowired private TestEntityManager tem;

  @Test
  void testMapping_withNoSpots() {
    Parking parking = tem.persistAndFlush(new Parking("parking 1", "21 Test S.t.", "0Lat, 0Long"));
    assertAll(
        "parking",
        () -> assertNotNull(parking.getParkingId()),
        () -> assertEquals(parking.getName(), "parking 1"),
        () -> assertEquals(parking.getAddress(), "21 Test S.t."),
        () -> assertEquals(parking.getCoordinates(), "0Lat, 0Long"),
        () -> assertTrue(parking.getSpots().isEmpty()));
  }

  @Test
  void testMapping_withTwoSpots() {

    ParkingSpot parkingSpotOne = tem.persistAndFlush(new ParkingSpot());
    ParkingSpot parkingSpotTwo = tem.persistAndFlush(new ParkingSpot());
    Parking notPersistedParking = new Parking("parking 1", "21 Test S.t.", "0Lat, 0Long");
    notPersistedParking.setSpots(Set.of(parkingSpotOne, parkingSpotTwo));
    Parking parking = tem.persistAndFlush(notPersistedParking);
    assertAll(
        "parking",
        () -> assertNotNull(parking.getParkingId()),
        () -> assertEquals(parking.getName(), "parking 1"),
        () -> assertEquals(parking.getAddress(), "21 Test S.t."),
        () -> assertEquals(parking.getCoordinates(), "0Lat, 0Long"),
        () -> assertFalse(parking.getSpots().isEmpty()),
        () -> assertEquals(parking.getSpots().size(), 2));
  }
}
