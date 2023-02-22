package com.signify.jdbc;

public interface StudentPaymentInterface {
	public void payFeesByCheque(Integer studentId, String bankName , String chequeNumber , int amount);
	public void payFeesByCard(Integer studentId, String cardNumber , String cardType , int amount);
	public void payFeesByNetBanking(Integer studentId, String modeOfTransfer , String accountNumber , String ifscCode , int amount);
	public void payFeesByCash(Integer studentId , int amount);
}