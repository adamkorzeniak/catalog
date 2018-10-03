package com.adamkorzeniak.catalog.task.service;

import java.util.List;

import com.adamkorzeniak.catalog.task.model.Task;
import com.adamkorzeniak.catalog.task.model.TaskDTO;

public interface TaskConverter {

	Task convertToEntity(TaskDTO dto);

	TaskDTO convertToDTO(Task task);

	List<TaskDTO> convertToDTOs(List<Task> tasks);
}