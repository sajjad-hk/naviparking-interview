package com.interview.interview.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ParkingSpotJpaTest {

  @Autowired private TestEntityManager tem;

  @Test
  void testMapping_parkingSpot() {
    ParkingSpot parkingSpot = tem.persistAndFlush(new ParkingSpot());
    assertAll("parking spot", () -> assertNotNull(parkingSpot.getSpotId()));
  }
}
