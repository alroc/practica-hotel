package com.atsistemas.practicahotel.service;

import com.atsistemas.practicahotel.dto.OpenAvailabilityDto;
import com.atsistemas.practicahotel.error.HotelNotFoundException;

public interface AvailabilityService {

	void open(OpenAvailabilityDto openAvailabilityDto) throws HotelNotFoundException;
	
}
