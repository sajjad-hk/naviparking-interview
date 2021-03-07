package com.interview.interview.model;

import com.interview.interview.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

  @Id @GeneratedValue private Long userId;
  private String firstName;
  private String lastName;
  private String emailAddress;
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "plate_number")
  private Car car;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "user_assigned_parking",
      joinColumns = @JoinColumn(name = "parkingId"),
      inverseJoinColumns = @JoinColumn(name = "userId"))
  private Set<Parking> assignedParking = new HashSet<>();

  public User(String firstName, String lastName, String emailAddress, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.password = password;
  }

  public static User from(UserDto userDto) {
    return User.builder()
        .userId(userDto.getUserId())
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .emailAddress(userDto.getEmailAddress())
        .password(userDto.getPassword())
        .car(Car.from(userDto.getCar()))
        .assignedParking(
            userDto.getAssignedParking().stream().map(Parking::from).collect(toSet()))
        .build();
  }
}
