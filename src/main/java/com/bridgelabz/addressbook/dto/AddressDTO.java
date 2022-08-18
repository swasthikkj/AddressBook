package com.bridgelabz.addressbook.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressDTO {
	
	private long aadharNumber;
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "First name Invalid")
	private String firstName;
	@Pattern(regexp = "^[a-z]{1}[a-zA-Z\\s]{2,}$", message = "last name Invalid")
	private String lastName;
	@Pattern(regexp = "^[9,8,7,6]{1}[0-9]{9}$", message = "mobile number invalid")
	private long mobileNumber;
	@NotBlank(message = "city cannot be empty")
	private String city;
	@NotBlank(message = "state cannot be empty")
	private String state;
	@Pattern(regexp = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$",message = "Pincode Invalid")
	private long pincode;
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "email is invalid")
	private String emailId;
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%!]).{8,}$", message = "password is invalid")
	private String password;
}
