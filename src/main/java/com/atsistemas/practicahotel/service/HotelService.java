package com.atsistemas.practicahotel.service;

import java.util.List;

import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;

public interface HotelService {
	
	List<Hotel> findAll();
	
	Hotel findById(Integer id) throws HotelNotFoundException;
	
	Hotel create(Hotel hotel);
	
	Hotel update(Integer id, Hotel updatedHotel) throws HotelNotFoundException;
	
	List<Hotel> findHotels(String name, Integer category);
}
