package com.interview.interview.service;

import com.interview.interview.model.Car;
import com.interview.interview.model.User;
import com.interview.interview.repository.CarRepository;
import com.interview.interview.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final CarRepository carRepository;

  public User addUser(User user) {
   return userRepository.save(user);
  }

  @Transactional
  public User addCarToUser(long userId, Car car) {
    User user = findUserById(userId);
    Car savedCar = carRepository.save(car);
    user.setCar(savedCar);
    return userRepository.save(user);
  }

  public User findUserById(long userId) {
    return userRepository
        .findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("user with id " + userId + "not found!"));
  }

  public List<User> getAllUsers() {
    return StreamSupport.stream(userRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  public User editUser(Long id, User user) {
    User userToEdit = findUserById(id);
    userToEdit.setFirstName(user.getFirstName());
    userToEdit.setLastName(user.getLastName());
    userToEdit.setEmailAddress(user.getEmailAddress());
    userToEdit.setPassword(user.getPassword());
    userToEdit.setCar(user.getCar());
    return userToEdit;
  }
}
