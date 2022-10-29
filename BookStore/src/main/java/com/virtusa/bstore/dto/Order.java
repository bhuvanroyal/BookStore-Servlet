package com.virtusa.bstore.dto;

public class Order {
	int ordId;
	String buyerMail;
	int amount;
	String status;
	public Order(int ordId, String buyerMail, int amount, String status) {
		super();
		this.ordId = ordId;
		this.buyerMail = buyerMail;
		this.amount = amount;
		this.status = status;
	}
	public int getOrdId() {
		return ordId;
	}
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
	public String getBuyerMail() {
		return buyerMail;
	}
	public void setBuyerMail(String buyerMail) {
		this.buyerMail = buyerMail;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [ordId=" + ordId + ", buyerMail=" + buyerMail + ", amount=" + amount + ", status=" + status + "]";
	}
	
	

}
