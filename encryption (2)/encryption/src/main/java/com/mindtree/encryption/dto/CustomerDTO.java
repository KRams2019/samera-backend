package com.mindtree.encryption.dto;

public class CustomerDTO {
	private  String customerId;
	private String customerName;
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public CustomerDTO() {
		super();
	}
	
}
