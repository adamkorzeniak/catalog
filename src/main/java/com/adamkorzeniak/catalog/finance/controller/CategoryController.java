package com.adamkorzeniak.catalog.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adamkorzeniak.catalog.finance.model.Category;
import com.adamkorzeniak.catalog.finance.model.CategoryType;
import com.adamkorzeniak.catalog.finance.service.CategoryService;

@RestController
@RequestMapping("/api/finance")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/categoryTypes", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryType>> findCategoryTypes() {

		List<CategoryType> categoryTypes = categoryService.getAllCategoryTypes();
		if (categoryTypes.isEmpty()) {
			return new ResponseEntity<List<CategoryType>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CategoryType>>(categoryTypes, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> findCategories() {

		List<Category> categories = categoryService.findAllCategories();
		if (categories.isEmpty()) {
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findCategory(@PathVariable long id) {

		Category category = categoryService.findCategory(id);
		if (category == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public ResponseEntity<Void> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {

		if (categoryService.isCategoryExist(category)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		categoryService.create(category);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable long id) {

		Category currentCategory = categoryService.findCategory(id);
		if (currentCategory == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		categoryService.updateCategory(currentCategory, category);
		return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategory(@PathVariable long id) {

		Category category = categoryService.findCategory(id);
		if (category == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		categoryService.deleteCategory(category);
		return new ResponseEntity<Category>(HttpStatus.OK);
	}
}
