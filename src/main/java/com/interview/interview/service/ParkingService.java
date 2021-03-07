package com.interview.interview.service;

import com.interview.interview.model.Parking;
import com.interview.interview.model.ParkingSpot;
import com.interview.interview.repository.ParkingRepository;
import com.interview.interview.repository.ParkingSpotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class ParkingService {

  private final ParkingRepository parkingRepository;
  private final ParkingSpotRepository parkingSpotRepository;

  public Parking addParking(Parking parking) {
    return parkingRepository.save(parking);
  }

  @Transactional
  public Parking addSpotToParking(long parkingId) {
    Parking parking = findParkingById(parkingId);
    int max =
        parking.getSpots().stream()
            .map(ParkingSpot::getSpotNumber)
            .max(Comparator.naturalOrder())
            .orElse(1);

    ParkingSpot spot = parkingSpotRepository.save(new ParkingSpot(max));
    parking.addSpot(spot);
    return parkingRepository.save(parking);
  }

  public Parking findParkingById(long parkingId) {
    return parkingRepository
        .findById(parkingId)
        .orElseThrow(() -> new RuntimeException("parking with id " + parkingId + " not found!"));
  }
}
