package com.signify.bean;
import java.time.LocalDate;
public class Admin extends User {
	private LocalDate doj;

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	
}
