package com.inventory.inventorymanagement.service;

import java.util.List;

import com.inventory.inventorymanagement.dto.CategoryDto;
import com.inventory.inventorymanagement.dto.ProductDto;
import com.inventory.inventorymanagement.model.Product;

public interface ProductService {
	public List<ProductDto> getProducts();
	public ProductDto getProduct(long productId);
	public CategoryDto getProductCategory(long productId);
	public ProductDto addProduct(Product product);
	public boolean deleteProduct(long productId);
	public ProductDto updateProduct(long productId, Product product);
	public ProductDto assignCategory(long productId, long categoryId);
	public List<ProductDto> findByCategory(long categoryId);
}
