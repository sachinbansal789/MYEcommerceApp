package com.sheryians.major.Controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.Binding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sheryians.major.Helper.Message;
import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.repository.UserRepository;

@Controller
public class LoginController {
	
	
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;

	

	@GetMapping("/login")  
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	
	@PostMapping("/do_register")
	public String registerPost(@ModelAttribute("User") User user ,BindingResult result , HttpServletRequest request,Model model,
			HttpSession session  ) throws ServletException{


		
//	List<Role> roles = new ArrayList<>();
//	roles.add(roleRepository.findById(2).get());
	
		
		 
	//	 request.login(user1.getEmail(),passworde);
	
	    
		
		// to login into the system , we use request object
		
		
	//	return "redirect:/";
	
	String email= user.getEmail();
	
	if(userRepository.findUserByEmail(email).isPresent()) {
		
		session.setAttribute("message", new Message("User Already Registered!! ","alert-success"));
		
	}else {
		User user1 = new User();
		
		// whenevr we use object of httpservlert we use get.Principal  .getattributes to extract data
    	user1.setFirstname(user.getFirstname());
       	user1.setLastname(user.getLastname());
     
     	user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       	
     //  	user1.setPassword(user.getPassword());
		user1.setEmail(email); 
		List<Role> roles = new ArrayList<>();
		
		roles.add(roleRepository.findById(2).get());
		user1 .setRoles(roles);
		
		
	    
     	userRepository.save(user1);
     	
     	session.setAttribute("message", new Message("Congratulations , Registration successfull !! ","alert-success"));
     	
     	
//     	request.login(user1.getEmail(),user1.getPassword());
}
	return "redirect:/";
	}

}
