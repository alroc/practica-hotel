package com.atsistemas.practicahotel.service;

import com.atsistemas.practicahotel.dto.BookingDto;
import com.atsistemas.practicahotel.dto.CreateBookingDto;
import com.atsistemas.practicahotel.entity.Booking;
import com.atsistemas.practicahotel.error.HotelNotAvailableException;
import com.atsistemas.practicahotel.error.HotelNotFoundException;

public interface BookingService {
	
	Booking create(CreateBookingDto createBookingDto) throws HotelNotFoundException, HotelNotAvailableException;

}
