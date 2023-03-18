package com.atsistemas.practicahotel.mapper;

import org.springframework.stereotype.Component;

import com.atsistemas.practicahotel.dto.AvailabilityDto;
import com.atsistemas.practicahotel.dto.HotelDto;
import com.atsistemas.practicahotel.entity.Availability;
import com.atsistemas.practicahotel.entity.Hotel;

@Component
public class AvailabilityMapper implements Mapper<AvailabilityDto, Availability>{
	
	private Mapper<HotelDto, Hotel> hotelMapper;
	
	public AvailabilityMapper(Mapper<HotelDto, Hotel> hotelMapper) {
		super();
		this.hotelMapper = hotelMapper;
	}

	@Override
	public AvailabilityDto mapToDto(Availability entity) {
		return new AvailabilityDto(entity.getId(), entity.getDate(), hotelMapper.mapToDto(entity.getHotel()), entity.getRooms());
	}

	@Override
	public Availability mapToEntity(AvailabilityDto dto) {
		return new Availability(dto.getId(), dto.getDate(), hotelMapper.mapToEntity(dto.getHotel()), dto.getRooms());
	}

}
