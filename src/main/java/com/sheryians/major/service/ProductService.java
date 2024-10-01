package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.dto.ProductDTO;
import com.sheryians.major.model.Product;
import com.sheryians.major.repository.ProductRepository;

@Service
public class ProductService {
	
	
	@Autowired
	ProductRepository productRepository;
	
	 
	// add data
	
	public void addProduct(Product product) {
		productRepository.save(product );
	}
	
	// get data
	
	public List<Product> getProduct(){
		return productRepository.findAll();
	}
	
	// remove data
	
	public void removeProduct(long id) {
		productRepository.deleteById(id);
	}
	
	public Optional<Product> getProductByID(long id){
		 return productRepository.findById(id);
	}
	
	public List<Product> getProductbyCategoryid(int id){
		return productRepository.findAllByCategory_Id(id);
		
	}

}
