package com.adamkorzeniak.catalog.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adamkorzeniak.catalog.config.exception.ParentOfItselfException;
import com.adamkorzeniak.catalog.task.model.Task;
import com.adamkorzeniak.catalog.task.repository.TaskRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task create(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> findAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> findDescendants(long parentTaskId) {
		if (parentTaskId < 1) {
			return taskRepository.findByParentTaskIsNull();
		}
		Task parentTask = taskRepository.getOne(parentTaskId);
		if (parentTask!= null) {
			return taskRepository.findByParentTask(taskRepository.getOne(parentTaskId));
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Task findTask(long taskId) {
		return taskRepository.getOne(taskId);
	}

	@Override
	public Task updateTask(long taskId, Task task) {
		
		Task currentTask = taskRepository.getOne(taskId);
		currentTask.update(task);
		if (currentTask.getParentTask() != null && currentTask.getId().equals(currentTask.getParentTask().getId())) {
			throw new ParentOfItselfException();
		}
		return taskRepository.save(currentTask);
	}
	
	@Override
	@Transactional
	public boolean deleteTask(long id) {
		List<Long> ids = new ArrayList<>();
		ids.add(id);
		
		for (int i = 0; i < ids.size(); i++) {
			List<Task> descendants = findDescendants(ids.get(i));
			for (Task t: descendants) {
				ids.add(t.getId());
			}
		}
		
		taskRepository.deleteByIdIn(ids);
		return true;
	}
	
	@Override
	public boolean taskExists(long id) {
		return taskRepository.existsById(id);
	}

}
