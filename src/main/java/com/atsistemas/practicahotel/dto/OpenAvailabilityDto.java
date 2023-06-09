package com.atsistemas.practicahotel.dto;

import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class OpenAvailabilityDto {
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private LocalDate dateFrom;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private LocalDate dateTo;
	
	@NotNull
	private Integer idHotel;
	
	@NotNull
	private Integer rooms;
	
	public OpenAvailabilityDto(LocalDate dateFrom, LocalDate dateTo, Integer idHotel, Integer rooms) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.idHotel = idHotel;
		this.rooms = rooms;
	}
	
	public OpenAvailabilityDto() {
		
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}
	
}
