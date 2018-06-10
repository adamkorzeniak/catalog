package com.adamkorzeniak.catalog.task.service;

import java.util.List;

import com.adamkorzeniak.catalog.task.model.TaskDTO;
import com.adamkorzeniak.catalog.task.model.TaskSearch;

public interface TaskService {

	TaskDTO create(TaskDTO dto);

	List<TaskDTO> findAllTasks();

	TaskDTO findTask(long taskId);

	TaskDTO updateTask(TaskDTO dto, long id);

	boolean deleteTask(long id);

	List<TaskDTO> searchTasks(TaskSearch taskSearch);
}
