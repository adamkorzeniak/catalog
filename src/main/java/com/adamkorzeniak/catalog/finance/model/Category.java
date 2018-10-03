package com.adamkorzeniak.catalog.finance.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	
    @OneToMany(mappedBy="parentCategory", fetch=FetchType.LAZY)
	private List<Category> childrenCategories;
    
    @ManyToOne ( fetch=FetchType.LAZY )
    @JoinColumn( name="parent_id", nullable=true)
	private Category parentCategory;
	private CategoryType type;
	private boolean isActive;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getParent() {
		return parentCategory;
	}

	public void setParent(Category parent) {
		this.parentCategory = parent;
	}

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
