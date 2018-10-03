package com.adamkorzeniak.catalog.task.service;

import java.util.List;

import com.adamkorzeniak.catalog.task.model.Task;

public interface TaskService {

	Task create(Task task);

	List<Task> findDescendants(long parentTaskId);

	List<Task> findAllTasks();

	Task findTask(long taskId);

	Task updateTask(long taskId, Task task);

	boolean deleteTask(long taskId);

	boolean taskExists(long id);
}
