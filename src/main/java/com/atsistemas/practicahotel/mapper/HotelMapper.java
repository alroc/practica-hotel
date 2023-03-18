package com.atsistemas.practicahotel.mapper;

import org.springframework.stereotype.Component;

import com.atsistemas.practicahotel.dto.HotelDto;
import com.atsistemas.practicahotel.entity.Hotel;

@Component
public class HotelMapper {
	
	public HotelDto mapToDto(Hotel hotel) {
		return new HotelDto(hotel.getId(), hotel.getName(), hotel.getCategory());
	}
	
	public Hotel mapToEntity(HotelDto hotel) {
		return new Hotel(hotel.getId(), hotel.getName(), hotel.getCategory());
	}

}
