package com.adamkorzeniak.catalog.management.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Project {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private List<TaskGroup> taskGroups;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskGroup> getTaskGroups() {
		return taskGroups;
	}

	public void setTaskGroups(List<TaskGroup> taskGroups) {
		this.taskGroups = taskGroups;
	}
}