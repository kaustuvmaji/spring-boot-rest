package com.example.demo.domain;

import java.io.Serializable;
import java.util.List;

public class ContactDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 799818822284620731L;

	private List<Address> address;
	private String emailId;
	private Integer phoneNumber;
	private Integer mobileNumber;

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public ContactDetail() {
	}

	public ContactDetail(List<Address> address, String emailId, Integer phoneNumber, Integer mobileNumber) {
		super();
		this.address = address;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "ContactDetail [" + (address != null ? "address=" + address + ", " : "")
				+ (emailId != null ? "emailId=" + emailId + ", " : "")
				+ (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
				+ (mobileNumber != null ? "mobileNumber=" + mobileNumber : "") + "]";
	}

}