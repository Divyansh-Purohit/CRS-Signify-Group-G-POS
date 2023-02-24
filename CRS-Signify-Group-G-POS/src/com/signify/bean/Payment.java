package com.signify.bean;
public class Payment {
	
	private String studentId, referencedId, mode;
	private float amount;
	private int status;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getReferencedId() {
		return referencedId;
	}
	public void setReferencedId(String referencedId) {
		this.referencedId = referencedId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
