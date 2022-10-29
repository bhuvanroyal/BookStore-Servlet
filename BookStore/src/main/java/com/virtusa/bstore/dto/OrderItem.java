package com.virtusa.bstore.dto;

public class OrderItem {
	String buyerMail;
	String bookName;
	int bookPrice;
	int quantity;
	int ordId;
	String image;
	public OrderItem(String buyerMail, String bookName, int bookPrice, int quantity, int ordId, String image) {
		super();
		this.buyerMail = buyerMail;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.quantity = quantity;
		this.ordId = ordId;
		this.image = image;
	}
	public String getBuyerMail() {
		return buyerMail;
	}
	public void setBuyerMail(String buyerMail) {
		this.buyerMail = buyerMail;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrdId() {
		return ordId;
	}
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "OrderItem [buyerMail=" + buyerMail + ", bookName=" + bookName + ", bookPrice=" + bookPrice
				+ ", quantity=" + quantity + ", ordId=" + ordId + ", image=" + image + "]";
	}
	

}
