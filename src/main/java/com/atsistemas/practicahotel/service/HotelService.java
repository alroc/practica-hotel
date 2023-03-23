package com.atsistemas.practicahotel.service;

import java.time.LocalDate;
import java.util.List;

import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;

public interface HotelService {
	
	List<Hotel> findAll();
	
	Hotel findById(Integer id) throws HotelNotFoundException;
	
	Hotel create(Hotel hotel);
	
	Hotel update(Integer id, Hotel updatedHotel) throws HotelNotFoundException;
	
	List<Hotel> findHotels(String name, Integer category, LocalDate dateFrom, LocalDate dateTo);
	
	List<Hotel> findAvailableHotels(LocalDate dateFrom, LocalDate dateTo);
}
