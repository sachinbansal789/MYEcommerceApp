package com.sheryians.major.Controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.ProductService;

import lombok.var;

import com.razorpay.*;

@Controller
public class cartController {
	
	@Autowired
	ProductService productService;
	

	
	
	@GetMapping("/addToCart/{id}")
	public String addToCart( @PathVariable int id) {
		
		GlobalData.cart.add(productService.getProductByID(id).get());
	
		return "redirect:/shop";
		
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		
		model.addAttribute("cartCount", GlobalData.cart.size());
	//	model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("total", GlobalData.cart.stream().mapToInt(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		
		return "cart";	
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String removeItem(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("total", GlobalData.cart.stream().mapToInt(Product::getPrice).sum());
		
		return "checkout";
	}
	
	@PostMapping("/create_order")
	// we are using responsebody to pass return type directly as object not view
	@ResponseBody()
	// we are using Requestbody here ,we are getting data from javascript file ,with attribute name data which has string and object
	public String createOrder(@RequestBody Map<String,Object> data) throws Exception {
		
		int amt =Integer.parseInt(data.get("amount").toString());
		
	//	RazorpayClient client = new RazorpayClient("rzp_test_LVyNV4y0TFmjRQ", "ZHRlONGiIWqON4qmmwmrJue5");
		
	//	OR from java 11
		
		RazorpayClient client = new RazorpayClient("rzp_test_oP6Z4wsNNSUTPJ", "46aG56FG1iLaCgkitab7SrnP");
		
		// create a json object
		
		JSONObject obj= new JSONObject();
		
		obj.put("amount", amt*100);
		obj.put("currency","INR");
		obj.put("receipt","txn_235425");
		
		// Creating new order using jsonobject obj and razorpay api
		
		// razorpay ki api se order ka json object banaya
		
		Order order=client.orders.create(obj);
		
		System.out.println(order);
		
		return order.toString();
}

}
