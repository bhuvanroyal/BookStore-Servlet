package com.virtusa.bstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.virtusa.bstore.dto.AdminLogin;
import com.virtusa.bstore.dto.User;
import com.virtusa.bstore.util.DbConnection;

public class AdminLoginDao {

	public boolean validateAdmin(String adEmail,String pwd)
	{
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM adminData WHERE email=? and password=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1,adEmail);
			ps.setString(2, pwd);
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
	public boolean addAdmin(AdminLogin l)
	{
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="INSERT INTO adminData VALUES(?,?,?)";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, l.getAdminName());
			ps.setString(2, l.getAdminEmail());
			ps.setString(3, l.getAdminPassword());
			ps.executeUpdate();
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
			
	}
	public AdminLogin viewProfile(String email) {
		try
		{
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM adminData WHERE email=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setString(1, email);
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				String adminName=res.getString(1);
				String password=res.getString(3);
				
				AdminLogin a=new AdminLogin(adminName,email,password);
				return a;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	public boolean modifyProfile(AdminLogin a) {
		try 
		{
		Connection con=DbConnection.getConnection();
		String cmd="UPDATE adminData SET name=?,password=? WHERE email=?";
		PreparedStatement ps=con.prepareStatement(cmd);
		ps.setString(1, a.getAdminName());
		ps.setString(2, a.getAdminPassword());
		ps.setString(3, a.getAdminEmail());
		if(ps.executeUpdate()>0) {
			return true;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList <AdminLogin> viewAllAdmins()
	{
		try 
		{
			ArrayList<AdminLogin> adminList=new ArrayList<AdminLogin>();
			Connection con=DbConnection.getConnection();
			String cmd="SELECT * FROM adminData";
			PreparedStatement ps=con.prepareStatement(cmd);
			ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			String name=res.getString(1);
			String email=res.getString(2);
			String password=res.getString(3);
			AdminLogin a=new AdminLogin(name,email,password);
			adminList.add(a);
		  }
		return adminList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
