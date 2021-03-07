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

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "parking_id")
  private Set<ParkingSpot> spots = new HashSet<>();

  @ManyToMany(mappedBy = "assignedParking")
  private Set<User> users = new HashSet<>();

  public Parking(String name, String address, String coordinates) {
    this.name = name;
    this.address = address;
    this.coordinates = coordinates;
  }

  public void addSpot(ParkingSpot spot) {
    spots.add(spot);
  }
}
