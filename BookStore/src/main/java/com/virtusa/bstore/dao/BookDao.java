package com.virtusa.bstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.util.DbConnection;

public class BookDao {
	
	public int getBookPrice(int id) {
		int price;
		try
		{
			
			Connection con=DbConnection.getConnection();
			String cmd="SELECT bookPrice FROM book WHERE bookId=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1, id);
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				price=res.getInt(1);
				return price;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;	
	}
	public Book searchBook(int bId) {
		try
		{
			
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM book WHERE bookId=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1, bId);
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				int id=res.getInt(1);
				String bookName=res.getString(2);
				String bookAuthor=res.getString(3);
				String bookCategory=res.getString(4);
				int quantity=res.getInt(5);
				int price=res.getInt(6);
				String image=res.getString(7);
				Book b=new Book(id,bookName,bookAuthor,bookCategory,quantity,price,image);
				return b;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;	
	}
	public boolean isBookExist(int id)
	{
		try
		{
			
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM book WHERE bookId=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1, id);
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				return true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;	
	}
	public boolean addBook(Book b)
	{
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="INSERT INTO book VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1, b.getBookId());
			ps.setString(2, b.getBookName());
			ps.setString(3, b.getBookAuthor());
			ps.setString(4, b.getBookCategory());
			ps.setInt(5, b.getQuantity());
			ps.setInt(6,b.getBookPrice());
			ps.setString(7, b.getImage());
			if(ps.executeUpdate()>0)
			{
				return true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<Book> getBooks()
	{
		try 
		{
			ArrayList<Book> bookList=new ArrayList<Book>();
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM book";
			PreparedStatement ps=con.prepareStatement(cmd);
			ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			int id=res.getInt(1);
			String bookName=res.getString(2);
			String bookAuthor=res.getString(3);
			String bookCategory=res.getString(4);
			int quantity=res.getInt(5);
			int price=res.getInt(6);
			String image=res.getString(7);
			Book l=new Book(id,bookName,bookAuthor,bookCategory,quantity,price,image);
			bookList.add(l);
		  }
		return bookList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public boolean updateBookQuantity(ArrayList<Cart> cartItems) {
		try {
		Connection con=DbConnection.getConnection();
		String cmd1="SELECT quantity FROM book WHERE bookId=?";
		String cmd2="UPDATE book SET quantity=? WHERE bookId=?";
		Iterator<Cart> it=cartItems.iterator();
		while(it.hasNext()) {
			int quantity=0;
			Cart c= it.next();
			PreparedStatement ps1=con.prepareStatement(cmd1);
			ps1.setInt(1, c.getBookId());
			ResultSet res=ps1.executeQuery();
			if(res.next()) {
				quantity=res.getInt(1);
			}
			quantity-=c.getQuantity();
			PreparedStatement ps2=con.prepareStatement(cmd2);
			ps2.setInt(1, quantity);
			ps2.setInt(2, c.getBookId());
			ps2.executeUpdate();
		}
		return true;	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public boolean removeBook(int id) 
	{
		try 
		{
		Connection con=DbConnection.getConnection();
		String cmd="DELETE FROM book WHERE bookId=?";
		PreparedStatement ps=con.prepareStatement(cmd);
		PreparedStatement ps1=con.prepareStatement("DELETE FROM cart WHERE bookId=?");
		ps1.setInt(1, id);
		ps.setInt(1, id);
		if(ps.executeUpdate()>0 || ps1.executeUpdate()>0)
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean modify(int bId,String bName,String bAuthor,String bCategory,int quantity,int price) 
	{
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="UPDATE cart SET bookName=?,price=? WHERE bookId=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1,bName);
			ps.setInt(2, price);
			ps.setInt(3, bId);
			ps.executeUpdate();
			PreparedStatement ps1=con.prepareStatement("UPDATE book SET bookName=?,bookAuthor=?,bookCategory=?,quantity=?,bookPrice=? WHERE bookId=?");
			ps1.setString(1, bName);
			ps1.setString(2, bAuthor);
			ps1.setString(3, bCategory);
			ps1.setInt(4, quantity);
			ps1.setInt(5, price);
			ps1.setInt(6, bId);
			if(ps1.executeUpdate()>0) {
				return true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<Book> searchByCategory(String category){
		try 
		{
			ArrayList<Book> categoryList=new ArrayList<Book>();
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM book where bookCategory=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1,category);
			ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			int id=res.getInt(1);
			String bookName=res.getString(2);
			String bookAuthor=res.getString(3);
			String bookCategory=res.getString(4);
			int quantity=res.getInt(5);
			int price=res.getInt(6);
			String image=res.getString(7);
			Book l=new Book(id,bookName,bookAuthor,bookCategory,quantity,price,image);
			categoryList.add(l);
		  }
		return categoryList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
