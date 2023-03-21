package com.atsistemas.practicahotel.mapper;

import org.springframework.stereotype.Component;

import com.atsistemas.practicahotel.dto.BookingDto;
import com.atsistemas.practicahotel.dto.HotelDto;
import com.atsistemas.practicahotel.entity.Booking;
import com.atsistemas.practicahotel.entity.Hotel;


@Component
public class BookingMapper implements Mapper<BookingDto, Booking>{

	private Mapper<HotelDto, Hotel> hotelMapper;

	
	public BookingMapper(Mapper<HotelDto, Hotel> hotelMapper) {
		super();
		this.hotelMapper = hotelMapper;
	}

	@Override
	public BookingDto mapToDto(Booking entity) {
		return new BookingDto(entity.getId(),  hotelMapper.mapToDto(entity.getHotel()), 
				entity.getDateFrom(), entity.getDateTo(), entity.getEmail());
	}

	@Override
	public Booking mapToEntity(BookingDto dto) {
		return new Booking(dto.getId(), hotelMapper.mapToEntity(dto.getHotel()), 
				dto.getDateFrom(), dto.getDateTo(), dto.getEmail());
	}

}
