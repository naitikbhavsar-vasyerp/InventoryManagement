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
import com.inventory.inventorymanagement.dto.ProductDto;
import com.inventory.inventorymanagement.dto.RequestPayloadDto;
import com.inventory.inventorymanagement.dto.ResponseDto;
import com.inventory.inventorymanagement.model.Product;
import com.inventory.inventorymanagement.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/getAll")
	public ResponseDto getProducts(){
		List<ProductDto> productDtos =  productService.getProducts();
		return new ResponseDto(200, "Products fetched successfully", productDtos);
	}
	
	@GetMapping("/get/{productId}")
	public ResponseDto getProduct(@PathVariable("productId") long productId){
		ProductDto productDto =  productService.getProduct(productId);
		return new ResponseDto(200, "Product fetched successfully", productDto);
	}
	
	@GetMapping("/getCategory/{productId}")
	public ResponseDto getProductCategory(@PathVariable("productId") long productId){
		CategoryDto categoryDto =  productService.getProductCategory(productId);
		return new ResponseDto(200, "Category fetched successfully", categoryDto);
	}
	
	@GetMapping("/getProducts/{categoryId}")
	public ResponseDto getProductsByCategoryId(@PathVariable("categoryId") long categoryId) {
		List<ProductDto> productDtos = productService.findByCategory(categoryId);
		return new ResponseDto(200, "Products fetched by category successfully", productDtos);
	}
	
	@PostMapping("/add")
	public ResponseDto saveProduct(@RequestBody Product product) {
		ProductDto productDto = productService.addProduct(product);
		return new ResponseDto(200, "Product added successfully", productDto);
	}
	
	@PostMapping("/assignCategory")
	public ResponseDto assignCategory(@RequestBody RequestPayloadDto requestPayload) {
		ProductDto productDto = productService.assignCategory(requestPayload.productId, requestPayload.categoryId);
		return new ResponseDto(200, "Category assigned to product successfully", productDto);
	}
	
	@PutMapping("/update/{productId}")
	public ResponseDto updateProduct(@PathVariable long productId, @RequestBody Product product) {	
		ProductDto productDto = productService.updateProduct(productId, product);
		return new ResponseDto(200, "Product updated successfully", productDto);
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseDto deleteProduct(@PathVariable("productId") Long productId) {
		String message = new String();
		int status = 500;
		if(productService.deleteProduct(productId)) {
			message = "Product deleted successfully";
			status=200;
		}
		else {
			message = "Product not deleted";
		}
		
		return new ResponseDto(status, message);
	}
	    
}
