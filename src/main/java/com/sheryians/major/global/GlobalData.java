package com.sheryians.major.global;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sheryians.major.model.Product;

public class GlobalData {
	
	@Autowired
	Product product;
	
	public static List<Product> cart;
	
	static {
		cart = new ArrayList<Product>();
	}

}
