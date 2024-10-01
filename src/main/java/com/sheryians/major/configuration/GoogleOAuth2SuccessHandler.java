package com.sheryians.major.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.repository.UserRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler  {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	// if we want to redirect something internally we can use this
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
     
	// we will get token from authentication, if google has user's information  , we can get it from authentication
	// we will extract details from authentication and put into token
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequestrequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		
		// we have to upcast authentication
		OAuth2AuthenticationToken token =(OAuth2AuthenticationToken) authentication;
		
		// we will use this get principal method in OAuth cases , extraction email from token
		String email = token.getPrincipal().getAttributes().get("email").toString();
		
		
		// if details of user is present inside database we will not do anything otherwise we have to add the details from token to database
		// we are checking inside userrepo by email
		if(userRepository.findUserByEmail(email).isPresent()) {
			
		}else {
			User user = new User();
			
			// whenevr we use object of httpservlert we use get.Principal  .getattributes to extract data
			user.setFirstname(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastname(token.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);
			List<Role> roles = new ArrayList<>();
			
			roles.add(roleRepository.findById(2).get());
			user.setRoles(roles);
		    
	     	userRepository.save(user);
	}
		
		redirectStrategy.sendRedirect(httpServletRequestrequest, httpServletResponse, "/");
		
	

}
}
