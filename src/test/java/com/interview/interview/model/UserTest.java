package com.interview.interview.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  void testCreation() {
    User user = new User(1L, "John", "Due", "john.due@tst.com", "password 123");

    assertAll(
        "user",
        () -> assertEquals(user.getId(), 1L),
        () -> assertEquals(user.getFirstName(), "John"),
        () -> assertEquals(user.getLastName(), "Due"),
        () -> assertEquals(user.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(user.getPassword(), "password 123"),
        () -> assertNull(user.getCar()));
  }
}
