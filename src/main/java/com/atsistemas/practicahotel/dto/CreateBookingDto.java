package com.atsistemas.practicahotel.dto;

import java.time.LocalDate;

public class CreateBookingDto {
	
	private Integer id;
	private Integer idHotel;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private String email;
	
	public CreateBookingDto(Integer id, Integer idHotel, LocalDate dateFrom, LocalDate dateTo, String email) {
		super();
		this.id = id;
		this.idHotel = idHotel;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.email = email;
	}
	
	public CreateBookingDto() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
