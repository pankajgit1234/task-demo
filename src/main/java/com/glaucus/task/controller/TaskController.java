package com.glaucus.task.controller;
/*
 * @author pankaj This controller invokes GET and PUT APIs for number increment task .
 */

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.glaucus.task.controller.error.ResourceNotFoundException;
import com.glaucus.task.dto.NumberDto;
import com.glaucus.task.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

	private static final Logger log = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;

	/**
	 * GET /demo-task : get Number based on id
	 *
	 * @param id id of the number
	 * @return the ResponseEntity with status 200 (OK) and NumberDto in body
	 * 
	 */

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NumberDto> getNumberById(@PathVariable Integer id) {
		log.debug(" TaskController : Trying to access the Task service for Number with id {} ", id);
		Optional<NumberDto> data = taskService.getValueById(id);
		log.debug("Data : {} : {}", data, data.isPresent());
		return Optional.ofNullable(data.get()).map(result -> new ResponseEntity<NumberDto>(data.get(), HttpStatus.OK))
				.orElse(new ResponseEntity<NumberDto>(HttpStatus.NOT_FOUND));
	}

	/**
	 * PUT /demo-task : save incremented Number based on id
	 *
	 * @param id id of the number
	 * @return NumberDto 
	 * 
	 */

	@RequestMapping(value = "/id/{id}", method = RequestMethod.PUT)
	public NumberDto updateValue(@PathVariable Integer id)  {
		log.debug(" TaskController : Trying to access the Task service to increment  number value with id {} ", id);
		return taskService.setIncrementedValueById(id);
	}
}
