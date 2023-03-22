package com.atsistemas.practicahotel.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.practicahotel.dto.HotelDto;
import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.mapper.Mapper;
import com.atsistemas.practicahotel.service.HotelService;

@RestController
@RequestMapping("hotels")
public class HotelController {
	
	private HotelService hotelService;
	private Mapper<HotelDto, Hotel> hotelMapper;

	public HotelController(HotelService hotelService, Mapper<HotelDto, Hotel> hotelMapper) {
		super();
		this.hotelService = hotelService;
		this.hotelMapper = hotelMapper;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HotelDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.findAll().stream()
				.map(hotelMapper::mapToDto).collect(Collectors.toList()));
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelDto> getById(@PathVariable Integer id) throws HotelNotFoundException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(hotelMapper.mapToDto(hotelService.findById(id)));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelDto> create(@Valid @RequestBody HotelDto hotelDto) {		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(hotelMapper.mapToDto(hotelService.create(hotelMapper.mapToEntity(hotelDto))));		
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<HotelDto> update(@PathVariable Integer id,
			@RequestBody HotelDto hotelDto) throws HotelNotFoundException {		
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(hotelMapper.mapToDto(hotelService.update(id, hotelMapper.mapToEntity(hotelDto))));
	}
}
