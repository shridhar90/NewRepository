package com.Banking.Banking.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_details")
public class CustomerDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    
    private String address;
    
    private int age;
    
    private Date dob;
    
    private String emailId;
    
    private String firstName;
    
    private String gender;
    
    private String lastName;
    
    private String panNumber;
    
    private String phoneNumber;
    
    

	public CustomerDetails() {
	}

	public CustomerDetails(String address, int age, Date dob, String emailId, String firstName,
			String gender, String lastName, String panNumber, String phoneNumber) {
		this.address = address;
		this.age = age;
		this.dob = dob;
		this.emailId = emailId;
		this.firstName = firstName;
		this.gender = gender;
		this.lastName = lastName;
		this.panNumber = panNumber;
		this.phoneNumber = phoneNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "CustomerDetails [customerId=" + customerId + ", address=" + address + ", age=" + age + ", dob=" + dob
				+ ", emailId=" + emailId + ", firstName=" + firstName + ", gender=" + gender + ", lastName=" + lastName
				+ ", panNumber=" + panNumber + ", phoneNumber=" + phoneNumber + "]";
	}
}
