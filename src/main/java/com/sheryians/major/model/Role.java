package com.sheryians.major.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "roles")
public class Role {
	
	
		public Role() {
			
		}
	

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", users=" + users + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
	@NotEmpty
//	@Column(name="role_id")
//	 @GeneratedValue(generator="system-uuid")
//	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private Integer id;
	
	public Role(@NotEmpty Integer id, @NotEmpty String name, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}

	@NotEmpty
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	public List<User> users;
	

}
