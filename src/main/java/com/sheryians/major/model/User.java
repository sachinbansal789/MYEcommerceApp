package com.sheryians.major.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.JoinColumn;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	
	@Id
 @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
//	
	@NotEmpty
	@Column(nullable = false)
	private String firstname;
	
	private String lastname;
	
	@NotEmpty
    @Column(nullable = false, unique = true)
	@Email( message = "{errors.invalid.email}" )
	private String email;
	
	
	
	private String password;
	
	// this will create a new table with user id and role id and this is the
	@ManyToMany(cascade=CascadeType.MERGE , fetch=FetchType.EAGER ) 
	@JoinTable(
	        name = "user_role",
	        joinColumns = @JoinColumn(
	                name = "USER_ID",
	                referencedColumnName = "ID"
	        ),
	        inverseJoinColumns = @JoinColumn(
	                name = "ROLE_ID",
	                referencedColumnName = "ID"
	        )
	)
	public  List<Role> roles;
//	public Role roles;


	public User(Integer id, @NotEmpty String firstname, String lastname,
			@NotEmpty @Email(message = "{errors.invalid.email}") String email, String password, List<Role> roles) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}



	public User(User user) {
		// TODO Auto-generated constructor stub
	}



	public User() {
		// TODO Auto-generated constructor stub
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public List<Role> getRoles() {
		return roles;
}



	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



	public void setRole(String string) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", roles=" + roles + "]";
	}


}


