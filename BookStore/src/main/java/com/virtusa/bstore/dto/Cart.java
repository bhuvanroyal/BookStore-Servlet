package com.virtusa.bstore.dto;

public class Cart {
	int bookId;
	String bookName;
	int bookPrice;
	int quantity;
	String buyerEmail;
	String image;
	public Cart(int bookId, String bookName, int bookPrice, int quantity, String buyerEmail,String image) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.quantity = quantity;
		this.buyerEmail = buyerEmail;
		this.image=image;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
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
	public String getBuyerEmail() {
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Cart [bookId=" + bookId + ", bookName=" + bookName + ", bookPrice=" + bookPrice + ", quantity="
				+ quantity + ", buyerEmail=" + buyerEmail + "]";
	}
	

}
