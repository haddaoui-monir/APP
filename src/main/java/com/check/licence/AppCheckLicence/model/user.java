package com.check.licence.AppCheckLicence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class user {
	
	
	
    private String firstName;
     
    

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotNull(message = "First Name cannot be null")
	private String name;
	@NotNull(message = "Password cannot be null")
	private String password;
	@Min(value = 15, message = "Age should not be less than 15")
    @Max(value = 65, message = "Age should not be greater than 65")
    private int age;
	private String Email;
	
	public user(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		Email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	@Override
	public String toString() {
		return "user [name=" + name + ", password=" + password + ", Email=" + Email + "]";
	}

}
