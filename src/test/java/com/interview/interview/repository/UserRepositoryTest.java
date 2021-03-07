package com.interview.interview.repository;

import com.interview.interview.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

  @Autowired private UserRepository repository;

  @Test
  void creationUser_withOutCar_withOutParking() {

    User user = repository.save(new User("John", "Due", "john.due@tst.com", "password 123"));

    User persistedUser =
        repository
            .findByUserId(user.getUserId())
            .orElseThrow(
                () -> new RuntimeException("user with userId " + user.getUserId() + "not found"));

    assertAll(
        "user",
        () -> assertNotNull(persistedUser.getUserId()),
        () -> assertEquals(persistedUser.getFirstName(), "John"),
        () -> assertEquals(persistedUser.getLastName(), "Due"),
        () -> assertEquals(persistedUser.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(persistedUser.getPassword(), "password 123"),
        () -> assertNull(persistedUser.getCar()));
  }
}
