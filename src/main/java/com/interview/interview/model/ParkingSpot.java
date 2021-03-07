package com.interview.interview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ParkingSpot {

  @Id @GeneratedValue private Long spotId;
  private int spotNumber;

  public ParkingSpot(int spotNumber) {
    this.spotNumber = spotNumber;
  }
}
