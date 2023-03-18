package com.atsistemas.practicahotel.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.practicahotel.dto.HotelDto;
import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.mapper.HotelMapper;
import com.atsistemas.practicahotel.service.HotelService;

@RestController
@RequestMapping("hotels")
public class HotelController {
	
	private HotelService hotelService;
	private HotelMapper hotelMapper;

	public HotelController(HotelService hotelService, HotelMapper hotelMapper) {
		super();
		this.hotelService = hotelService;
		this.hotelMapper = hotelMapper;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HotelDto> getAll() {
		return hotelService.findAll().stream().map(hotelMapper::mapToDto).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelDto getById(@PathVariable Integer id) {
		return hotelMapper.mapToDto(hotelService.findById(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelDto create(@RequestBody HotelDto hotelDto) {
		Hotel hotel = hotelMapper.mapToEntity(hotelDto);
		
		return hotelMapper.mapToDto(hotelService.create(hotel));
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelDto update(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {
		Hotel hotel = hotelMapper.mapToEntity(hotelDto);
		
		return hotelMapper.mapToDto(hotelService.update(id, hotel));
	}
	
	

}
