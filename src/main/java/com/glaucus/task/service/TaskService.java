package com.glaucus.task.service;

import java.util.Optional;

import com.glaucus.task.controller.error.ResourceNotFoundException;

/*
 * @author pankaj This interface invokes service class from controller.
 */


import com.glaucus.task.dto.NumberDto;

public interface TaskService {

	/**
	 * 
	 * Method to get the number value based on number id.
	 * 
	 * @param id
	 *            id of the number to be fetched .
	 * @return Optional<NumberDto>
	 *              dto having id and value of number.
	 * 
	 */
	
	Optional<NumberDto> getValueById(Integer id);
	
	/**
	 * 
	 * Method to set the incremented value .
	 * 
	 * @param id
	 *            id of the number to be incremented.
	 * @return void.
	 * @throws ResourceNotFoundException 
	 * 
	 */
	
	
	NumberDto setIncrementedValueById(Integer id)   ;

}
