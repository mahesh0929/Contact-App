package com.example.contacts.dto;

public class UserRegistrationDto {
	private String firstName;
	private String phone;
	private String email;
	private String password;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.phone = lastName;
		this.email = email;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return phone;
	}
	public void setLastName(String lastName) {
		this.phone = lastName;
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
}
