package com.atsistemas.practicahotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.practicahotel.dto.BookingDto;
import com.atsistemas.practicahotel.dto.CreateBookingDto;
import com.atsistemas.practicahotel.entity.Booking;
import com.atsistemas.practicahotel.error.BookingNotFoundException;
import com.atsistemas.practicahotel.error.HotelNotAvailableException;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.mapper.Mapper;
import com.atsistemas.practicahotel.service.BookingService;

@RestController
@RequestMapping("bookings")
public class BookingController {
	
	private BookingService bookingService;
	private Mapper<BookingDto, Booking> bookingMapper;
	
	public BookingController(BookingService bookingService, Mapper<BookingDto, Booking> bookingMapper) {
		super();
		this.bookingService = bookingService;
		this.bookingMapper = bookingMapper;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingDto create(@RequestBody CreateBookingDto createBookingDto) throws HotelNotFoundException, HotelNotAvailableException {
		
		return bookingMapper.mapToDto(bookingService.create(createBookingDto));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookingDto>> getBookings(@RequestParam Integer idHotel,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) throws HotelNotFoundException {

		List<BookingDto> bookingsDto = bookingService.findBookings(idHotel, dateFrom, dateTo)
				.stream().map(bookingMapper::mapToDto).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(bookingsDto);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingDto> getById(@PathVariable Integer id) throws BookingNotFoundException {
		BookingDto bookingDto = bookingMapper.mapToDto(bookingService.findById(id));
		
		return ResponseEntity.status(HttpStatus.OK).body(bookingDto);
	}
	
	

}
