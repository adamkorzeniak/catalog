package com.adamkorzeniak.catalog.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adamkorzeniak.catalog.exception.ParentOfItselfException;
import com.adamkorzeniak.catalog.exception.ParentTaskNotFoundException;
import com.adamkorzeniak.catalog.task.model.Task;
import com.adamkorzeniak.catalog.task.model.TaskDTO;
import com.adamkorzeniak.catalog.task.model.TaskSearch;
import com.adamkorzeniak.catalog.task.repository.TaskRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public TaskDTO create(TaskDTO taskDTO) {
		Task task = convertDTOToEntity(taskDTO);
		task = taskRepository.save(task);
		return convertEntityToDTO(task);
	}

	public List<TaskDTO> findAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		return convertTaskstoDTOs(tasks);
	}

	@Override
	public TaskDTO findTask(long taskId) {
		Task task = taskRepository.getOne(taskId);
		return convertEntityToDTO(task);
	}

	@Override
	public TaskDTO updateTask(TaskDTO taskDTO, long taskId) {
		
		Task currentTask = taskRepository.getOne(taskId);
		if (currentTask == null) {
			return null;
		}
		currentTask.update(convertDTOToEntity(taskDTO));
		if (currentTask.getParentTask() != null && currentTask.getId().equals(currentTask.getParentTask().getId())) {
			throw new ParentOfItselfException();
		}
		currentTask = taskRepository.save(currentTask);
		return convertEntityToDTO(currentTask);
	}
	
	@Override
	public boolean deleteTask(long id) {
		Task task = taskRepository.getOne(id);
		if (task == null) {
			return false;
		}
		taskRepository.delete(task);
		return true;
	}
	
	private Task convertDTOToEntity(TaskDTO dto) {
		
		if (dto == null) {
			return null;
		}
		
		Task task = new Task();
		task.setName(dto.getName());
		task.setDescription(dto.getDescription());
		task.setMinutesEstimation(dto.getMinutesEstimation());
		task.setMinutesInvested(dto.getMinutesInvested());
		task.setDate(dto.getDate());
		task.setDeadline(dto.getDeadline());
		task.setStatus(dto.getStatus());
		if (dto.getParentTaskId() != null) {
			Task parentTask = taskRepository.getOne(dto.getParentTaskId());
			if (parentTask == null) {
				throw new ParentTaskNotFoundException();
			}
			task.setParentTask(parentTask);
		}
		return task;		
	}
	
	private TaskDTO convertEntityToDTO(Task task) {
		
		if (task == null) {
			return null;
		}
		
		TaskDTO dto = new TaskDTO();
		dto.setId(task.getId());
		dto.setName(task.getName());
		dto.setDescription(task.getDescription());
		dto.setMinutesEstimation(task.getMinutesEstimation());
		dto.setMinutesInvested(task.getMinutesInvested());
		dto.setDate(task.getDate());
		dto.setDeadline(task.getDeadline());
		dto.setStatus(task.getStatus());
		if (task.getParentTask() != null) {
			dto.setParentTaskId(task.getParentTask().getId());
		}
		return dto;
	}

	@Override
	public List<TaskDTO> searchTasks(TaskSearch taskSearch) {
		List<Task> tasks = taskRepository.search(taskSearch);
		return convertTaskstoDTOs(tasks);
	}

	private List<TaskDTO> convertTaskstoDTOs(List<Task> tasks) {
		List<TaskDTO> dtos = new ArrayList<>();
		for (Task task: tasks) {
			dtos.add(convertEntityToDTO(task));
		}
		return dtos;
	}
}
