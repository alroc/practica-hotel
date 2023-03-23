package com.atsistemas.practicahotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.atsistemas.practicahotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel> {
	
	
	@Query(value = "SELECT h FROM Availability a INNER JOIN a.hotel h where a.date BETWEEN :dateFrom AND :dateTo AND a.rooms > 0"
			+ "GROUP BY h HAVING COUNT(a) = :days ORDER BY h.id ASC")
	public List<Hotel> findAvailableHotels(LocalDate dateFrom, LocalDate dateTo, Long days);
}
