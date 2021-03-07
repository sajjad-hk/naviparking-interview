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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parkingId")
  private Parking parking;

  public ParkingSpot(Long spotId, int spotNumber) {
    this.spotId = spotId;
    this.spotNumber = spotNumber;
  }
}
