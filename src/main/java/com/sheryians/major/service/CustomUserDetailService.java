package com.sheryians.major.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.CustomUserDetail;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.UserRepository;

@Service
// @Transactional
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository ;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// if user details are present inside userrepo it will comes under user 
		Optional<User> user = userRepository.findUserByEmail(email);
		
		// if we are not able to find user details inside userrepo through email
		// orElseThrow method is fn used with else when we are not able to find user and throw this particular exception
		user.orElseThrow(() -> new UsernameNotFoundException("user to nahi mila"));
		
		// CustomUserDetail:: new ,this method is used to create an object of CustomUserDetail object
		
		return user.map(CustomUserDetail::new).get();
	}
	

}
