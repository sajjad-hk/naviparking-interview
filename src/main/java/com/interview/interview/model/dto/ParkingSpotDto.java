package com.interview.interview.model.dto;

import com.interview.interview.model.ParkingSpot;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ParkingSpotDto {

  private Long spotId;
  private int spotNumber;

  public static ParkingSpotDto from(ParkingSpot parkingSpot) {
    return ParkingSpotDto.builder()
        .spotId(parkingSpot.getSpotId())
        .spotNumber(parkingSpot.getSpotNumber())
        .build();
  }
}
