package com.inventory.inventorymanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.inventorymanagement.dto.CategoryDto;
import com.inventory.inventorymanagement.exception.CategoryNotFoundException;
import com.inventory.inventorymanagement.model.Category;
import com.inventory.inventorymanagement.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoryDtos = new ArrayList<>();
		for (Category category : categories) {
			CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
			categoryDtos.add(categoryDto);
		}
		return categoryDtos;
	}

	@Override
	public CategoryDto getCategoryDto(long categoryId) {
		Category category = new Category();
		category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public Category getCategory(long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
		return category;
	}
	
	@Override
	public CategoryDto addCategory(Category category) {
		Category category2 = new Category();
		try {
			category2 = categoryRepository.save(category);
		} catch (Exception e) {
			new Exception("Category passed is null");
		}
		CategoryDto categoryDto = modelMapper.map(category2, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(long categoryId, Category category) {
		Category category2 = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
		category2.setCategoryId(categoryId);
		if (category.getCategoryName() != null)
			category2.setCategoryName(category.getCategoryName());
		if (category.getCategoryDescription() != null)
			category2.setCategoryDescription(category.getCategoryDescription());
		categoryRepository.save(category2);
		CategoryDto categoryDto = modelMapper.map(category2, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public boolean deleteCategory(long categoryId) {
		boolean b = false;
		try {
			if (categoryRepository.existsById(categoryId)) {
				categoryRepository.deleteById(categoryId);
				b = true;
			}
		} catch (Exception e) {
			new RuntimeException("CategoryId is null");
		}

		return b;
	}

}
