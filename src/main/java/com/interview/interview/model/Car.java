package com.interview.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Car {

  @Id
  private String plateNumber;
  private String model;
  private String make;

  @OneToOne
  @JoinColumn(name = "id")
  private User user;

  public Car(String plateNumber, String model, String make) {
    this.plateNumber = plateNumber;
    this.model = model;
    this.make = make;
  }
}
