package com.interview.interview.model.dto;

import com.interview.interview.model.Parking;
import com.interview.interview.model.ParkingSpot;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Builder
@Data
public class ParkingDto {

  private Long parkingId;
  private String name;
  private String address;
  private String coordinates;
  private Set<ParkingSpotDto> spots;
  private Set<UserDto> users;

  public static ParkingDto from(Parking parking) {
    return ParkingDto.builder()
        .parkingId(parking.getParkingId())
        .name(parking.getName())
        .address(parking.getAddress())
        .coordinates(parking.getCoordinates())
        .spots(parking.getSpots().stream().map(ParkingSpotDto::from).collect(toSet()))
        //        .users(User.parking.getUsers())
        .build();
  }
}
