package com.adamkorzeniak.catalog.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adamkorzeniak.catalog.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {

}
