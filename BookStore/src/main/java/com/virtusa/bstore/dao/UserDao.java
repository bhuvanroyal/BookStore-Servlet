package com.virtusa.bstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.virtusa.bstore.dto.User;
import com.virtusa.bstore.util.DbConnection;

public class UserDao {
	public boolean validateUser(String userEmail,String userPwd)
	{
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM userData WHERE Email=? and password=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1,userEmail);
			ps.setString(2,userPwd);
			ResultSet res=ps.executeQuery();
			if(res.next())
				return true;
			return false;	
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;	
	}
	public ArrayList <User> viewAllUsers()
	{
		try 
		{
			ArrayList<User> userList=new ArrayList<User>();
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM userData";
			PreparedStatement ps=con.prepareStatement(cmd);
			ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			int userId=res.getInt(1);
			String userEmail=res.getString(2);
			String userPwd=res.getString(3);
			String location=res.getString(4);
			int age=res.getInt(5);
			String  mobileNo=String.valueOf(res.getLong(6));
			java.sql.Date dt=res.getDate(7);
			Date d=new Date(dt.getTime());
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MMM/yyyy");
			User l=new User(userId,userEmail,userPwd,location,age,mobileNo,sdf.format(d));
			userList.add(l);
		  }
		return userList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public boolean removeUser(int id) 
	{
		try 
		{
		Connection con=DbConnection.getConnection();
		String cmd="DELETE FROM userData WHERE userId=?";
		PreparedStatement ps=con.prepareStatement(cmd);
		ps.setInt(1, id);
	    if(ps.executeUpdate()>0)
	    	return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean modifyUser(String pwd,String location,int age,String mobileNo,String dob,String userEmail) {
		try 
		{
		Connection con=DbConnection.getConnection();
		String cmd="UPDATE userData SET password=?,location=?,age=?,mobileNumber=?,dob=? WHERE Email=?";
		PreparedStatement ps=con.prepareStatement(cmd);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dt=sdf.parse(dob);
		java.sql.Date sdate=new java.sql.Date(dt.getTime());
		ps.setString(1, pwd);
		ps.setString(2, location);
		ps.setInt(3, age);
		ps.setLong(4,Long.parseLong(mobileNo));
		ps.setDate(5, sdate);
		ps.setString(6, userEmail);
		if(ps.executeUpdate()>0) {
			return true;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean userExist(int id) {
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM userData WHERE userId=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1,id);
			if(ps.executeUpdate()>0) {
				return true;
			}
		}catch(Exception e) {
			
		}
		return false;
	}
	public User searchUser(String email) {
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM userData WHERE Email=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, email);
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				int userId=res.getInt(1);
				String userEmail=res.getString(2);
				String userPwd=res.getString(3);
				String location=res.getString(4);
				int age=res.getInt(5);
				String  mobileNo=String.valueOf(res.getLong(6));
				java.sql.Date dt=res.getDate(7);
				Date d=new Date(dt.getTime());
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MMM/yyyy");
				User u=new User(userId,userEmail,userPwd,location,age,mobileNo,sdf.format(d));
				return u;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public boolean addUser(User u) {
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="INSERT INTO userData VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1,u.getUserId());
			ps.setString(2, u.getUserEmail());
			ps.setString(3, u.getPassWord());
			ps.setString(4, u.getLocation());
			ps.setInt(5, u.getAge());
			ps.setLong(6,Long.parseLong(u.getUserMobileNo()));
			String nwDob=u.getDob();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date dt=sdf.parse(nwDob);
			java.sql.Date sdate=new java.sql.Date(dt.getTime());
			ps.setDate(7, sdate);
			ps.executeUpdate();
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
