package com.atsistemas.practicahotel.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.atsistemas.practicahotel.dto.ErrorDto;
import com.atsistemas.practicahotel.dto.ValidationErrorDto;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = HotelNotFoundException.class)
	public ResponseEntity<Object> handlePruebaException(HotelNotFoundException ex) {
		return new ResponseEntity<>(new ErrorDto("404", ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		return new ResponseEntity<Object>(
				new ValidationErrorDto("400", errors), HttpStatus.BAD_REQUEST);
	}

}
