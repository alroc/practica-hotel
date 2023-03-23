package com.atsistemas.practicahotel.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.atsistemas.practicahotel.dto.CreateBookingDto;
import com.atsistemas.practicahotel.entity.Availability;
import com.atsistemas.practicahotel.entity.Booking;
import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.BookingNotFoundException;
import com.atsistemas.practicahotel.error.HotelNotAvailableException;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.repository.AvailabilityRepository;
import com.atsistemas.practicahotel.repository.BookingRepository;
import com.atsistemas.practicahotel.service.HotelService;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {
	
	private BookingServiceImpl bookingServiceImpl;
	
	@Mock private BookingRepository bookingRepositoryMock;
	@Mock private HotelService hotelServiceMock;
	@Mock private AvailabilityRepository availabilityRepositoryMock;
	
	private final LocalDate dateFrom = LocalDate.of(2023, 7, 14);
	private final LocalDate dateTo = LocalDate.of(2023, 7, 15);
	private final Integer id = 5;
	
	private Hotel hotelMock;
	private Optional<Booking> optBookingMock;
	private Booking bookingMock;
	private Availability availabilityMock;
	Optional<Availability> optAvailabilityMock;
	
	@BeforeEach
	public void setup()
	{
		bookingServiceImpl = new BookingServiceImpl(bookingRepositoryMock, hotelServiceMock, availabilityRepositoryMock);
		hotelMock = new Hotel(1, "Barcelo", 4);
		bookingMock = new Booking(id, hotelMock, dateFrom, dateTo, "prueba@gmail.com");
		
		optBookingMock = Optional.of(bookingMock);
		bookingMock = optBookingMock.get();
		
		availabilityMock = new Availability(1, dateFrom, hotelMock, 3);
		optAvailabilityMock = Optional.of(availabilityMock);
	}
	
	@Test
	public void testFindById() throws BookingNotFoundException
	{
		Mockito.when(bookingRepositoryMock.findById(id)).thenReturn(optBookingMock);
		
		Assertions.assertEquals(id, bookingServiceImpl.findById(id).getId());		
		
		Mockito.verify(bookingRepositoryMock).findById(id);
		Mockito.verifyNoMoreInteractions(bookingRepositoryMock);
	}
	
	@Test
	public void testFindByIdWhenBookingNotFound() throws BookingNotFoundException
	{
		Mockito.when(bookingRepositoryMock.findById(id)).thenReturn(Optional.empty());
		Assertions.assertThrows(BookingNotFoundException.class, () -> bookingServiceImpl.findById(id));

	}
	
	@Test
	public void testFindBookings() throws HotelNotFoundException
	{
		List<Booking> bookingsMock = List.of(bookingMock, new Booking(2, hotelMock, dateFrom, dateTo, "pedro@gmail.com"));
		
		Mockito.when(hotelServiceMock.findById(hotelMock.getId())).thenReturn(hotelMock);
		Mockito.when(bookingRepositoryMock.findByHotelAndDateFromAndDateTo(hotelMock, dateFrom, dateTo)).thenReturn(bookingsMock);
		
		List<Booking> bookings =  bookingServiceImpl.findBookings(hotelMock.getId(), dateFrom, dateTo);
		Assertions.assertEquals(bookingsMock.size(), bookings.size());
		
		Mockito.verify(hotelServiceMock).findById(hotelMock.getId());
		Mockito.verify(bookingRepositoryMock).findByHotelAndDateFromAndDateTo(hotelMock, dateFrom, dateTo);
	}
	
	@Test
	public void testCreate() throws HotelNotFoundException, HotelNotAvailableException
	{
		CreateBookingDto createBookingDto = new CreateBookingDto(hotelMock.getId(), dateFrom, dateTo, "prueba@gmail.com");
		List<Hotel> availableHotelsMock = List.of(hotelMock, new Hotel(2, "Mar", 5));
		
		Mockito.when(hotelServiceMock.findById(1)).thenReturn(hotelMock);
		Mockito.when(hotelServiceMock.findAvailableHotels(dateFrom, dateTo)).thenReturn(availableHotelsMock);
		Mockito.when(availabilityRepositoryMock.findByDateAndHotel(Mockito.any(LocalDate.class), Mockito.any(Hotel.class))).thenReturn(optAvailabilityMock);
		Mockito.when(availabilityRepositoryMock.save(availabilityMock)).thenReturn(availabilityMock);
		Mockito.when(bookingRepositoryMock.save(Mockito.any(Booking.class))).thenReturn(bookingMock);

		Booking booking = bookingServiceImpl.create(createBookingDto);
		
		Assertions.assertEquals(1, availabilityMock.getRooms());
		Assertions.assertNotNull(booking);
		
		Mockito.verify(availabilityRepositoryMock, Mockito.times(2)).findByDateAndHotel(Mockito.any(LocalDate.class), Mockito.any(Hotel.class));
		Mockito.verify(availabilityRepositoryMock, Mockito.times(2)).save(optAvailabilityMock.get());
		Mockito.verify(bookingRepositoryMock).save(Mockito.any(Booking.class));
	}
	
	@Test
	public void testCreateWhenHotelNotAvailable() throws HotelNotFoundException, HotelNotAvailableException
	{
		CreateBookingDto createBookingDto = new CreateBookingDto(hotelMock.getId(), dateFrom, dateTo, "prueba@gmail.com");
		List<Hotel> availableHotelsMock = List.of(new Hotel(2, "Mar", 5), new Hotel(3, "Cielo", 6));
		
		Mockito.when(hotelServiceMock.findById(1)).thenReturn(hotelMock);
		Mockito.when(hotelServiceMock.findAvailableHotels(dateFrom, dateTo)).thenReturn(availableHotelsMock);
		
		Assertions.assertThrows(HotelNotAvailableException.class, () -> bookingServiceImpl.create(createBookingDto));
	}
	
	@Test
	public void testDelete() throws BookingNotFoundException
	{	
		Mockito.when(bookingRepositoryMock.findById(id)).thenReturn(optBookingMock);
		Mockito.when(availabilityRepositoryMock.findByDateAndHotel(Mockito.any(LocalDate.class), Mockito.any(Hotel.class))).thenReturn(optAvailabilityMock);
		Mockito.when(availabilityRepositoryMock.save(availabilityMock)).thenReturn(availabilityMock);
		Mockito.doNothing().when(bookingRepositoryMock).delete(bookingMock);
				
		bookingServiceImpl.delete(id);
		Assertions.assertEquals(5, availabilityMock.getRooms());
		
		Mockito.verify(bookingRepositoryMock).findById(id);
		Mockito.verify(availabilityRepositoryMock, Mockito.times(2)).findByDateAndHotel(Mockito.any(LocalDate.class), Mockito.any(Hotel.class));
		Mockito.verify(availabilityRepositoryMock, Mockito.times(2)).save(optAvailabilityMock.get());
		Mockito.verify(bookingRepositoryMock).delete(bookingMock);
	}

}
