package com.mybootapp.main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class ProductGodown {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	
	private Product product;
	@ManyToOne
	
	private Godown godown;
	@OneToOne	
	
	private Supplier supplier;
	private int quantity;
	private String invoiceno;
	private String receiptno;
	private LocalDate dateodsupply;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Godown getGodown() {
		return godown;
	}
	public void setGodown(Godown godown) {
		this.godown = godown;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public String getReceiptno() {
		return receiptno;
	}
	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}
	
	
	
	
}
