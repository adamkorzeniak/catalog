package com.adamkorzeniak.catalog.task.repository;

import java.util.List;

import com.adamkorzeniak.catalog.task.model.Task;
import com.adamkorzeniak.catalog.task.model.TaskSearch;

public interface TaskRepositoryCustom {

	public List<Task> search(TaskSearch task);
}
