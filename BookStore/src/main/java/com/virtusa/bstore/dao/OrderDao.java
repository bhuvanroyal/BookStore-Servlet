package com.virtusa.bstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.dto.OrderItem;
import com.virtusa.bstore.util.DbConnection;

public class OrderDao
{
	public int addingOrder(String email,int amount) 
	{
		int ordId=0 ;
		try
		{
			String status="active";
			Connection con=DbConnection.getConnection();
			String cmd="INSERT INTO orders(customerMail,Amount,status) VALUES(?,?,?)";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, email);
			ps.setInt(2, amount);
			ps.setString(3, status);
			if(ps.executeUpdate()>0) {
				PreparedStatement p1=con.prepareStatement("SELECT LAST_INSERT_ID()");
				ResultSet res=p1.executeQuery();
				if(res.next()) {
					ordId=res.getInt(1);
				}
				return ordId;
			}
		}catch(Exception e) {
			e.printStackTrace();
			}
		return ordId;
	}
	public boolean updateOrderStatus(String [] orderIds) 
	{
		try
		{
			String status="delivered";
			Connection con=DbConnection.getConnection();
			String cmd="UPDATE orders SET status=? WHERE orderId=?";
			for(String i:orderIds) 
			{
				int id=Integer.parseInt(i);
				PreparedStatement ps=con.prepareStatement(cmd);
				ps.setString(1,status);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean addingOrderItems(int ordId,String email,ArrayList<Cart> cartItems)
	{
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="INSERT INTO order_items VALUES(?,?,?,?,?,?)";
			Iterator<Cart> it=cartItems.iterator();
			while(it.hasNext()) {
				Cart c= it.next();
				PreparedStatement ps=con.prepareStatement(cmd);
				ps.setString(1,email);
				ps.setString(2, c.getBookName());
				ps.setInt(3, c.getBookPrice());
				ps.setInt(4,c.getQuantity());
				ps.setInt(5, ordId);
				ps.setString(6, c.getImage());
				ps.executeUpdate();
			}
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<Order> viewOrdersByUser(String email)
	{
		try 
		{
			ArrayList<Order> ordertList=new ArrayList<Order>();
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM orders WHERE customerMail=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, email);
			ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			int ordId=res.getInt(1);
			int amount=res.getInt(3);
			String status=res.getString(4);
			Order o=new Order(ordId,email,amount,status);
			ordertList.add(o);
		  }
		return ordertList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Order> viewOrdersByAdmin()
	{
		try 
		{
			String status="active";
			ArrayList<Order> ordertList=new ArrayList<Order>();
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM orders WHERE status=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, status);
			ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			int ordId=res.getInt(1);
			String email=res.getString(2);
			int amount=res.getInt(3);
			Order o=new Order(ordId,email,amount,status);
			ordertList.add(o);
		  }
		return ordertList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<OrderItem> viewOrderItems(int ordId,String buyerMail)
	{
		try 
		{
			ArrayList<OrderItem> orderItemtList=new ArrayList<OrderItem>();
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM order_items WHERE ordId=? AND customerMail=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1, ordId);
			ps.setString(2, buyerMail);
			ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			String bookName=res.getString(2);
			int bookPrice=res.getInt(3);
			int quantity=res.getInt(4);
			String image=res.getString(6);
			OrderItem ot=new OrderItem(buyerMail,bookName,bookPrice,quantity,ordId,image);
			orderItemtList.add(ot);
		  }
		return orderItemtList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
