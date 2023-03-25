package com.atsistemas.practicahotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.practicahotel.entity.Booking;
import com.atsistemas.practicahotel.entity.Hotel;


public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	List<Booking> findByHotelAndDateFromGreaterThanEqualAndDateToLessThanEqual(Hotel hotel, LocalDate startDate, LocalDate endDate);

}
