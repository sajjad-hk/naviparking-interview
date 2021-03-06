package com.interview.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Parking {

  @Id @GeneratedValue private Long id;
  private String name;
  private String address;
  private String coordinates;

  @OneToMany(mappedBy = "parking")
  private Set<ParkingSpot> spots;

  public Parking(Long id, String name, String address, String coordinates) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.coordinates = coordinates;
  }
}
