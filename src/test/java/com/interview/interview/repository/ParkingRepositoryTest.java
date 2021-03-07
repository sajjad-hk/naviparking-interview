package com.interview.interview.repository;

import com.interview.interview.model.Parking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ParkingRepositoryTest {

  @Autowired private ParkingRepository repository;

  @Test
  void testCreation_parkingWithoutSpot() {
    Parking parking = new Parking("parking 1", "21 Test S.t.", "0Lat, 0Long");
    Parking saved = repository.save(parking);

    Parking persistedParking =
        repository
            .findById(saved.getParkingId())
            .orElseThrow(
                () ->
                    new RuntimeException("parking with id" + saved.getParkingId() + "not found!"));
    assertAll(
        "parking",
        () -> assertNotNull(persistedParking.getParkingId()),
        () -> assertEquals(persistedParking.getName(), "parking 1"),
        () -> assertEquals(persistedParking.getAddress(), "21 Test S.t."),
        () -> assertEquals(persistedParking.getCoordinates(), "0Lat, 0Long"),
        () -> assertTrue(persistedParking.getSpots().isEmpty()));
  }
}
