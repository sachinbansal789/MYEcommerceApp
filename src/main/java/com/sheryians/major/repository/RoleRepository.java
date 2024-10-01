package com.sheryians.major.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;


public interface RoleRepository extends JpaRepository<Role , Integer> {
	
	
	
}
