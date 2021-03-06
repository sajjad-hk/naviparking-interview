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
public class UserJpaTest {
  @Autowired private TestEntityManager tem;

  @Test
  void mapping() {
    Car car = tem.persistAndFlush(new Car("TST 123 PL", "Mars", "DE"));
    User notPersistedUser = new User(null, "John", "Due", "john.due@tst.com", "password 123");
    notPersistedUser.setCar(car);

    User user =
        tem.persistAndFlush(notPersistedUser);

    assertAll(
        "user",
        () -> assertNotNull(user.getId()),
        () -> assertEquals(user.getFirstName(), "John"),
        () -> assertEquals(user.getLastName(), "Due"),
        () -> assertEquals(user.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(user.getPassword(), "password 123"),
        () -> assertNotNull(user.getCar()),
        () -> assertEquals(user.getCar().getPlateNumber(), "TST 123 PL"));
  }
}
