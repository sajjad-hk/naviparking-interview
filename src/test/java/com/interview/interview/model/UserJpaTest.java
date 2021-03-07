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
public class UserJpaTest {
  @Autowired private TestEntityManager tem;

  @Test
  void testMapping_userWithOutCar() {
    User notPersistedUser = new User(null, "John", "Due", "john.due@tst.com", "password 123");

    User user = tem.persistAndFlush(notPersistedUser);

    assertAll(
        "user",
        () -> assertNotNull(user.getUserId()),
        () -> assertEquals(user.getFirstName(), "John"),
        () -> assertEquals(user.getLastName(), "Due"),
        () -> assertEquals(user.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(user.getPassword(), "password 123"),
        () -> assertNull(user.getCar()),
        () -> assertTrue(user.getAssignedParking().isEmpty()));
  }

  @Test
  void testMapping_userWithCar_withoutParking() {
    Car car = tem.persistAndFlush(new Car("TST 123 PL", "Mars", "DE"));
    User notPersistedUser = new User(null, "John", "Due", "john.due@tst.com", "password 123");

    notPersistedUser.setCar(car);

    User user = tem.persistAndFlush(notPersistedUser);

    assertAll(
        "user",
        () -> assertNotNull(user.getUserId()),
        () -> assertEquals(user.getFirstName(), "John"),
        () -> assertEquals(user.getLastName(), "Due"),
        () -> assertEquals(user.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(user.getPassword(), "password 123"),
        () -> assertNotNull(user.getCar()),
        () -> assertEquals(user.getCar().getPlateNumber(), "TST 123 PL"),
        () -> assertTrue(user.getAssignedParking().isEmpty()));
  }

  @Test
  void testMapping_userWithCar_withParking() {


    Car car = tem.persistAndFlush(new Car("TST 123 PL", "Mars", "DE"));

    ParkingSpot parking1SpotOne = tem.persistAndFlush(new ParkingSpot(null, 1));
    ParkingSpot parking1SpotTwo = tem.persistAndFlush(new ParkingSpot(null, 2));
    Parking notPersistedParking1 = new Parking(null, "parking 1", "21 Test S.t.", "0Lat, 0Long");
    notPersistedParking1.setSpots(Set.of(parking1SpotOne, parking1SpotTwo));
    Parking parking1 = tem.persistAndFlush(notPersistedParking1);

    ParkingSpot parking2SpotOne = tem.persistAndFlush(new ParkingSpot(null, 1));
    ParkingSpot parking2SpotTwo = tem.persistAndFlush(new ParkingSpot(null, 2));
    ParkingSpot parking2SpotThree = tem.persistAndFlush(new ParkingSpot(null, 3));
    Parking notPersistedParking2 = new Parking(null, "parking 1", "21 Test S.t.", "0Lat, 0Long");
    notPersistedParking2.setSpots(Set.of(parking2SpotOne, parking2SpotTwo, parking2SpotThree));
    Parking parking2 = tem.persistAndFlush(notPersistedParking2);

    User notPersistedUser = new User(null, "John", "Due", "john.due@tst.com", "password 123");

    notPersistedUser.setCar(car);
    notPersistedUser.setAssignedParking(Set.of(parking1, parking2));
    User user = tem.persistAndFlush(notPersistedUser);

    assertAll(
        "user",
        () -> assertNotNull(user.getUserId()),
        () -> assertEquals(user.getFirstName(), "John"),
        () -> assertEquals(user.getLastName(), "Due"),
        () -> assertEquals(user.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(user.getPassword(), "password 123"),
        () -> assertNotNull(user.getCar()),
        () -> assertEquals(user.getCar().getPlateNumber(), "TST 123 PL"),
        () -> assertFalse(user.getAssignedParking().isEmpty()));
  }
}
