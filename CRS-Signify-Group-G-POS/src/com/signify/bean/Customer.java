/**
 * 
 */
package com.signify.bean;

/**
 * @author dp201
 *
 */
public class Customer {
	
//	all customer attributes
	private int customerId;
	private String customerName, customerAddress;
	
//	all customer setters getters
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

}
