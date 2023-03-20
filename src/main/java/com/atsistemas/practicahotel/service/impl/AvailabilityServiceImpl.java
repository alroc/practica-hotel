package com.atsistemas.practicahotel.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.atsistemas.practicahotel.dto.OpenAvailabilityDto;
import com.atsistemas.practicahotel.entity.Availability;
import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.repository.AvailabilityRepository;
import com.atsistemas.practicahotel.service.AvailabilityService;
import com.atsistemas.practicahotel.service.HotelService;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{
	
	private AvailabilityRepository availabilityRepository;
	private HotelService hotelService;	

	public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, HotelService hotelService) {
		super();
		this.availabilityRepository = availabilityRepository;
		this.hotelService = hotelService;
	}


	@Override
	public void open(OpenAvailabilityDto openAvailabilityDto) throws HotelNotFoundException {
		
		//Busqueda del hotel
		Hotel hotel = hotelService.findById(openAvailabilityDto.getIdHotel());
		
		//Construccion del rango de fechas
		List<LocalDate> dates = openAvailabilityDto.getDateFrom()
				.datesUntil(openAvailabilityDto.getDateTo().plusDays(1)).collect(Collectors.toList());
		
		//Recorro fechas y actualizo/registro las disponibilidades
		dates.forEach((date) -> {
			Optional<Availability> optAvailability = availabilityRepository.findByDateAndHotel(date, hotel);
			Availability availability;
			
			//Ya existe una disponibilidad con esa fecha
			if(optAvailability.isPresent())
			{
				availability = optAvailability.get();
				availability.setRooms(availability.getRooms() + openAvailabilityDto.getRooms());
				availabilityRepository.save(availability);
			}
			else //No existe disponibilidad con esa fecha
			{
				availability = new Availability();
				availability.setHotel(hotel);
				availability.setDate(date);
				availability.setRooms(openAvailabilityDto.getRooms());
				availabilityRepository.save(availability);
			}			
		});
		
	}	
	
	//Devuelve true si el hotel esta disponible para todo el rango de dias, y falso para lo contrario
	@Override
	public boolean checkHotelAvailability(Hotel hotel, List<LocalDate> dates) {
		
		boolean dayNotAvailable = dates.stream().anyMatch((date) ->  availabilityRepository
				.findByDateAndHotelAndRoomsGreaterThanEqual(date, hotel, 1).isEmpty());
	
		return !(dayNotAvailable);
	}

	@Override
	public List<Hotel> findAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String name, 
			Integer category) {
		List<Hotel> availableHotels = new ArrayList<Hotel>();
		List<Hotel> hotels = hotelService.findHotels(name, category);
		
		//Rango de fechas
		List<LocalDate> dates = dateFrom.datesUntil(dateTo.plusDays(1)).collect(Collectors.toList());
		
		hotels.forEach((hotel) -> {
			if(this.checkHotelAvailability(hotel, dates))
				availableHotels.add(hotel);
		});
		
		return availableHotels;
	}

}
