package com.atsistemas.practicahotel.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.practicahotel.entity.Availability;
import com.atsistemas.practicahotel.entity.Hotel;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
	
	Optional<Availability> findByDateAndHotel(LocalDate date, Hotel hotel);
	
}
