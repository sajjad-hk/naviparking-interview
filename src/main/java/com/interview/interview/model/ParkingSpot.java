package com.interview.interview.model;

import com.interview.interview.model.dto.ParkingSpotDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParkingSpot {

  @Id @GeneratedValue private Long spotId;
  private int spotNumber;

  public ParkingSpot(int spotNumber) {
    this.spotNumber = spotNumber;
  }

  public static ParkingSpot from(ParkingSpotDto parkingSpotDto) {
    return ParkingSpot.builder()
        .spotId(parkingSpotDto.getSpotId())
        .spotNumber(parkingSpotDto.getSpotNumber())
        .build();
  }
}
