package com.interview.interview.model.dto;

import com.interview.interview.model.Car;
import com.interview.interview.model.Parking;
import com.interview.interview.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Builder
@Data
public class UserDto {

  private Long userId;
  private String firstName;
  private String lastName;
  private String emailAddress;
  private String password;
  private CarDto car;
  private Set<ParkingDto> assignedParking;

  public static UserDto from(User user) {
    return UserDto.builder()
        .userId(user.getUserId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .emailAddress(user.getEmailAddress())
        .password(user.getPassword())
        .car(CarDto.from(user.getCar()))
        .assignedParking(
            user.getAssignedParking().stream().map(ParkingDto::from).collect(toSet()))
        .build();
  }
}
