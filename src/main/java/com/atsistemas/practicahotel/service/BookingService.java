package com.atsistemas.practicahotel.service;

import java.time.LocalDate;
import java.util.List;

import com.atsistemas.practicahotel.dto.CreateBookingDto;
import com.atsistemas.practicahotel.entity.Booking;
import com.atsistemas.practicahotel.error.BookingNotFoundException;
import com.atsistemas.practicahotel.error.HotelNotAvailableException;
import com.atsistemas.practicahotel.error.HotelNotFoundException;

public interface BookingService {
	
	Booking create(CreateBookingDto createBookingDto) throws HotelNotFoundException, HotelNotAvailableException;
	
	Booking findById(Integer id) throws BookingNotFoundException;
	
	List<Booking> findBookings(Integer idHotel, LocalDate startDate, LocalDate endDate) throws HotelNotFoundException; 
	
	void delete(Integer id) throws BookingNotFoundException;
}
