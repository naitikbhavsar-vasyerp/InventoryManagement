package com.inventory.inventorymanagement.service;

import java.util.List;

import com.inventory.inventorymanagement.dto.CategoryDto;
import com.inventory.inventorymanagement.model.Category;

public interface CategoryService {
	public List<CategoryDto> getCategories();
	public CategoryDto getCategoryDto(long categoryId);
	public Category getCategory(long categoryId);
	public CategoryDto addCategory(Category category);
	public boolean deleteCategory(long categoryId);
	public CategoryDto updateCategory(long categoryId, Category category);
}
