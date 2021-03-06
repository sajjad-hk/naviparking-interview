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
public class CarJpaTest {

  @Autowired private TestEntityManager tem;

  @Test
  void mapping() {
    Car car = tem.persistAndFlush(new Car("TST 123 PL", "Mars", "DE"));
    assertAll(
        "user car",
        () -> assertEquals(car.getPlateNumber(), "TST 123 PL"),
        () -> assertEquals(car.getModel(), "Mars"),
        () -> assertEquals(car.getMake(), "DE"));
  }
}
