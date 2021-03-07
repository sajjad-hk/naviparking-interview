package com.interview.interview.controller;

import com.interview.interview.model.User;
import com.interview.interview.model.dto.UserDto;
import com.interview.interview.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  @GetMapping("/api/users")
  public ResponseEntity<List<UserDto>> getUsers() {
    List<UserDto> users = userService.getAllUsers().stream().map(UserDto::from).collect(toList());
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/user/user/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable final Long id) {
    UserDto user = UserDto.from(userService.findUserById(id));
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping("/api/user")
  public ResponseEntity<UserDto> addUser(@RequestBody final UserDto user) {
    UserDto userDto = UserDto.from(userService.addUser(User.from(user)));
    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }

  @PutMapping("/api/user/{id}")
  public ResponseEntity<UserDto> editUser(@PathVariable final Long id, @RequestParam UserDto user) {
    UserDto userToEdit = UserDto.from(userService.editUser(id, User.from(user)));
    return new ResponseEntity<>(userToEdit, HttpStatus.OK);
  }
}
