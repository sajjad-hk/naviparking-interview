package com.interview.interview.controller;

import com.interview.interview.model.Parking;
import com.interview.interview.model.dto.ParkingDto;
import com.interview.interview.service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class ParkingController {

  private final ParkingService parkingService;

  @PostMapping("/api/parking")
  public ResponseEntity<ParkingDto> addParking(@RequestBody ParkingDto parking) {
    ParkingDto savedParking = ParkingDto.from(parkingService.addParking(Parking.from(parking)));
    return new ResponseEntity<>(savedParking, HttpStatus.OK);
  }

  @PutMapping("/api/parking/{id}")
  public ResponseEntity<ParkingDto> addSpot(@PathVariable Long id) {
    ParkingDto parking = ParkingDto.from(parkingService.addSpotToParking(id));
    return new ResponseEntity<>(parking, HttpStatus.OK);
  }
}
