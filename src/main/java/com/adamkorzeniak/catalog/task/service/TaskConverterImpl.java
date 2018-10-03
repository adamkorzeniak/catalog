package com.adamkorzeniak.catalog.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adamkorzeniak.catalog.config.exception.ParentTaskNotFoundException;
import com.adamkorzeniak.catalog.task.model.Task;
import com.adamkorzeniak.catalog.task.model.TaskDTO;
import com.adamkorzeniak.catalog.task.repository.TaskRepository;

@Service("taskConverter")
public class TaskConverterImpl implements TaskConverter {
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public Task convertToEntity(TaskDTO dto) {
		
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
	
	@Override
	public TaskDTO convertToDTO(Task task) {
		
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
	public List<TaskDTO> convertToDTOs(List<Task> tasks) {
		List<TaskDTO> dtos = new ArrayList<>();
		for (Task task: tasks) {
			dtos.add(convertToDTO(task));
		}
		return dtos;
	}
}
