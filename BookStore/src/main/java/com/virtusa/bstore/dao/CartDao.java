package com.virtusa.bstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.util.DbConnection;

public class CartDao {
	public boolean updateQuantity(int bId,int quantity,String buyer)
	{
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="UPDATE cart SET quantity=? WHERE bookId=? AND Email=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1, quantity);
			ps.setInt(2, bId);
			ps.setString(3, buyer);
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
	public int existInCart(int bId,String buyer)
	{
		int quantity = 0;
		try
		{
			
			Connection con=DbConnection.getConnection();
			String cmd="SELECT quantity FROM cart WHERE bookId=? AND Email=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1, bId);
			ps.setString(2, buyer);
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				 quantity=res.getInt(1);
			}
			return quantity;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return quantity;
	}
	public boolean addCart(Cart c) 
	{
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="INSERT INTO cart VALUES(?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1, c.getBookId());
			ps.setString(2, c.getBookName() );
			ps.setInt(3, c.getBookPrice());
			ps.setInt(4, c.getQuantity());
			ps.setString(5, c.getBuyerEmail());
			ps.setString(6, c.getImage());
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
	public ArrayList<Cart> viewCart(String email)
	{
		try 
		{
			ArrayList<Cart> cartList=new ArrayList<Cart>();
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM cart WHERE Email=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, email);
			ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			int bId=res.getInt(1);
			String bookName=res.getString(2);
			int bookPrice=res.getInt(3);
			int quantity=res.getInt(4);
			String image=res.getString(6);
			Cart c=new Cart(bId,bookName,bookPrice,quantity,email,image);
			cartList.add(c);
		  }
		return cartList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public boolean removeCartItem(int bId,String email) {
		try 
		{
		Connection con=DbConnection.getConnection();
		String cmd="DELETE FROM cart WHERE Email=? AND bookId=?";
		PreparedStatement ps=con.prepareStatement(cmd);
		ps.setString(1, email);
		ps.setInt(2, bId);
		if(ps.executeUpdate()>0)
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean removeAllCartItems(String email) 
	{
			try 
			{
			Connection con=DbConnection.getConnection();
			String cmd="DELETE FROM cart WHERE Email=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, email);
			if(ps.executeUpdate()>0)
				return true;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return false;
	}
}
