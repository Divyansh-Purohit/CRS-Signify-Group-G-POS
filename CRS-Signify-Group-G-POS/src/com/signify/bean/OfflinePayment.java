/**
 * 
 */
package com.signify.bean;

/**
 * @author dp201
 *
 */
public class OfflinePayment extends Payment{
	private int cash, chequeNumber;
	private String bankName;
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public int getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(int chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}
