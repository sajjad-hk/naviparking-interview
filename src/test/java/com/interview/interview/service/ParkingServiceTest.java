package com.interview.interview.service;

import com.interview.interview.model.Parking;
import com.interview.interview.repository.ParkingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ParkingServiceTest {

  @Autowired private ParkingService parkingService;
  @Autowired private ParkingRepository parkingRepository;

  @Test
  void addParking_withoutAnySpot() {
    Parking parking =
        parkingService.addParking(new Parking("parking 1", "21 Test S.t.", "0Lat, 0Long"));
    Parking savedParking = parkingService.findParkingById(parking.getParkingId());

    assertAll(
        "parking",
        () -> assertEquals(savedParking.getParkingId(), parking.getParkingId()),
        () -> assertEquals(savedParking.getName(), "parking 1"),
        () -> assertEquals(savedParking.getAddress(), "21 Test S.t."),
        () -> assertEquals(savedParking.getCoordinates(), "0Lat, 0Long"),
        () -> assertTrue(savedParking.getSpots().isEmpty()));
  }

  @Test
  void addParking_withSpots() {
    Parking parking1 =
        parkingService.addParking(new Parking("parking 1", "21 Test S.t.", "0Lat, 0Long"));
    Parking parking2 = parkingService.addSpotToParking(parking1.getParkingId());

    assertAll(
        "parking",
        () -> assertEquals(parking2.getParkingId(), parking1.getParkingId()),
        () -> assertEquals(parking2.getName(), "parking 1"),
        () -> assertEquals(parking2.getAddress(), "21 Test S.t."),
        () -> assertEquals(parking2.getCoordinates(), "0Lat, 0Long"),
        () -> assertFalse(parking2.getSpots().isEmpty()));
  }
}
