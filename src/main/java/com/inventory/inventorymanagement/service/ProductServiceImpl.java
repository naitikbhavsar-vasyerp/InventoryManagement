package com.inventory.inventorymanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.inventorymanagement.dto.CategoryDto;
import com.inventory.inventorymanagement.dto.ProductDto;
import com.inventory.inventorymanagement.exception.ProductNotFoundException;
import com.inventory.inventorymanagement.model.Category;
import com.inventory.inventorymanagement.model.Product;
import com.inventory.inventorymanagement.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryService categoryService;

	@Override
	public List<ProductDto> getProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> productDtos = new ArrayList<>();
		for (Product product : products) {
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			productDtos.add(productDto);
		}
		return productDtos;
	}

	@Override
	public ProductDto getProduct(long productId) {
		Product product = new Product();
		product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found"));
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public ProductDto addProduct(Product product) {
		Product product2 = new Product();
		try {
			product2 = productRepository.save(product);
		} catch (Exception e) {
			new Exception("product passed is null");
		}
		ProductDto productDto = modelMapper.map(product2, ProductDto.class);
		return productDto;
	}

	@Override
	public boolean deleteProduct(long productId) {
		boolean b = false;
		try {
			if (productRepository.existsById(productId)) {
				productRepository.deleteById(productId);
				b = true;
			}
		} catch (Exception e) {
			new RuntimeException("product does not exist");
		}

		return b;
	}

	@Override
	public ProductDto updateProduct(long productId, Product product) {
		Product product2 = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found while updating info"));
		if (product.getProductName() != null)
			product2.setProductName(product.getProductName());
		if (product.getProductDescription() != null)
			product2.setProductDescription(product.getProductDescription());
		if (product.getProductBrand() != null)
			product2.setProductBrand(product.getProductBrand());
		if (product.getProductPrice() >= 0)
			product2.setProductPrice(product.getProductPrice());
		if (product.getCategory() != null) {
			product2.setCategory(product.getCategory());
			Category category = product.getCategory();
			categoryService.updateCategory(category.getCategoryId(), category);
		}
		return new ModelMapper().map(product2, ProductDto.class);
	}

	@Override
	public ProductDto assignCategory(long productId, long categoryId) {
		Category category = categoryService.getCategory(categoryId);
		
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found while assigning category"));
		product.setCategory(category);
		productRepository.save(product);
		return new ModelMapper().map(product, ProductDto.class);
	}

	@Override
	public CategoryDto getProductCategory(long productId) {
		
		return modelMapper.map(categoryService.getCategory(productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found")).getCategory().getCategoryId()), CategoryDto.class);
	}

	@Override
	public List<ProductDto> findByCategory(long categoryId) {
		List<Product> products = productRepository.findByCategoryId(categoryId);
		List<ProductDto> productDtos = new ArrayList<>();
		for (Product product : products) {
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			productDtos.add(productDto);
		}
		return productDtos;
	}

}
