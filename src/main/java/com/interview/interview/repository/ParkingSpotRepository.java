package com.interview.interview.repository;

import com.interview.interview.model.ParkingSpot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Long> {

    ParkingSpot findParkingSpotBySpotNumber(int spotNumber);
}
