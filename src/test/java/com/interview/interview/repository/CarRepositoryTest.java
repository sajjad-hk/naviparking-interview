package com.interview.interview.repository;

import com.interview.interview.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class CarRepositoryTest {

  @Autowired private CarRepository repository;

  @Test
  void testCreation_carWithoutUser() {
    Car notPersistedCar = new Car("TST 123 PL", "Mars", "DE");
    repository.save(notPersistedCar);

    String plateNumber = "TST 123 PL";
    Car car =
        repository
            .findById(plateNumber)
            .orElseThrow(
                () -> new RuntimeException("car with plate number" + plateNumber + "not found!"));

    assertAll(
        "user car",
        () -> assertEquals(car.getPlateNumber(), "TST 123 PL"),
        () -> assertEquals(car.getModel(), "Mars"),
        () -> assertEquals(car.getMake(), "DE"));
  }
}
