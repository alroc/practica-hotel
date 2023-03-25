package com.atsistemas.practicahotel.service.impl;

import java.time.LocalDate;
import java.util.Optional;

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
	
	private final Integer idHotel = 1;
	private final LocalDate dateFrom = LocalDate.of(2023, 7, 14);
	private final LocalDate dateTo = LocalDate.of(2023, 7, 15);
	private Hotel hotelMock;
	
	@BeforeEach
	public void setup()
	{
		availabilityServiceImpl = new AvailabilityServiceImpl(availabilityRepositoryMock, hotelServiceMock);
		hotelMock = new Hotel(idHotel, "Barcelo", 3);
	}
	
	@Test
	public void testOpen() throws HotelNotFoundException
	{
		Availability availabilityMock = new Availability(1, dateFrom, hotelMock, 2);
		Optional<Availability> optAvailabilityMock = Optional.of(availabilityMock);
		
		OpenAvailabilityDto openAvailabilityDto = new OpenAvailabilityDto(dateFrom, dateTo, idHotel, 3);
		
		Mockito.when(hotelServiceMock.findById(idHotel)).thenReturn(hotelMock);
		Mockito.when(availabilityRepositoryMock.findByDateAndHotel(Mockito.any(LocalDate.class), Mockito.any(Hotel.class)))
			.thenReturn(optAvailabilityMock);
		Mockito.when(availabilityRepositoryMock.save(availabilityMock)).thenReturn(availabilityMock);

		availabilityServiceImpl.open(openAvailabilityDto);
		
		Assertions.assertEquals(8, availabilityMock.getRooms());
		
		Mockito.verify(hotelServiceMock).findById(idHotel);
		Mockito.verify(availabilityRepositoryMock, Mockito.times(2)).findByDateAndHotel(Mockito.any(LocalDate.class), Mockito.any(Hotel.class));
		Mockito.verify(availabilityRepositoryMock, Mockito.times(2)).save(availabilityMock);	
	}
	
	@Test
	public void testOpenWhenAvailabilityNotExist() throws HotelNotFoundException
	{
		OpenAvailabilityDto openAvailabilityDto = new OpenAvailabilityDto(dateFrom, dateTo, idHotel, 3);

		Mockito.when(hotelServiceMock.findById(idHotel)).thenReturn(hotelMock);
		Mockito.when(availabilityRepositoryMock.findByDateAndHotel(Mockito.any(LocalDate.class), Mockito.any(Hotel.class)))
			.thenReturn(Optional.empty());
		
		availabilityServiceImpl.open(openAvailabilityDto);
		
		Mockito.verify(hotelServiceMock).findById(idHotel);
		Mockito.verify(availabilityRepositoryMock, Mockito.times(2)).findByDateAndHotel(Mockito.any(LocalDate.class), Mockito.any(Hotel.class));
	}
	
}
