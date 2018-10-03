package com.adamkorzeniak.catalog.finance.service;

import java.util.List;

import com.adamkorzeniak.catalog.finance.model.Category;
import com.adamkorzeniak.catalog.finance.model.CategoryType;

public interface CategoryService {

	Category create(Category category);

	List<Category> findAllCategories();

	Category findCategory(long id);

	void updateCategory(Category current, Category updated);

	void deleteCategory(Category category);

	boolean isCategoryExist(Category category);

	List<CategoryType> getAllCategoryTypes();
}
