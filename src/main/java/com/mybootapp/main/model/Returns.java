package com.mybootapp.main.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Returns {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@OneToOne
	private OutwardRegister outwardRegister;
	
	@Column(name="date_of_return")
	private LocalDate dateOfReturn;
	
	@Column(length = 2000)
	private String reasonOfReturn;

	public int getId() {
		return id;
	}

	public OutwardRegister getOutwardRegister() {
		return outwardRegister;
	}

	public LocalDate getDateOfReturn() {
		return dateOfReturn;
	}

	public String getReasonOfReturn() {
		return reasonOfReturn;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOutwardRegister(OutwardRegister outwardRegister) {
		this.outwardRegister = outwardRegister;
	}

	public void setDateOfReturn(LocalDate dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public void setReasonOfReturn(String reasonOfReturn) {
		this.reasonOfReturn = reasonOfReturn;
	}
}