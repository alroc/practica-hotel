package com.atsistemas.practicahotel.dto;

import java.util.List;

public class ValidationErrorDto {
	
	private String code;
	private List<String> errors;
	
	public ValidationErrorDto() {
		
	}
	
	public ValidationErrorDto(String code, List<String> errors) {
		super();
		this.code = code;
		this.errors = errors;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
