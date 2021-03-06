package com.interview.interview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@Entity
public class User {

  @Id @GeneratedValue private Long id;
  private String firstName;
  private String lastName;
  private String emailAddress;
  private String password;

  @OneToOne(mappedBy = "user")
  private Car car;

  public User(Long id, String firstName, String lastName, String emailAddress, String password) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.password = password;
  }
}
