package com.atsistemas.practicahotel.controller;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.practicahotel.dto.OpenAvailabilityDto;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.service.AvailabilityService;

@RestController
@RequestMapping("availabilities")
public class AvailabilityController {
	
	private AvailabilityService availabilityService;
	
	public AvailabilityController(AvailabilityService availabilityService) {
		super();
		this.availabilityService = availabilityService;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> open(@Valid @RequestBody OpenAvailabilityDto openAvailabilityDto) throws HotelNotFoundException {
		
		availabilityService.open(openAvailabilityDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
