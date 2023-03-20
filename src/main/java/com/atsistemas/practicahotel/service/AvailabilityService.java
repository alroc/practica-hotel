package com.atsistemas.practicahotel.service;

import java.time.LocalDate;
import java.util.List;

import com.atsistemas.practicahotel.dto.OpenAvailabilityDto;
import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;

public interface AvailabilityService {

	void open(OpenAvailabilityDto openAvailabilityDto) throws HotelNotFoundException;

	boolean checkHotelAvailability(Hotel hotel, List<LocalDate> dates);

	List<Hotel> findAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String name, Integer category);
	
}
