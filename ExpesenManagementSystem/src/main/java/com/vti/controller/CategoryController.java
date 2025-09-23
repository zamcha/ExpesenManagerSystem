package com.vti.controller;

import com.vti.dto.CategoryResponse;
import com.vti.entity.Category;
import com.vti.form.CategoryFilterForm;
import com.vti.service.CategoryService;
import com.vti.service.ICategoryService;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final ICategoryService categoryService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.createCategory(category));
	}

	@GetMapping
	public Page<CategoryResponse> getAllCategories(Pageable pageable, @RequestParam(value = "search") String search,
			CategoryFilterForm filterForm) {

		Page<Category> entityPages = categoryService.getAllCategory(pageable, search, filterForm);

		List<CategoryResponse> dtos = modelMapper.map(entityPages, new TypeToken<List<CategoryResponse>>() {
		}.getType());

		Page<CategoryResponse> dtosPages = new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
		
		return dtosPages;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getCategory(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		return ResponseEntity.ok(categoryService.updateCategory(id, category));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
}