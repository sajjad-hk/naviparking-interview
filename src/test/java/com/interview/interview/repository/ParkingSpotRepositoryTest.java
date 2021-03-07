package com.interview.interview.repository;

import com.interview.interview.model.ParkingSpot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ParkingSpotRepositoryTest {

  @Autowired private ParkingSpotRepository repository;

  @Test
  void testCreation_parkingSpot() {
    ParkingSpot parkingSpot = new ParkingSpot(1);
    repository.save(parkingSpot);

    ParkingSpot persistedParkingSpot = repository.findParkingSpotBySpotNumber(1);
    assertAll(
        "parking spot",
        () -> assertNotNull(persistedParkingSpot.getSpotId()),
        () -> assertEquals(persistedParkingSpot.getSpotNumber(), 1));
  }
}
