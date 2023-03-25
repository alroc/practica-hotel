package com.atsistemas.practicahotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<BookingDto> create(@Valid @RequestBody CreateBookingDto createBookingDto)
			throws HotelNotFoundException, HotelNotAvailableException {
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(bookingMapper.mapToDto(bookingService.create(createBookingDto)));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookingDto>> getBookings(@RequestParam Integer idHotel,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws HotelNotFoundException {
				
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.findBookings(idHotel, startDate, endDate)
				.stream().map(bookingMapper::mapToDto).collect(Collectors.toList()));
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingDto> getById(@PathVariable Integer id) throws BookingNotFoundException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(bookingMapper.mapToDto(bookingService.findById(id)));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws BookingNotFoundException {
		bookingService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
