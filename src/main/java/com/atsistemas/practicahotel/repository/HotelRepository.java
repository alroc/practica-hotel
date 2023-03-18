package com.atsistemas.practicahotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.practicahotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{

}
