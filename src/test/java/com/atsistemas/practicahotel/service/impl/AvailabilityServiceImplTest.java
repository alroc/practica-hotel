package com.atsistemas.practicahotel.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.atsistemas.practicahotel.dto.OpenAvailabilityDto;
import com.atsistemas.practicahotel.entity.Availability;
import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.repository.AvailabilityRepository;
import com.atsistemas.practicahotel.service.HotelService;

@ExtendWith(MockitoExtension.class)
public class AvailabilityServiceImplTest {
	
	private AvailabilityServiceImpl availabilityServiceImpl;
	@Mock private AvailabilityRepository availabilityRepositoryMock;
	@Mock private HotelService hotelServiceMock;
	
	private OpenAvailabilityDto openAvailabilityDto;
	private final Integer idHotel = 1;
	private final Integer rooms = 3;
	private final LocalDate dateFrom = LocalDate.of(2023, 7, 14);
	private final LocalDate dateTo = LocalDate.of(2023, 7, 14);
	private Hotel hotelMock;
	
	@BeforeEach
	public void setup()
	{
		availabilityServiceImpl = new AvailabilityServiceImpl(availabilityRepositoryMock, hotelServiceMock);
		openAvailabilityDto = new OpenAvailabilityDto(dateFrom, dateTo, idHotel, rooms);
		hotelMock = new Hotel(1, "Barcelo", 3);
	}
	
	@Test
	public void testOpen() throws HotelNotFoundException
	{
		Optional<Availability> optAvailability = Optional.of(new Availability(1, dateFrom, hotelMock, 2));
		
		Mockito.when(hotelServiceMock.findById(idHotel)).thenReturn(hotelMock);
		Mockito.when(availabilityRepositoryMock.findByDateAndHotel(dateFrom, hotelMock)).thenReturn(optAvailability);
		Mockito.when(availabilityRepositoryMock.save(optAvailability.get())).thenReturn(optAvailability.get());

		availabilityServiceImpl.open(openAvailabilityDto);
		
		Assertions.assertEquals(5, optAvailability.get().getRooms());

		
		Mockito.verify(hotelServiceMock).findById(idHotel);
		Mockito.verify(availabilityRepositoryMock).findByDateAndHotel(dateFrom, hotelMock);
		Mockito.verify(availabilityRepositoryMock).save(optAvailability.get());	
	}
	
	@Test
	public void testOpenWhenAvailabilityNotExist() throws HotelNotFoundException
	{		
		Mockito.when(hotelServiceMock.findById(idHotel)).thenReturn(hotelMock);
		Mockito.when(availabilityRepositoryMock.findByDateAndHotel(dateFrom, hotelMock)).thenReturn(Optional.empty());
		
		availabilityServiceImpl.open(openAvailabilityDto);
		
		Mockito.verify(hotelServiceMock).findById(idHotel);
		Mockito.verify(availabilityRepositoryMock).findByDateAndHotel(dateFrom, hotelMock);
	}
}
