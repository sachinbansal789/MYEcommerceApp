package com.sheryians.major.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Forgotcontroller {
	
	@GetMapping("/forgotpassword")
	public String openForgotform() {
		
		return "forgot_password_form";
	}
	
	@PostMapping("/send-otp")
    public String sendOTP() {
    	
    }
	}
	
	
