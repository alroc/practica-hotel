package com.atsistemas.practicahotel.service;

import java.util.List;
import java.util.Optional;

import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;

public interface HotelService {
	
	public List<Hotel> findAll();
	
	public Hotel findById(Integer id) throws HotelNotFoundException;
	
	public Hotel create(Hotel hotel);
	
	public Hotel update(Integer id, Hotel updatedHotel) throws HotelNotFoundException;

	

}
