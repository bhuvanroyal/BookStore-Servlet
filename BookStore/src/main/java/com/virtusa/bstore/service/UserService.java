package com.virtusa.bstore.service;

import java.util.ArrayList;

import com.virtusa.bstore.dao.BookDao;
import com.virtusa.bstore.dao.CartDao;
import com.virtusa.bstore.dao.OrderDao;
import com.virtusa.bstore.dao.UserDao;
import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.dto.OrderItem;
import com.virtusa.bstore.dto.User;

public class UserService {
	UserDao ud;
	BookDao bd;
	CartDao cd;
	OrderDao od;
	public boolean userExist(int id) {
		ud=new UserDao();
		return ud.userExist(id);
	}
	public boolean modifyUser(String pwd,String location,int age,String mobileNo,String dob,String userEmail) {
		ud=new UserDao();
		return ud.modifyUser(pwd,location,age,mobileNo,dob,userEmail);
	}
	public boolean addUser(int uId,String userEmail,String pwd,String location,int age,String mobileNo,String dob) {
		ud=new UserDao();
		return ud.addUser(new User(uId,userEmail,pwd,location,age,mobileNo,dob));
	}
	public ArrayList<Book> getBooks()
	{
		bd=new BookDao();
		return bd.getBooks();
	}
	public ArrayList<Book> searchByCategory(String category){
		bd=new BookDao();
		return bd.searchByCategory(category);
	}
	public boolean validateUser(String userEmail,String userPwd)
	{
		ud=new UserDao();
		return ud.validateUser(userEmail, userPwd);
	}
	public boolean addCart(int bId,String bookName,int price,int quantity,String email,String image) {
		cd=new CartDao();
		return cd.addCart(new Cart(bId,bookName,price,quantity,email,image));
	}
	public int existInCart(int bId,String buyer) {
		cd=new CartDao();
		return cd.existInCart(bId,buyer);
	}
	public boolean updateQuantity(int bId,int quantity,String buyer) {
		cd=new CartDao();
		return cd.updateQuantity(bId, quantity,buyer);
	}
	public ArrayList<Cart> viewCartItems(String buyer){
		cd=new CartDao();
		return cd.viewCart(buyer);
	}
	public ArrayList<OrderItem> viewOrderItems(int ordId,String buyerMail)
	{
		od=new OrderDao();
		return od.viewOrderItems(ordId, buyerMail);
	}
	public User searchUser(String email) {
		ud=new UserDao();
		return ud.searchUser(email);
	}
	public boolean addingOrder(ArrayList<Cart> cartItems,String email,int total) 
	{
		od=new OrderDao();
		cd=new CartDao();
		bd=new BookDao();
		int ordId=od.addingOrder(email, total);
		if(od.addingOrderItems(ordId, email, cartItems))
		{
			if(cd.removeAllCartItems(email)) {
				bd.updateBookQuantity(cartItems);
				return true;
			}
		}else {
			return false;
		}
		return false;
	}
	public boolean removeCartItem(int bId,String email) {
		cd=new CartDao();
		return cd.removeCartItem(bId, email);
	}
	public ArrayList<Order> viewOrdersByUser(String email)
	{
		od=new OrderDao();
		return od.viewOrdersByUser(email);
	}
		
}
