/**
 * 
 */
package com.signify.bean;

/**
 * @author dp201
 *
 */
public class OnlinePayment extends Payment{
	private int card;
	private String cardNumber, cardType, modeOfTransfer, accountNumber, ifscode;
	public int getCard() {
		return card;
	}
	public void setCard(int card) {
		this.card = card;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getModeOfTransfer() {
		return modeOfTransfer;
	}
	public void setModeOfTransfer(String modeOfTransfer) {
		this.modeOfTransfer = modeOfTransfer;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfscode() {
		return ifscode;
	}
	public void setIfscode(String ifscode) {
		this.ifscode = ifscode;
	} 
}
