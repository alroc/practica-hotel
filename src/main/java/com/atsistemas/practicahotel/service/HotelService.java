package com.atsistemas.practicahotel.service;

import java.util.List;
import java.util.Optional;

import com.atsistemas.practicahotel.entity.Hotel;

public interface HotelService {
	
	public List<Hotel> findAll();
	
	public Hotel findById(Integer id);
	
	public Hotel create(Hotel hotel);
	
	public Hotel update(Integer id, Hotel updatedHotel);

	

}
