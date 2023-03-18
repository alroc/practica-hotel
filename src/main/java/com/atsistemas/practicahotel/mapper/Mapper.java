package com.atsistemas.practicahotel.mapper;

public interface Mapper <T, S>{
	
	public T mapToDto(S entity);
	public S mapToEntity(T dto);

}
