package com.interview.interview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Parking {

  @Id @GeneratedValue private Long parkingId;
  private String name;
  private String address;
  private String coordinates;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parking")
  private Set<ParkingSpot> spots;

  @ManyToMany(mappedBy = "assignedParking")
  private Set<User> users = new HashSet<>();

  public Parking(Long parkingId, String name, String address, String coordinates) {
    this.parkingId = parkingId;
    this.name = name;
    this.address = address;
    this.coordinates = coordinates;
  }
}
