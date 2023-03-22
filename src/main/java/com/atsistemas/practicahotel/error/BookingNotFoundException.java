package com.atsistemas.practicahotel.error;

public class BookingNotFoundException extends Exception {

	private static final long serialVersionUID = 5872148889695014808L;
	private static final String message = "Reserva no encontrada";

	public BookingNotFoundException() {
		super(message);
	}

}
