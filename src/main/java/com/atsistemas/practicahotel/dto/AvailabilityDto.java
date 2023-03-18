package com.atsistemas.practicahotel.dto;

import java.util.Date;

public class AvailabilityDto {
	
	private Integer id;
	
	private Date date;
	
	private HotelDto hotel;

	private Integer rooms;

	public AvailabilityDto(Integer id, Date date, HotelDto hotel, Integer rooms) {
		super();
		this.id = id;
		this.date = date;
		this.hotel = hotel;
		this.rooms = rooms;
	}
	
	public AvailabilityDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public HotelDto getHotel() {
		return hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

}
