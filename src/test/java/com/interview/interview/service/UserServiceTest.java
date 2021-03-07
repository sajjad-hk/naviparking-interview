package com.interview.interview.service;

import com.interview.interview.model.Car;
import com.interview.interview.model.User;
import com.interview.interview.repository.CarRepository;
import com.interview.interview.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

  @Autowired private UserService userService;
  @Autowired private UserRepository userRepository;
  @Autowired private CarRepository carRepository;

  @AfterEach
  void tearDown() {
    userRepository.deleteAll();
  }

  @Test
  void testAddBaseUser() {

    User user = userService.addUser(new User("John", "Due", "john.due@tst.com", "password 123"));

    User savedUser = userService.findUserById(user.getUserId());
    assertAll(
        "user",
        () -> assertEquals(savedUser.getFirstName(), "John"),
        () -> assertEquals(savedUser.getLastName(), "Due"),
        () -> assertEquals(savedUser.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(savedUser.getPassword(), "password 123"),
        () -> assertNull(savedUser.getCar()));
  }

  @Test
  void testCreateUser_withCar() {

    User user = new User("John", "Due", "john.due@tst.com", "password 123");
    Car car = new Car("TST 123 PL", "Mars", "DE");
    user.setCar(car);
    User savedUser = userService.addUser(user);
    assertAll(
            "user",
            () -> assertEquals(savedUser.getUserId(), user.getUserId()),
            () -> assertEquals(savedUser.getFirstName(), "John"),
            () -> assertEquals(savedUser.getLastName(), "Due"),
            () -> assertEquals(savedUser.getEmailAddress(), "john.due@tst.com"),
            () -> assertEquals(savedUser.getPassword(), "password 123"),
            () -> assertNotNull(savedUser.getCar()));
  }

  @Test
  void testUpdateUser_withCar() {

    User user = userService.addUser(new User("John", "Due", "john.due@tst.com", "password 123"));
    Car car = new Car("TST 123 PL", "Mars", "DE");

    User updatedUser = userService.addCarToUser(user.getUserId(), car);
    assertAll(
        "user",
        () -> assertEquals(updatedUser.getUserId(), user.getUserId()),
        () -> assertEquals(updatedUser.getFirstName(), "John"),
        () -> assertEquals(updatedUser.getLastName(), "Due"),
        () -> assertEquals(updatedUser.getEmailAddress(), "john.due@tst.com"),
        () -> assertEquals(updatedUser.getPassword(), "password 123"),
        () -> assertNotNull(updatedUser.getCar()));
  }

  @Test
  void testGetAllUsers() {
    User user1 = userService.addUser(new User("John1", "Due1", "john.due@tst.com", "password 123"));
    User user2 = userService.addUser(new User("John2", "Due2", "john.due@tst.com", "password 123"));
    User user3 = userService.addUser(new User("John3", "Due3", "john.due@tst.com", "password 123"));

    List<User> users = userService.getAllUsers();

    assertFalse(users.isEmpty());
    assertEquals(users.size(), 3);
  }
}
