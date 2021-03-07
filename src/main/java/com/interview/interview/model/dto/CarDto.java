package com.interview.interview.model.dto;

import com.interview.interview.model.Car;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarDto {

  private String plateNumber;
  private String model;
  private String make;

  public static CarDto from(Car car) {
    return CarDto.builder()
        .plateNumber(car.getPlateNumber())
        .model(car.getModel())
        .make(car.getMake())
        .build();
  }
}
