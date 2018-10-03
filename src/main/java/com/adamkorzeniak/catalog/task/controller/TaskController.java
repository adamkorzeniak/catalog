package com.adamkorzeniak.catalog.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adamkorzeniak.catalog.config.exception.ParentTaskNotFoundException;
import com.adamkorzeniak.catalog.task.model.Task;
import com.adamkorzeniak.catalog.task.model.TaskDTO;
import com.adamkorzeniak.catalog.task.service.TaskConverter;
import com.adamkorzeniak.catalog.task.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskConverter taskConverter;

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public ResponseEntity<List<TaskDTO>> findTasks(@RequestParam(value="parentTaskId", required=false) Long parentTaskId) {
		
		List<Task> tasks = null;
		if (parentTaskId != null) {
			tasks = taskService.findDescendants(parentTaskId);
		} else {
			tasks = taskService.findAllTasks();
		}
		
		if (tasks.isEmpty()) {
			return new ResponseEntity<List<TaskDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TaskDTO>>(taskConverter.convertToDTOs(tasks), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
	public ResponseEntity<TaskDTO> findTask(@PathVariable long id) {

		Task task = taskService.findTask(id);
		if (task == null) {
			return new ResponseEntity<TaskDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TaskDTO>(taskConverter.convertToDTO(task), HttpStatus.OK);
	}

	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
	public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO, UriComponentsBuilder ucBuilder) {

		if (taskDTO.getParentTaskId() != null && !taskService.taskExists(taskDTO.getParentTaskId())) {
			throw new ParentTaskNotFoundException();
		}
		
		Task task = taskService.create(taskConverter.convertToEntity(taskDTO));
		return new ResponseEntity<TaskDTO>(taskConverter.convertToDTO(task), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
	public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable long id) {

		if (!taskService.taskExists(id)) {
			return new ResponseEntity<TaskDTO>(HttpStatus.NOT_FOUND);
		}
		Task task = taskService.updateTask(id, taskConverter.convertToEntity(taskDTO));
		return new ResponseEntity<TaskDTO>(taskConverter.convertToDTO(task), HttpStatus.OK);
	}

	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<TaskDTO> deleteTask(@PathVariable long id) {

		if (!taskService.taskExists(id)) {
			return new ResponseEntity<TaskDTO>(HttpStatus.NOT_FOUND);
		}
		taskService.deleteTask(id);
		return new ResponseEntity<TaskDTO>(HttpStatus.NO_CONTENT);
	}
	
}
