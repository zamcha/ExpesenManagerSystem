package com.vti.service;

import com.vti.entity.Category;
import com.vti.form.CategoryFilterForm;
import com.vti.repository.CategoryRepository;
import com.vti.specification.CategorySpecification;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

	private final CategoryRepository categoryRepository;

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category getCategory(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy category"));
	}

	public Category updateCategory(Long id, Category categoryDetails) {
		Category category = getCategory(id);
		category.setName(categoryDetails.getName());
		return categoryRepository.save(category);
	}

	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Page<Category> getAllCategory(Pageable pageable, String search, CategoryFilterForm filterForm) {

		Specification<Category> where = CategorySpecification.buildWhere(search, filterForm);
		return categoryRepository.findAll(where, pageable);
	}
}