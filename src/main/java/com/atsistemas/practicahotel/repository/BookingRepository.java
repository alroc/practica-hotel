package com.atsistemas.practicahotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.practicahotel.entity.Booking;


public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
