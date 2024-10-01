package com.sheryians.major.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sheryians.major.dto.ProductDTO;

import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;


@Controller
public class adminController {
	
	public static String UploadDir = System.getProperty("user.dir")+ "/src/main/resources/static/productImages";

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/admin")
	public String adminHome () {
		return "adminHome";
	}
	
	// manage pe click kiya
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories",categoryService.getCategory());
		return "categories";
	}
	
	@GetMapping("/admin/products")
	public String getProducts(Model model) {
		model.addAttribute("products",productService.getProduct());
		return "products";
	}
	
	// add pe click kiya 
	@GetMapping("/admin/products/add")
	public String getAddProducts(Model model) {
		model.addAttribute("productDTO",new ProductDTO());
		model.addAttribute("categories",categoryService.getCategory());
		return "productsAdd";
	}
	
	// add pe click kiya
	
	@GetMapping("/admin/categories/add")
	public String getaadCat(Model model) {
		model.addAttribute("category",new Category());
		return "categoriesAdd";
	}
	
	
	//   save kiya
	@PostMapping("/admin/products/add")
	public String getAddProducts(@ModelAttribute("productDTO")ProductDTO productDTO, @RequestParam("productImage")MultipartFile file,@RequestParam("imgName")String imgName) throws IOException {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		///  productionDTOek category id dega, usse categorynikalkr product vale categor ko denge
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imageUUID; 
		
		// this is syntax of saving image file
		// file.getBytes(), file name , and file name and path
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(UploadDir,imageUUID);
			Files.write(fileNameAndPath , file.getBytes());
		}else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		
		productService.addProduct(product);
		
		return "redirect:/admin/products";
		
	}

	@PostMapping("/admin/categories/add")
	public String getaadCat(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
    //  delete kiya
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProducts(@PathVariable long id) {
		productService.removeProduct(id);
		return "redirect:/admin/products";
		
	}
	 /// product se product DTO mein dalna
	@GetMapping("/admin/product/update/{id}")
	public String updateProducts(@PathVariable long id, Model model) {
		Product product = productService.getProductByID(id).get();
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("Categor",categoryService.getCategory());
		model.addAttribute("productDTO", productDTO);
		
		return "productsAdd";
	}
	
	
	@RequestMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
		return "redirect:/admin/categories";
			
}
}
