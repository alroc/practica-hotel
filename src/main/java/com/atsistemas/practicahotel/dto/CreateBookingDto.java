package com.atsistemas.practicahotel.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateBookingDto {
	
	@NotNull
	private Integer idHotel;
	@NotNull
	private LocalDate dateFrom;
	@NotNull
	private LocalDate dateTo;
	@NotBlank
	private String email;
	
	public CreateBookingDto(Integer idHotel, LocalDate dateFrom, LocalDate dateTo, String email) {
		super();
		this.idHotel = idHotel;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.email = email;
	}
	
	public CreateBookingDto() {
		
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
