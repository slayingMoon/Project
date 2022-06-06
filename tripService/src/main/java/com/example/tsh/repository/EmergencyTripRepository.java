package com.example.tsh.repository;

import com.example.tsh.domain.entity.EmergencyTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyTripRepository extends JpaRepository<EmergencyTrip, Long> {
}
