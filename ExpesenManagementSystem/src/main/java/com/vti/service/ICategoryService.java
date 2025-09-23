package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Category;
import com.vti.form.CategoryFilterForm;

public interface ICategoryService {

	Category createCategory(Category category);

	Category getCategory(Long id);

	Category updateCategory(Long id, Category categoryDetails);
	
	void deleteCategory(Long id);
	
	Page<Category> getAllCategory(Pageable pageable, String search, CategoryFilterForm filterForm);
}
