package com.interview.interview.model;

import com.interview.interview.model.dto.CarDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@Entity
public class Car {

  @Id private String plateNumber;
  private String model;
  private String make;

  public Car(String plateNumber, String model, String make) {
    this.plateNumber = plateNumber;
    this.model = model;
    this.make = make;
  }

  public static Car from(CarDto carDto) {
    return Car.builder()
        .plateNumber(carDto.getPlateNumber())
        .model(carDto.getModel())
        .make(carDto.getMake())
        .build();
  }
}
