package com.glaucus.task.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author pankaj.  This is the service call.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glaucus.task.controller.error.ResourceNotFoundException;
import com.glaucus.task.domain.NumberTable;
import com.glaucus.task.dto.NumberDto;
import com.glaucus.task.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Autowired
	private TaskRepository taskRepository;

	/**
	 * 
	 * Method to get the number value based on number id.
	 * 
	 * @param id id of the number to be fetched .
	 * @return NumberDto dto having id and value of number.
	 * 
	 */

	@Override
	public Optional<NumberDto> getValueById(Integer id) {
		log.debug(" TaskServiceImpl : Trying to access the Task Repository for Number with id {} ", id);
		Optional<NumberTable> numberTable = taskRepository.findById(id);
		NumberTable nt = numberTable.orElse(new NumberTable());
		log.debug("dtos : {}  returning {}", nt, Optional.ofNullable(nt));

		return Optional.ofNullable(numberTableToNumberDto(nt));
	}

	/**
	 * Map NumberTable To NumberDto
	 *
	 * @param NumberTable the entity to map
	 * @return the NumberDto
	 */
	public NumberDto numberTableToNumberDto(NumberTable nt) {
		NumberDto dto = new NumberDto();
		dto.setId(nt.getId());
		dto.setValue(nt.getValue());
		return dto;
	};

	/**
	 * 
	 * Method to set the incremented value .
	 * 
	 * @param id id of the number to be incremented.
	 * @return NumberDto dto having id and updated value of the number.
	 * 
	 * 
	 */

	@Override
	public NumberDto setIncrementedValueById(Integer id) {
		synchronized (this) {
			log.debug(
					" TaskServiceImpl : Trying to access the Task Repository for Number value to be incremented  with id {} ",
					id);
			Optional<NumberTable> numberTable = taskRepository.findById(id);
			NumberTable nt = numberTable.orElseThrow(() -> new ResourceNotFoundException("Number", "id", id));

			int i = nt.getValue();
			nt.setId(id);
			nt.setValue(++i);
			log.debug(
					" TaskServiceImpl : Trying to access the Task Repository for updating the incremented Number value with id {} ",
					id);
			NumberTable updatedTable = taskRepository.save(nt);
			return numberTableToNumberDto(updatedTable);
		}

	}

}
