package com.atsistemas.practicahotel.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.repository.HotelRepository;
import com.atsistemas.practicahotel.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	private HotelRepository hotelRepository;

	public HotelServiceImpl(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}

	@Override
	public List<Hotel> findAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel findById(Integer id) {
		return hotelRepository.findById(id).get();
	}

	@Override
	public Hotel create(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel update(Integer id, Hotel updatedHotel) {
		Hotel hotel = findById(id);
		
		hotel.setName(updatedHotel.getName());
		hotel.setCategory(updatedHotel.getCategory());		
		
		return hotelRepository.save(hotel);
	}
	
	

}
