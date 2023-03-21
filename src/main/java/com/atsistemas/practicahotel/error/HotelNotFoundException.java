package com.atsistemas.practicahotel.error;

public class HotelNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3053558590594696696L;
	private static final String message = "Hotel no encontrado";
	

	public HotelNotFoundException() {
		super(message);
	}
	
}
