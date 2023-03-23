package com.atsistemas.practicahotel.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.atsistemas.practicahotel.entity.Hotel;
import com.atsistemas.practicahotel.error.HotelNotFoundException;
import com.atsistemas.practicahotel.repository.HotelRepository;
import com.atsistemas.practicahotel.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	private HotelRepository hotelRepository;

	public HotelServiceImpl(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}

	@Override
	public List<Hotel> findAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel findById(Integer id) throws HotelNotFoundException {
		return hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException());
	}

	@Override
	public Hotel create(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel update(Integer id, Hotel updatedHotel) throws HotelNotFoundException {
		Hotel hotel = findById(id);
		String name = updatedHotel.getName();
		Integer category = updatedHotel.getCategory();
		
		if(name != null)
			hotel.setName(name);
		
		if(category != null)
			hotel.setCategory(category);
		
		return hotelRepository.save(hotel);
	}
	
	@Override
	public List<Hotel> findHotels(String name, Integer category, LocalDate dateFrom, LocalDate dateTo) {
		Specification<Hotel> spec = Specification.where(null);
		
		List<Integer> hotels = findAvailableHotels(dateFrom, dateTo)
				.stream().map(Hotel::getId).collect(Collectors.toList());
		
		Specification<Hotel> availableHotelsSpec = availableHotelsFilter(hotels);
		spec = spec.and(availableHotelsSpec);

		if (name != null) {
			Specification<Hotel> nameSpec = nameFilter(name);
			spec = spec.and(nameSpec);
		}

		if (category != null) {
			Specification<Hotel> categorySpec = categoryFilter(category);
			spec = spec.and(categorySpec);
		}

		return hotelRepository.findAll(spec);
	}
		
	private Specification<Hotel> categoryFilter(Integer category) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
	}
	
	private Specification<Hotel> nameFilter(String name) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
	}
	
	private Specification<Hotel> availableHotelsFilter(List<Integer> hotels) {
		
		return (root, query, criteriaBuilder) -> root.get("id").in(hotels);
	}
	
	public List<Hotel> findAvailableHotels(LocalDate dateFrom, LocalDate dateTo)
	{
		Long days = dateFrom.until(dateTo.plusDays(1), ChronoUnit.DAYS);
		
		return hotelRepository.findAvailableHotels(dateFrom, dateTo, days);
	}
	
}
