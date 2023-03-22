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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.practicahotel.dto.HotelDto;
import com.atsistemas.practicahotel.dto.OpenAvailabilityDto;
import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.mapper.Mapper;
import com.atsistemas.practicahotel.service.AvailabilityService;

@RestController
@RequestMapping("availabilities")
public class AvailabilityController {
	
	private AvailabilityService availabilityService;
	private Mapper<HotelDto, Hotel> hotelMapper;
	
	public AvailabilityController(AvailabilityService availabilityService, Mapper<HotelDto,Hotel> hotelMapper) {
		super();
		this.availabilityService = availabilityService;
		this.hotelMapper = hotelMapper;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> open(@Valid @RequestBody OpenAvailabilityDto openAvailabilityDto) throws HotelNotFoundException {
		
		availabilityService.open(openAvailabilityDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HotelDto>> getAvailableHotels(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer category) {

		return ResponseEntity.status(HttpStatus.OK).body(availabilityService.findAvailableHotels(dateFrom, dateTo, name, category)
				.stream().map(hotelMapper::mapToDto).collect(Collectors.toList()));
	}
}
