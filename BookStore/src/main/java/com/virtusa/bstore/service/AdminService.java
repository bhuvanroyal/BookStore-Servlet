package com.virtusa.bstore.service;

import java.util.ArrayList;

import com.virtusa.bstore.dao.AdminLoginDao;
import com.virtusa.bstore.dao.BookDao;
import com.virtusa.bstore.dao.OrderDao;
import com.virtusa.bstore.dao.UserDao;
import com.virtusa.bstore.dto.AdminLogin;
import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.dto.OrderItem;
import com.virtusa.bstore.dto.User;

public class AdminService {
	AdminLoginDao ad;
	BookDao bd;
	UserDao ud;
	OrderDao od;
	public ArrayList <AdminLogin> viewAllAdmins()
	{
		ad=new AdminLoginDao();
		return ad.viewAllAdmins();
	}
	public boolean modifyProfile(String name,String email,String pwd) {
		ad=new AdminLoginDao();
		AdminLogin a=new AdminLogin(name,email,pwd);
		return ad.modifyProfile(a);
	}
	public AdminLogin viewProfile(String email) {
		ad=new AdminLoginDao();
		return ad.viewProfile(email);
	}
	public boolean validateAdmin(String adEmail,String pwd)
	{
		ad=new AdminLoginDao();
		return ad.validateAdmin(adEmail, pwd);
	}
	public boolean addAdmin(String name,String email,String pwd)
	{
		ad=new AdminLoginDao();
		return ad.addAdmin(new AdminLogin(name,email,pwd));
	}
	public boolean addBook(int id,String bName,String bAuthor,String bCategory,int quantity,int price,String image)
	{
		bd=new BookDao();
		return bd.addBook(new Book(id,bName,bAuthor,bCategory,quantity,price,image));
	}
	public ArrayList<Book> getBooks()
	{
		bd=new BookDao();
		return bd.getBooks();
	}
	public boolean removeBook(int id) 
	{
		bd=new BookDao();
		return bd.removeBook(id);
	}
	public boolean modify(int id,String bName,String bAuthor,String bCategory,int quantity,int price) 
	{
		bd=new BookDao();
		return bd.modify(id,bName,bAuthor,bCategory,quantity,price);
	}
	public ArrayList<Book> searchByCategory(String category){
		bd=new BookDao();
		return bd.searchByCategory(category);
	}
	public ArrayList<Order> viewOrders()
	{
		od=new OrderDao();
		return od.viewOrdersByAdmin();
	}
	public boolean updateOrderStatus(String [] orderIds) 
	{
		od=new OrderDao();
		return od.updateOrderStatus(orderIds);
	}
	public ArrayList<OrderItem> viewOrderItems(int ordId,String buyerMail)
	{
		od=new OrderDao();
		return od.viewOrderItems(ordId, buyerMail);
	}
	public ArrayList <User> viewAllUsers()
	{
		ud=new UserDao();
		return ud.viewAllUsers();
	}
	public boolean removeUser(int id) 
	{
		ud=new UserDao();
		return ud.removeUser(id);
	}
	public boolean isBookExist(int id)
	{
		bd=new BookDao();
		return bd.isBookExist(id);
	}
	public int getBookPrice(int id) {
		bd=new BookDao();
		return bd.getBookPrice(id);
	}
	public Book searchBook(int bId) {
		bd=new BookDao();
		return bd.searchBook(bId);
	}
}
