package com.atsistemas.practicahotel.mapper;

import org.springframework.stereotype.Component;

import com.atsistemas.practicahotel.dto.HotelDto;
import com.atsistemas.practicahotel.entity.Hotel;

@Component
public class HotelMapper implements Mapper<HotelDto, Hotel> {

	@Override
	public HotelDto mapToDto(Hotel entity) {
		return new HotelDto(entity.getId(), entity.getName(), entity.getCategory());
	}

	@Override
	public Hotel mapToEntity(HotelDto dto) {
		return new Hotel(dto.getId(), dto.getName(), dto.getCategory());
	}
}
