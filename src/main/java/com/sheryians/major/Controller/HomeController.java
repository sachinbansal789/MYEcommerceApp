package com.sheryians.major.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping({"/" , "/home"})
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}
	
	@GetMapping("/shop")
	public String Shop(Model model) {
		model.addAttribute("categories", categoryService.getCategory());
		model.addAttribute("products", productService.getProduct());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	@GetMapping("/shop/viewproduct/{id}")	
	public String getProduct(Model model , @PathVariable long id) {
		model.addAttribute("product",productService.getProductByID(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "viewProduct";
	}
	
	@GetMapping("/shop/category/{id}")
	public String category(Model model, @PathVariable int id) {
		model.addAttribute("products",productService.getProductbyCategoryid(id));
		model.addAttribute("categories", categoryService.getCategory());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
		
	}
	
	
	

}
