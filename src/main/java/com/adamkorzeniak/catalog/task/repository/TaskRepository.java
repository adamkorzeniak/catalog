package com.adamkorzeniak.catalog.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adamkorzeniak.catalog.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByParentTask(Task task);
	List<Task> findByParentTaskIsNull();
	void deleteByIdIn(List<Long> ids);
}
