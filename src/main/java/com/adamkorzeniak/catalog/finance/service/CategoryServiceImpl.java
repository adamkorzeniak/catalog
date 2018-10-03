package com.adamkorzeniak.catalog.finance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adamkorzeniak.catalog.finance.model.Category;
import com.adamkorzeniak.catalog.finance.model.CategoryType;
import com.adamkorzeniak.catalog.finance.repository.CategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category create(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	@Override
	public Category findCategory(long id) {
		return categoryRepository.getOne(id);
	}

	@Override
	public void updateCategory(Category current, Category updated) {
		current.setActive(updated.isActive());
		current.setDescription(updated.getDescription());
		current.setName(updated.getName());
		current.setParent(updated.getParent());
		current.setType(updated.getType());
		categoryRepository.save(current);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);;
	}

	@Override
	public boolean isCategoryExist(Category category) {
		return findCategory(category.getId()) != null;
	}

	@Override
	public List<CategoryType> getAllCategoryTypes() {
		List<CategoryType> categoryTypes = new ArrayList<>();
		for (CategoryType ct : CategoryType.values()) {
		    categoryTypes.add(ct);
		}
		return categoryTypes;
	}

}
