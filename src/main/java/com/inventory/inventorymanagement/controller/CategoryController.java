package com.inventory.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.inventorymanagement.dto.CategoryDto;
import com.inventory.inventorymanagement.dto.ResponseDto;
import com.inventory.inventorymanagement.model.Category;
import com.inventory.inventorymanagement.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/getAll")
	public ResponseDto getCategories() {
		List<CategoryDto> categoryDtos = categoryService.getCategories();
		return new ResponseDto(200, "Categories fetched successfully", categoryDtos);
	}
	
	@GetMapping("/get/{categoryId}")
	public ResponseDto getCategory(@PathVariable Long categoryId) {
		CategoryDto categoryDto = categoryService.getCategoryDto(categoryId);
		return new ResponseDto(200, "Category fetched successfully", categoryDto);
	}
	
	@PostMapping("/add")
	public ResponseDto addCategory(@RequestBody Category category) {
		CategoryDto categoryDto = categoryService.addCategory(category);
		return new ResponseDto(200, "Category added successfully", categoryDto);
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseDto updateCategory(@PathVariable long categoryId, @RequestBody Category category) {
		CategoryDto categoryDto = categoryService.updateCategory(categoryId, category);
		return new ResponseDto(200, "Category updated successfully", categoryDto);
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseDto deleteCategory(@PathVariable long categoryId) {
		String message = new String();
		if(categoryService.deleteCategory(categoryId))
			message="Category deleted successfully";
		else
			message="Category not deleted";
		return new ResponseDto(200, message);
			
	}
}
