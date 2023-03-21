package com.atsistemas.practicahotel.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.atsistemas.practicahotel.dto.CreateBookingDto;
import com.atsistemas.practicahotel.entity.Availability;
import com.atsistemas.practicahotel.entity.Booking;
import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.BookingNotFoundException;
import com.atsistemas.practicahotel.error.HotelNotAvailableException;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.repository.AvailabilityRepository;
import com.atsistemas.practicahotel.repository.BookingRepository;
import com.atsistemas.practicahotel.service.AvailabilityService;
import com.atsistemas.practicahotel.service.BookingService;
import com.atsistemas.practicahotel.service.HotelService;

@Service
public class BookingServiceImpl implements BookingService {
	
	private BookingRepository bookingRepository;
	private HotelService hotelService;
	private AvailabilityService availabilityService;
	private AvailabilityRepository availabilityRepository;

	public BookingServiceImpl(BookingRepository bookingRepository, HotelService hotelService,
			AvailabilityService availabilityService, AvailabilityRepository availabilityRepository) {
		super();
		this.bookingRepository = bookingRepository;
		this.hotelService = hotelService;
		this.availabilityService = availabilityService;
		this.availabilityRepository = availabilityRepository;
	}

	@Override
	public Booking create(CreateBookingDto createBookingDto) throws HotelNotFoundException, HotelNotAvailableException {
		
		Hotel hotel = hotelService.findById(createBookingDto.getIdHotel());
		
		//Se comprueba que se pueda hacer la reserva
		List<LocalDate> dates = createBookingDto.getDateFrom()
				.datesUntil(createBookingDto.getDateTo().plusDays(1)).collect(Collectors.toList());
		
		if(availabilityService.checkHotelAvailability(hotel, dates) == false) 
			throw new HotelNotAvailableException();
		
		//Se decrementa en una unidad las disponibilidades correspondientes
		dates.forEach((date) -> {
			Availability availability = availabilityRepository.findByDateAndHotel(date, hotel).get();
			availability.setRooms(availability.getRooms() - 1);
			availabilityRepository.save(availability);
		});
		
		//Se crea la reserva
		Booking booking = new Booking();
		
		booking.setHotel(hotel);
		booking.setDateFrom(createBookingDto.getDateFrom());
		booking.setDateTo(createBookingDto.getDateTo());
		booking.setEmail(createBookingDto.getEmail());
		
		return bookingRepository.save(booking);
	}

	@Override
	public Booking findById(Integer id) throws BookingNotFoundException {
		return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException());
	}

	@Override
	public List<Booking> findBookings(Integer idHotel, LocalDate dateFrom, LocalDate dateTo) throws HotelNotFoundException {
		Hotel hotel = hotelService.findById(idHotel);
		return bookingRepository.findByHotelAndDateFromAndDateTo(hotel, dateFrom, dateTo);
	}

	@Override
	public void delete(Integer id) throws BookingNotFoundException {
		Booking booking = this.findById(id);
		
		//Se incrementa en una unidad las disponibilidades correspondientes
		Hotel hotel = booking.getHotel();
		List<LocalDate> dates = booking.getDateFrom()
				.datesUntil(booking.getDateTo().plusDays(1)).collect(Collectors.toList());
		
		dates.forEach((date) -> {
			Availability availability = availabilityRepository.findByDateAndHotel(date, hotel).get();
			availability.setRooms(availability.getRooms() + 1);
			availabilityRepository.save(availability);
		});
		
		//Se elimina la reserva
		bookingRepository.delete(booking);
	}
}
