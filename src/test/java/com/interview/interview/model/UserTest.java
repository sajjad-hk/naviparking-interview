package com.interview.interview.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  void testCreation_userWithOutCar() {
    User user = new User("John", "Due", "john.due@tst.com", "password 123");

    assertAll(
        "user",
        () -> assertEquals(user.getFirstName(), "John"),
        () -> assertEquals(user.getLastName(), "Due"),
        () -> assertEquals(user.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(user.getPassword(), "password 123"),
        () -> assertNull(user.getCar()));
  }

  @Test
  void testCreation_userWithCar() {
    Car car = new Car("TST 123 PL", "Mars", "DE");
    User user = new User("John", "Due", "john.due@tst.com", "password 123");
    user.setCar(car);
    assertAll(
        "user",
        () -> assertEquals(user.getFirstName(), "John"),
        () -> assertEquals(user.getLastName(), "Due"),
        () -> assertEquals(user.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(user.getPassword(), "password 123"),
        () -> assertNotNull(user.getCar()),
        () -> assertEquals(user.getCar().getPlateNumber(), "TST 123 PL"));
  }
}
