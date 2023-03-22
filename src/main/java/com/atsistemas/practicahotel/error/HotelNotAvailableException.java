package com.atsistemas.practicahotel.error;

public class HotelNotAvailableException extends Exception {

	private static final long serialVersionUID = -5230885203176794430L;
	
	private static final String message = "El hotel no tiene disponibilidad para esas fechas";
	
	public HotelNotAvailableException() {
		super(message);
	}

}
