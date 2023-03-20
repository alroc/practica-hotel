package com.atsistemas.practicahotel.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.practicahotel.entity.Availability;
import com.atsistemas.practicahotel.entity.Hotel;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer>{
	
	//Busca la disponibilidad de un hotel y una fecha
	Optional<Availability> findByDateAndHotel(LocalDate date, Hotel hotel);
	
	//Busca la disponibililidad con un hotel y una en especifico, y un numero de habitaciones
	Optional<Availability> findByDateAndHotelAndRoomsGreaterThanEqual(LocalDate date, Hotel hotel, Integer rooms);

}
