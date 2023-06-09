package com.atsistemas.practicahotel.service.impl;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.repository.HotelRepository;

@ExtendWith(MockitoExtension.class)
public class HotelServiceImplTest {
	
	private HotelServiceImpl hotelServiceImpl;
	@Mock private HotelRepository hotelRepositoryMock;
	
	private final Integer id = 1;
	private List<Hotel> hotelsMock;
	private Hotel hotelMock;
	private Optional<Hotel> optHotelMock;
	
	private final LocalDate dateFrom = LocalDate.of(2023, 7, 14);
	private final LocalDate dateTo = LocalDate.of(2023, 7, 16);
	private final Long days = dateFrom.until(dateTo.plusDays(1), ChronoUnit.DAYS);
	
	@BeforeEach
	public void setup()
	{
		hotelServiceImpl = new HotelServiceImpl(hotelRepositoryMock);
		
		hotelsMock = List.of(new Hotel(1, "Barcelo", 3), new Hotel(2, "Mar", 4));
		hotelMock = new Hotel(1, "Barcelo", 3);
		optHotelMock = Optional.of(new Hotel(1, "Barcelo", 3));
	}
	
	@Test
	public void testFindAll()
	{
		Mockito.when(hotelRepositoryMock.findAll()).thenReturn(hotelsMock);
		
		List<Hotel> hotels = hotelServiceImpl.findAll();
		Assertions.assertEquals(2, hotels.size());
		
		Mockito.verify(hotelRepositoryMock).findAll();
		Mockito.verifyNoMoreInteractions(hotelRepositoryMock);
	}
	
	@Test
	public void testFindById() throws HotelNotFoundException
	{
		Mockito.when(hotelRepositoryMock.findById(id)).thenReturn(optHotelMock);

		Assertions.assertEquals(id, hotelServiceImpl.findById(id).getId());		
		
		Mockito.verify(hotelRepositoryMock).findById(id);
		Mockito.verifyNoMoreInteractions(hotelRepositoryMock);
	}
	
	@Test
	public void testFindByIdWhenException() throws HotelNotFoundException
	{
		Mockito.when(hotelRepositoryMock.findById(id)).thenReturn(Optional.empty());
		Assertions.assertThrows(HotelNotFoundException.class, () -> hotelServiceImpl.findById(id));
	}
	
	@Test
	public void testCreate()
	{
		Mockito.when(hotelRepositoryMock.save(hotelMock)).thenReturn(hotelMock);
		
		Assertions.assertNotNull(hotelServiceImpl.create(hotelMock));
		
		Mockito.verify(hotelRepositoryMock).save(hotelMock);
		Mockito.verifyNoMoreInteractions(hotelRepositoryMock);
	}
	
	@Test
	public void testUpdate() throws HotelNotFoundException
	{
		Mockito.when(hotelRepositoryMock.findById(id)).thenReturn(optHotelMock);
		Mockito.when(hotelRepositoryMock.save(optHotelMock.get())).thenReturn(optHotelMock.get());
		
		Hotel updatedHotelMock = new Hotel();
		updatedHotelMock.setName("Zenit");
		updatedHotelMock.setCategory(4);

		Hotel hotel = hotelServiceImpl.update(id, updatedHotelMock);
		
		Assertions.assertEquals(hotel.getName(), "Zenit");
		Assertions.assertEquals(hotel.getCategory(), 4);

		Mockito.verify(hotelRepositoryMock).findById(id);
		Mockito.verify(hotelRepositoryMock).save(optHotelMock.get());
	}

	@Test
	public void testFindHotels()
	{			
		Mockito.when(hotelRepositoryMock.findAvailableHotels(dateFrom, dateTo, days)).thenReturn(hotelsMock);
		Mockito.when(hotelRepositoryMock.findAll(Mockito.<Specification<Hotel>>any())).thenReturn(hotelsMock);
		
		List<Hotel> hotels = hotelServiceImpl.findHotels("Barcelo", 3, dateFrom, dateTo);
		Assertions.assertEquals(hotels.size(), 2);

		Mockito.verify(hotelRepositoryMock).findAvailableHotels(dateFrom, dateTo, days);
		Mockito.verify(hotelRepositoryMock).findAll(Mockito.<Specification<Hotel>>any());
	}
	
	@Test
	public void testFindAvailableHotels()
	{
		Mockito.when(hotelRepositoryMock.findAvailableHotels(dateFrom, dateTo, days)).thenReturn(hotelsMock);
		
		List<Hotel> hotels = hotelServiceImpl.findAvailableHotels(dateFrom, dateTo);
		Assertions.assertEquals(hotels.size(), 2);

		Mockito.verify(hotelRepositoryMock).findAvailableHotels(dateFrom, dateTo, days);
		Mockito.verifyNoMoreInteractions(hotelRepositoryMock);
	}
}
