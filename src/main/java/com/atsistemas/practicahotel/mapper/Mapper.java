package com.atsistemas.practicahotel.mapper;

public interface Mapper <T, S> {
	
	T mapToDto(S entity);
	S mapToEntity(T dto);

}
